package com.bitflow.finance.data.repository

import com.bitflow.finance.data.local.dao.CategoryDao
import com.bitflow.finance.data.local.dao.LearningRuleDao
import com.bitflow.finance.data.local.dao.TransactionDao
import com.bitflow.finance.data.local.entity.CategoryEntity
import com.bitflow.finance.data.local.entity.LearningRuleEntity
import com.bitflow.finance.data.local.entity.TransactionEntity
import com.bitflow.finance.domain.model.Activity
import com.bitflow.finance.domain.model.ActivityType
import com.bitflow.finance.domain.model.Category
import com.bitflow.finance.domain.model.CategoryLearningRule
import com.bitflow.finance.domain.model.RecurringPattern
import com.bitflow.finance.domain.model.SubscriptionDetectionCard
import com.bitflow.finance.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val categoryDao: CategoryDao,
    private val learningRuleDao: LearningRuleDao
) : TransactionRepository {

    override fun getAllTransactions(): Flow<List<Activity>> {
        return transactionDao.getAllTransactions().map { entities -> entities.map { it.toDomain() } }
    }

    override fun getTransactionsForAccount(accountId: Long): Flow<List<Activity>> {
        return transactionDao.getTransactionsForAccount(accountId).map { entities -> entities.map { it.toDomain() } }
    }

    override fun getTransactionsInPeriod(startDate: LocalDate, endDate: LocalDate): Flow<List<Activity>> {
        return transactionDao.getTransactionsInPeriod(startDate, endDate).map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun insertTransaction(transaction: Activity): Long {
        return transactionDao.insertTransaction(transaction.toEntity())
    }

    override suspend fun insertTransactions(transactions: List<Activity>) {
        // Batch insert without per-transaction logging for performance
        if (transactions.isNotEmpty()) {
            println("[TransactionRepository] Batch inserting ${transactions.size} transactions")
            transactionDao.insertTransactions(transactions.map { it.toEntity() })
            println("[TransactionRepository] Batch insert completed")
        }
    }

    override suspend fun updateTransaction(transaction: Activity) {
        transactionDao.updateTransaction(transaction.toEntity())
    }

    override suspend fun findExistingTransaction(
        accountId: Long,
        date: LocalDate,
        amount: Double,
        description: String
    ): Activity? {
        return transactionDao.findExistingTransaction(accountId, date, amount, description)?.toDomain()
    }

    override suspend fun getTransactionById(id: Long): Activity? {
        return transactionDao.getTransactionById(id)?.toDomain()
    }

    private fun TransactionEntity.toDomain(): Activity {
        return Activity(
            id = id,
            accountId = accountId,
            activityDate = txnDate,
            valueDate = valueDate,
            description = description,
            reference = reference,
            amount = amount,
            type = direction,
            categoryId = categoryId,
            tags = tags,
            billPhotoUri = billPhotoUri,
            notes = notes,
            balanceAfterTxn = balanceAfterTxn,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun Activity.toEntity(): TransactionEntity {
        return TransactionEntity(
            id = id,
            accountId = accountId,
            txnDate = activityDate,
            valueDate = valueDate,
            description = description,
            reference = reference,
            amount = amount,
            direction = type,
            categoryId = categoryId,
            tags = tags,
            billPhotoUri = billPhotoUri,
            notes = notes,
            balanceAfterTxn = balanceAfterTxn,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
    
    // Category methods
    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { entities -> 
            entities.map { it.toDomain() } 
        }
    }

    override suspend fun insertCategory(category: Category): Long {
        return categoryDao.insertCategory(category.toEntity())
    }
    
    override suspend fun incrementCategoryUsage(categoryId: Long) {
        categoryDao.incrementUsageCount(categoryId)
    }
    
    override suspend fun mergeCategories(sourceCategoryId: Long, targetCategoryId: Long) {
        // Update all transactions to use target category
        transactionDao.updateTransactionsCategory(sourceCategoryId, targetCategoryId)
        
        // Update learning rules
        learningRuleDao.updateRulesCategory(sourceCategoryId, targetCategoryId)
        
        // Add source usage count to target
        val sourceCategory = categoryDao.getCategoryById(sourceCategoryId)
        val targetCategory = categoryDao.getCategoryById(targetCategoryId)
        if (sourceCategory != null && targetCategory != null) {
            categoryDao.insertCategory(
                targetCategory.copy(usageCount = targetCategory.usageCount + sourceCategory.usageCount)
            )
        }
    }
    
    override suspend fun uncategorizeActivities(categoryId: Long) {
        // Move to uncategorized (ID 0 or null)
        transactionDao.updateTransactionsCategory(categoryId, 0L)
    }
    
    override suspend fun deleteCategory(categoryId: Long) {
        categoryDao.deleteCategory(categoryId)
    }
    
    override suspend fun updateCategory(category: Category) {
        categoryDao.insertCategory(category.toEntity())
    }
    
    // Learning rule methods
    override suspend fun insertLearningRule(rule: CategoryLearningRule) {
        learningRuleDao.insertRule(rule.toEntity())
    }
    
    override suspend fun updateLearningRule(rule: CategoryLearningRule) {
        learningRuleDao.updateRule(rule.toEntity())
    }
    
    override suspend fun findLearningRule(pattern: String): CategoryLearningRule? {
        return learningRuleDao.findRuleByMerchant(pattern)?.toDomain()
    }
    
    override suspend fun getAllLearningRules(): Flow<List<CategoryLearningRule>> {
        return learningRuleDao.getAllRules().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    // Recurring pattern methods (subscriptions)
    override suspend fun insertRecurringPattern(pattern: RecurringPattern) {
        // TODO: Create RecurringPatternEntity and DAO
        // For now, just log
        println("[Repository] Would insert recurring pattern: ${pattern.merchantName}")
    }
    
    override suspend fun updateRecurringPattern(pattern: RecurringPattern) {
        // TODO: Implement when RecurringPatternDao exists
        println("[Repository] Would update recurring pattern: ${pattern.merchantName}")
    }
    
    override suspend fun findRecurringPattern(merchantName: String): RecurringPattern? {
        // TODO: Implement when RecurringPatternDao exists
        return null
    }
    
    override suspend fun getUnconfirmedSubscriptions(): List<SubscriptionDetectionCard> {
        // TODO: Implement when RecurringPatternDao exists
        return emptyList()
    }
    
    override suspend fun confirmSubscription(patternId: Long) {
        // TODO: Implement when RecurringPatternDao exists
        println("[Repository] Would confirm subscription: $patternId")
    }
    
    override suspend fun dismissSubscription(patternId: Long) {
        // TODO: Implement when RecurringPatternDao exists
        println("[Repository] Would dismiss subscription: $patternId")
    }
    
    // Daily Pulse calculation methods
    override suspend fun getMonthlyIncome(): Double {
        val startOfMonth = LocalDate.now().withDayOfMonth(1)
        val endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth())
        
        return transactionDao.getTransactionsInPeriod(startOfMonth, endOfMonth)
            .first()
            .filter { it.direction == ActivityType.INCOME }
            .sumOf { it.amount }
    }
    
    override suspend fun getMonthlyFixedExpenses(): Double {
        // TODO: Sum confirmed subscriptions when RecurringPatternDao exists
        return 0.0
    }
    
    override suspend fun getTodayExpenses(): Double {
        val today = LocalDate.now()
        
        return transactionDao.getTransactionsInPeriod(today, today)
            .first()
            .filter { it.direction == ActivityType.EXPENSE }
            .sumOf { it.amount }
    }
    
    override suspend fun getRecentTransactions(limit: Int): Flow<List<Activity>> {
        return transactionDao.getRecentTransactions(limit).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    // Transaction deletion
    override suspend fun deleteTransaction(activityId: Long) {
        transactionDao.deleteTransaction(activityId)
    }
    
    // Performance optimization methods
    override suspend fun getAllTransactionsForDeduplication(accountId: Long): List<Activity> {
        return transactionDao.getAllTransactionsSync(accountId).map { it.toDomain() }
    }
    
    override suspend fun calculateAccountBalance(accountId: Long, initialBalance: Double): Double {
        return transactionDao.calculateBalance(accountId, initialBalance)
    }
    
    override suspend fun getLatestTransactionBalance(accountId: Long): Double? {
        // Get the most recent transaction that has a balance recorded
        val latestTransaction = transactionDao.getLatestTransactionWithBalance(accountId)
        return latestTransaction?.balanceAfterTxn?.takeIf { it > 0.0 }
    }
    
    // Conversion functions
    private fun CategoryEntity.toDomain(): Category {
        return Category(
            id = id,
            name = name,
            type = type,
            icon = icon,
            color = color,
            usageCount = usageCount,
            isUserDeletable = isUserDeletable,
            isHidden = isHidden,
            lastUsedAt = 0L // TODO: Add timestamp tracking in entity
        )
    }
    
    private fun Category.toEntity(): CategoryEntity {
        return CategoryEntity(
            id = id,
            name = name,
            type = type,
            icon = icon,
            color = color,
            usageCount = usageCount,
            isUserDeletable = isUserDeletable,
            isHidden = isHidden
        )
    }
    
    private fun LearningRuleEntity.toDomain(): CategoryLearningRule {
        return CategoryLearningRule(
            id = id,
            descriptionPattern = merchantPattern,
            categoryId = categoryId,
            confidenceScore = confidenceScore,
            usageCount = usageCount,
            createdAt = createdAt,
            lastAppliedAt = lastUsedAt,
            createdByUserCorrection = true
        )
    }
    
    private fun CategoryLearningRule.toEntity(): LearningRuleEntity {
        return LearningRuleEntity(
            id = id,
            merchantPattern = descriptionPattern,
            categoryId = categoryId,
            confidenceScore = confidenceScore,
            usageCount = usageCount,
            createdAt = createdAt ?: java.time.LocalDateTime.now(),
            lastUsedAt = lastAppliedAt ?: java.time.LocalDateTime.now()
        )
    }
}
