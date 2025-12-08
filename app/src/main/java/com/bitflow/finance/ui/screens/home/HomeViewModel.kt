package com.bitflow.finance.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitflow.finance.domain.model.Account
import com.bitflow.finance.domain.model.Transaction
import com.bitflow.finance.domain.repository.AccountRepository
import com.bitflow.finance.domain.repository.SettingsRepository
import com.bitflow.finance.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor(
    accountRepository: AccountRepository,
    transactionRepository: TransactionRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _selectedAccountId = MutableStateFlow<Long?>(null)

    val uiState: StateFlow<HomeUiState> = combine(
        accountRepository.getAllAccounts(),
        transactionRepository.getAllTransactions(),
        settingsRepository.currencySymbol,
        settingsRepository.isPrivacyModeEnabled,
        _selectedAccountId
    ) { accounts, transactions, currency, isPrivacyMode, selectedAccountId ->
        println("[HomeViewModel] Accounts count: ${accounts.size}")
        println("[HomeViewModel] Transactions count: ${transactions.size}")
        
        val totalBalance = if (selectedAccountId != null) {
            accounts.find { it.id == selectedAccountId }?.currentBalance ?: 0.0
        } else {
            accounts.sumOf { it.currentBalance }
        }

        HomeUiState(
            accounts = accounts,
            recentTransactions = transactions.take(10),
            totalNetWorth = totalBalance,
            currencySymbol = currency,
            isPrivacyMode = isPrivacyMode,
            selectedAccountId = selectedAccountId
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeUiState()
    )

    fun togglePrivacyMode() {
        viewModelScope.launch {
            val currentMode = uiState.value.isPrivacyMode
            settingsRepository.setPrivacyMode(!currentMode)
        }
    }

    fun selectAccount(accountId: Long?) {
        _selectedAccountId.value = if (_selectedAccountId.value == accountId) null else accountId
    }
}

data class HomeUiState(
    val accounts: List<Account> = emptyList(),
    val recentTransactions: List<Transaction> = emptyList(),
    val totalNetWorth: Double = 0.0,
    val currencySymbol: String = "₹",
    val isPrivacyMode: Boolean = false,
    val selectedAccountId: Long? = null
)
