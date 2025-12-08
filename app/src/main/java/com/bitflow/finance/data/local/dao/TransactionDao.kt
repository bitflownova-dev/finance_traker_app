package com.bitflow.finance.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bitflow.finance.data.local.entity.TransactionEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE accountId = :accountId ORDER BY txnDate DESC")
    fun getTransactionsForAccount(accountId: Long): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions ORDER BY txnDate DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE txnDate BETWEEN :startDate AND :endDate ORDER BY txnDate DESC")
    fun getTransactionsInPeriod(startDate: LocalDate, endDate: LocalDate): Flow<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransaction(transaction: TransactionEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTransactions(transactions: List<TransactionEntity>)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)
    
    @Query("SELECT * FROM transactions WHERE accountId = :accountId AND txnDate = :date AND amount = :amount AND description = :description")
    suspend fun findExistingTransaction(accountId: Long, date: LocalDate, amount: Double, description: String): TransactionEntity?

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransactionById(id: Long): TransactionEntity?
    
    /**
     * Bulk update transactions to new category (for category merge/delete)
     */
    @Query("UPDATE transactions SET categoryId = :newCategoryId WHERE categoryId = :oldCategoryId")
    suspend fun updateTransactionsCategory(oldCategoryId: Long, newCategoryId: Long)
    
    /**
     * Get transactions by merchant for pattern detection
     */
    @Query("SELECT * FROM transactions WHERE merchantName = :merchantName ORDER BY txnDate DESC")
    suspend fun getTransactionsByMerchant(merchantName: String): List<TransactionEntity>
    
    /**
     * Find recurring patterns - transactions in last 3 months grouped by merchant
     */
    @Query("""
        SELECT * FROM transactions 
        WHERE txnDate >= :startDate 
        AND merchantName IS NOT NULL 
        ORDER BY merchantName, txnDate DESC
    """)
    suspend fun getTransactionsForSubscriptionDetection(startDate: LocalDate): List<TransactionEntity>
    
    /**
     * Get recent transactions (for home screen)
     */
    @Query("SELECT * FROM transactions ORDER BY txnDate DESC, createdAt DESC LIMIT :limit")
    fun getRecentTransactions(limit: Int = 5): Flow<List<TransactionEntity>>
    
    /**
     * Delete transaction by ID (with undo support)
     */
    @Query("DELETE FROM transactions WHERE id = :id")
    suspend fun deleteTransaction(id: Long)
    
    /**
     * Performance optimization: Get all transactions synchronously for deduplication
     */
    @Query("SELECT * FROM transactions WHERE accountId = :accountId")
    suspend fun getAllTransactionsSync(accountId: Long): List<TransactionEntity>
    
    /**
     * Performance optimization: Calculate balance directly in database
     */
    @Query("""
        SELECT :initialBalance + 
        COALESCE((SELECT SUM(amount) FROM transactions WHERE accountId = :accountId AND direction = 'INCOME'), 0) -
        COALESCE((SELECT SUM(amount) FROM transactions WHERE accountId = :accountId AND direction = 'EXPENSE'), 0)
    """)
    suspend fun calculateBalance(accountId: Long, initialBalance: Double): Double
    
    /**
     * Get the most recent transaction with a recorded balance
     */
    @Query("SELECT * FROM transactions WHERE accountId = :accountId ORDER BY txnDate DESC, createdAt DESC LIMIT 1")
    suspend fun getLatestTransactionWithBalance(accountId: Long): TransactionEntity?
}
