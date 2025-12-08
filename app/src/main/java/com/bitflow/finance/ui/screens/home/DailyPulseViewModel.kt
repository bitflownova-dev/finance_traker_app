package com.bitflow.finance.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitflow.finance.domain.model.Activity
import com.bitflow.finance.domain.model.ActivityType
import com.bitflow.finance.domain.model.RecurringPattern
import com.bitflow.finance.domain.repository.TransactionRepository
import com.bitflow.finance.domain.usecase.SubscriptionDetective
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class DailyPulseViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val subscriptionDetective: SubscriptionDetective,
    private val settingsRepository: com.bitflow.finance.domain.repository.SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DailyPulseUiState())
    val uiState: StateFlow<DailyPulseUiState> = _uiState.asStateFlow()

    init {
        loadUserName()
        loadAccountBalance()
        loadRecentActivities()
        // Subscriptions detection removed for simpler startup
    }

    private fun loadUserName() {
        viewModelScope.launch {
            settingsRepository.userName.collect { name ->
                _uiState.value = _uiState.value.copy(userName = name)
            }
        }
    }

    /**
     * Calculate actual balance from all transactions
     */
    private fun loadAccountBalance() {
        viewModelScope.launch {
            transactionRepository.getAllTransactions().collect { transactions ->
                // Calculate total income and expenses from all transactions
                val totalIncome = transactions
                    .filter { it.type == ActivityType.INCOME }
                    .sumOf { it.amount }
                
                val totalExpenses = transactions
                    .filter { it.type == ActivityType.EXPENSE }
                    .sumOf { it.amount }
                
                val currentBalance = totalIncome - totalExpenses
                
                // Calculate today's expenses
                val today = LocalDate.now()
                val todayExpenses = transactions
                    .filter { it.activityDate == today && it.type == ActivityType.EXPENSE }
                    .sumOf { it.amount }
                
                // Calculate this month's stats
                val startOfMonth = today.withDayOfMonth(1)
                val monthIncome = transactions
                    .filter { it.activityDate >= startOfMonth && it.type == ActivityType.INCOME }
                    .sumOf { it.amount }
                
                val monthExpenses = transactions
                    .filter { it.activityDate >= startOfMonth && it.type == ActivityType.EXPENSE }
                    .sumOf { it.amount }

                // Determine pulse status based on monthly flow
                val pulseStatus = when {
                    monthIncome > monthExpenses * 1.3 -> PulseStatus.GOOD // Saving >30%
                    monthIncome > monthExpenses -> PulseStatus.CAUTION // Breaking even
                    else -> PulseStatus.SLOW_DOWN // Spending more than earning
                }

                _uiState.value = _uiState.value.copy(
                    currentBalance = currentBalance,
                    todayExpenses = todayExpenses,
                    monthIncome = monthIncome,
                    monthExpenses = monthExpenses,
                    pulseStatus = pulseStatus
                )
            }
        }
    }

    /**
     * Load all activities for filtering - they're already sorted by date DESC in DAO
     */
    private fun loadRecentActivities() {
        viewModelScope.launch {
            transactionRepository.getAllTransactions().collect { activities ->
                // Activities are already sorted by txnDate DESC from DAO
                _uiState.value = _uiState.value.copy(recentActivities = activities)
            }
        }
    }

    /**
     * Phase 3: Detect potential subscriptions
     */
    private fun detectSubscriptions() {
        viewModelScope.launch {
            val threeMonthsAgo = LocalDate.now().minusMonths(3)
            val allTransactions = mutableListOf<com.bitflow.finance.data.local.entity.TransactionEntity>()
            
            // TODO: Get transactions from repository
            // val transactions = transactionRepository.getTransactionsForSubscriptionDetection(threeMonthsAgo)
            
            val patterns = subscriptionDetective.detectPotentialSubscriptions(
                transactions = allTransactions,
                lookbackMonths = 3
            )

            _uiState.value = _uiState.value.copy(
                potentialSubscriptions = patterns.take(2) // Show max 2 at a time
            )
        }
    }

    fun confirmSubscription(pattern: RecurringPattern) {
        viewModelScope.launch {
            // TODO: Save as confirmed subscription
            // transactionRepository.confirmSubscription(pattern)
            
            // Remove from potential list
            _uiState.value = _uiState.value.copy(
                potentialSubscriptions = _uiState.value.potentialSubscriptions.filter { it != pattern }
            )
        }
    }

    fun dismissSubscription(pattern: RecurringPattern) {
        viewModelScope.launch {
            // Remove from list without saving
            _uiState.value = _uiState.value.copy(
                potentialSubscriptions = _uiState.value.potentialSubscriptions.filter { it != pattern }
            )
        }
    }

    fun deleteActivity(activityId: Long) {
        viewModelScope.launch {
            transactionRepository.deleteTransaction(activityId)
            // TODO: Add undo support
            // Balance will auto-refresh via Flow
        }
    }

    fun togglePrivacyMode() {
        _uiState.value = _uiState.value.copy(
            isPrivacyMode = !_uiState.value.isPrivacyMode
        )
    }
}

data class DailyPulseUiState(
    val userName: String = "",
    val currentBalance: Double = 0.0,
    val todayExpenses: Double = 0.0,
    val monthIncome: Double = 0.0,
    val monthExpenses: Double = 0.0,
    val pulseStatus: PulseStatus = PulseStatus.GOOD,
    val recentActivities: List<Activity> = emptyList(),
    val potentialSubscriptions: List<RecurringPattern> = emptyList(),
    val isPrivacyMode: Boolean = false
)
