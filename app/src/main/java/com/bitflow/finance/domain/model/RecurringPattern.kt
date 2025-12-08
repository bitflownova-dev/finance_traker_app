package com.bitflow.finance.domain.model

import java.time.LocalDate

/**
 * Detected recurring payment pattern (subscription detective)
 */
data class RecurringPattern(
    val id: Long = 0,
    val merchantName: String,
    val averageAmount: Double,
    val frequency: RecurrenceFrequency,
    val lastDetectedDate: LocalDate,
    val occurrenceCount: Int,
    val isConfirmedSubscription: Boolean = false,
    val isDismissed: Boolean = false,
    val categoryId: Long?
)

enum class RecurrenceFrequency {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}

/**
 * UI-friendly subscription card data
 */
data class SubscriptionDetectionCard(
    val patternId: Long,
    val merchantName: String,
    val amount: Double,
    val frequency: String, // "monthly", "weekly", etc.
    val lastPaymentDate: LocalDate
)
