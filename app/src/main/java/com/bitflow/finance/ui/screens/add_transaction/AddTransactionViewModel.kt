package com.bitflow.finance.ui.screens.add_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bitflow.finance.domain.model.Activity
import com.bitflow.finance.domain.model.ActivityType
import com.bitflow.finance.domain.model.Category
import com.bitflow.finance.domain.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddTransactionUiState())
    val uiState: StateFlow<AddTransactionUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            transactionRepository.getAllCategories().collect { categories ->
                _uiState.value = _uiState.value.copy(
                    categories = categories.filter { !it.isHidden }
                )
            }
        }
    }

    fun setType(type: ActivityType) {
        _uiState.value = _uiState.value.copy(type = type)
    }

    fun setAmount(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount)
    }

    fun selectCategory(category: Category) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
    }

    fun setDescription(description: String) {
        _uiState.value = _uiState.value.copy(description = description)
    }

    fun setNotes(notes: String) {
        _uiState.value = _uiState.value.copy(notes = notes)
    }

    fun setBillPhoto(uri: String?) {
        _uiState.value = _uiState.value.copy(billPhotoUri = uri)
    }

    fun saveTransaction() {
        viewModelScope.launch {
            val state = _uiState.value
            val amountValue = state.amount.toDoubleOrNull() ?: return@launch

            val activity = Activity(
                accountId = 1L, // TODO: Allow user to select account
                activityDate = LocalDate.now(),
                valueDate = LocalDate.now(),
                description = state.description,
                reference = null,
                amount = amountValue,
                type = state.type,
                categoryId = state.selectedCategory?.id,
                tags = emptyList(),
                billPhotoUri = state.billPhotoUri,
                createdAt = java.time.LocalDateTime.now(),
                notes = state.notes
            )

            transactionRepository.insertTransaction(activity)
            _uiState.value = _uiState.value.copy(saved = true)
        }
    }
}

data class AddTransactionUiState(
    val type: ActivityType = ActivityType.EXPENSE,
    val amount: String = "",
    val selectedCategory: Category? = null,
    val categories: List<Category> = emptyList(),
    val description: String = "",
    val notes: String = "",
    val billPhotoUri: String? = null,
    val saved: Boolean = false
)
