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
import com.bitflow.finance.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val categoryDao: CategoryDao,
    private val learningRuleDao: LearningRuleDao,
    private val authRepository: AuthRepository
) : TransactionRepository {

    override fun getAllTransactions(): Flow<List<Activity>> {
        return authRepository.currentUserId.flatMapLatest { userId ->
            transactionDao.getAllTransactions(userId).map { entities -> entities.map { it.toDomain() } }
        }
    }

    override fun getTransactionsForAccount(accountId: Long): Flow<List<Activity>> {
        return authRepository.currentUserId.flatMapLatest { userId ->
            transactionDao.getTransactionsForAccount(accountId, userId).map { entities -> entities.map { it.toDomain() } }
        }
    }

    override fun getTransactionsInPeriod(startDate: LocalDate, endDate: LocalDate): Flow<List<Activity>> {
        return authRepository.currentUserId.flatMapLatest { userId ->
            transactionDao.getTransactionsInPeriod(startDate, endDate, userId).map { entities -> entities.map { it.toDomain() } }
        }
    }

    override suspend fun insertTransaction(transaction: Activity): Long {
        val userId = authRepository.currentUserId.first()
        return transactionDao.insertTransaction(transaction.toEntity(userId))
    }

    override suspend fun insertTransactions(transactions: List<Activity>) {
        // Batch insert without per-transaction logging for performance
        if (transactions.isNotEmpty()) {
            val userId = authRepository.currentUserId.first()
            println("[TransactionRepository] Batch inserting ${transactions.size} transactions")
            transactionDao.insertTransactions(transactions.map { it.toEntity(userId) })
            println("[TransactionRepository] Batch insert completed")
        }
    }

    override suspend fun updateTransaction(transaction: Activity) {
        val userId = authRepository.currentUserId.first()
        transactionDao.updateTransaction(transaction.toEntity(userId))
    }

    override suspend fun findExistingTransaction(
        accountId: Long,
        date: LocalDate,
        amount: Double,
        description: String
    ): Activity? {
        val userId = authRepository.currentUserId.first()
        return transactionDao.findExistingTransaction(accountId, date, amount, description, userId)?.toDomain()
    }

    override suspend fun getTransactionById(id: Long): Activity? {
        val userId = authRepository.currentUserId.first()
        return transactionDao.getTransactionById(id, userId)?.toDomain()
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

    private fun Activity.toEntity(userId: String): TransactionEntity {
        return TransactionEntity(
            id = id,
            userId = userId,
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
        return authRepository.currentUserId.flatMapLatest { userId ->
            categoryDao.getAllCategories(userId).map { entities -> 
                entities.map { it.toDomain() } 
            }
        }
    }

    override suspend fun insertCategory(category: Category): Long {
        val userId = authRepository.currentUserId.first()
        return categoryDao.insertCategory(category.toEntity(userId))
    }
    
    override suspend fun incrementCategoryUsage(categoryId: Long) {
        categoryDao.incrementUsageCount(categoryId)
    }
    
    override suspend fun mergeCategories(sourceCategoryId: Long, targetCategoryId: Long) {
        val userId = authRepository.currentUserId.first()
        // Update all transactions to use target category
        transactionDao.updateTransactionsCategory(sourceCategoryId, targetCategoryId, userId)
        
        // Update learning rules
        learningRuleDao.updateRulesCategory(sourceCategoryId, targetCategoryId, userId)
        
        // Add source usage count to target
        val sourceCategory = categoryDao.getCategoryById(sourceCategoryId, userId)
        val targetCategory = categoryDao.getCategoryById(targetCategoryId, userId)
        if (sourceCategory != null && targetCategory != null) {
            categoryDao.insertCategory(
                targetCategory.copy(usageCount = targetCategory.usageCount + sourceCategory.usageCount)
            )
        }
    }
    
    override suspend fun uncategorizeActivities(categoryId: Long) {
        val userId = authRepository.currentUserId.first()
        // Move to uncategorized (ID 0 or null)
        transactionDao.updateTransactionsCategory(categoryId, 0L, userId)
    }
    
    override suspend fun deleteCategory(categoryId: Long) {
        val userId = authRepository.currentUserId.first()
        categoryDao.deleteCategory(categoryId, userId)
    }
    
    override suspend fun updateCategory(category: Category) {
        val userId = authRepository.currentUserId.first()
        categoryDao.insertCategory(category.toEntity(userId))
    }
    
    // Learning rule methods
    override suspend fun insertLearningRule(rule: CategoryLearningRule) {
        val userId = authRepository.currentUserId.first()
        learningRuleDao.insertRule(rule.toEntity(userId))
    }
    
    override suspend fun updateLearningRule(rule: CategoryLearningRule) {
        val userId = authRepository.currentUserId.first()
        learningRuleDao.updateRule(rule.toEntity(userId))
    }
    
    override suspend fun findLearningRule(pattern: String): CategoryLearningRule? {
        val userId = authRepository.currentUserId.first()
        return learningRuleDao.findRuleByMerchant(pattern, userId)?.toDomain()
    }
    
    override suspend fun getAllLearningRules(): Flow<List<CategoryLearningRule>> {
        return authRepository.currentUserId.flatMapLatest { userId ->
            learningRuleDao.getAllRules(userId).map { entities ->
                entities.map { it.toDomain() }
            }
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
        val userId = authRepository.currentUserId.first()
        val startOfMonth = LocalDate.now().withDayOfMonth(1)
        val endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth())
        
        return transactionDao.getTransactionsInPeriod(startOfMonth, endOfMonth, userId)
            .first()
            .filter { it.direction == ActivityType.INCOME }
            .sumOf { it.amount }
    }
    
    override suspend fun getMonthlyFixedExpenses(): Double {
        // TODO: Sum confirmed subscriptions when RecurringPatternDao exists
        return 0.0
    }
    
    override suspend fun getTodayExpenses(): Double {
        val userId = authRepository.currentUserId.first()
        val today = LocalDate.now()
        
        return transactionDao.getTransactionsInPeriod(today, today, userId)
            .first()
            .filter { it.direction == ActivityType.EXPENSE }
            .sumOf { it.amount }
    }
    
    override suspend fun getRecentTransactions(limit: Int): Flow<List<Activity>> {
        return authRepository.currentUserId.flatMapLatest { userId ->
            transactionDao.getRecentTransactions(limit, userId).map { entities ->
                entities.map { it.toDomain() }
            }
        }
    }
    
    // Transaction deletion
    override suspend fun deleteTransaction(activityId: Long) {
        val userId = authRepository.currentUserId.first()
        transactionDao.deleteTransaction(activityId, userId)
    }
    
    // Performance optimization methods
    override suspend fun getAllTransactionsForDeduplication(accountId: Long): List<Activity> {
        val userId = authRepository.currentUserId.first()
        return transactionDao.getAllTransactionsSync(accountId, userId).map { it.toDomain() }
    }
    
    override suspend fun calculateAccountBalance(accountId: Long, initialBalance: Double): Double {
        val userId = authRepository.currentUserId.first()
        return transactionDao.calculateBalance(accountId, initialBalance, userId)
    }
    
    override suspend fun getLatestTransactionBalance(accountId: Long): Double? {
        val userId = authRepository.currentUserId.first()
        // Get the most recent transaction that has a balance recorded
        val latestTransaction = transactionDao.getLatestTransactionWithBalance(accountId, userId)
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
    
    private fun Category.toEntity(userId: String?): CategoryEntity {
        return CategoryEntity(
            id = id,
            userId = userId,
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
    
    private fun CategoryLearningRule.toEntity(userId: String): LearningRuleEntity {
        return LearningRuleEntity(
            id = id,
            userId = userId,
            merchantPattern = descriptionPattern,
            categoryId = categoryId,
            confidenceScore = confidenceScore,
            usageCount = usageCount,
            createdAt = createdAt ?: java.time.LocalDateTime.now(),
            lastUsedAt = lastAppliedAt ?: java.time.LocalDateTime.now()
        )
    }
}
