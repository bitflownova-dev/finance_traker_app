package com.bitflow.finance.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.bitflow.finance.data.local.entity.LearningRuleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LearningRuleDao {
    
    /**
     * Find existing rule for a merchant pattern
     */
    @Query("SELECT * FROM learning_rules WHERE merchantPattern = :merchantPattern LIMIT 1")
    suspend fun findRuleByMerchant(merchantPattern: String): LearningRuleEntity?
    
    /**
     * Get all learning rules sorted by confidence and usage
     */
    @Query("SELECT * FROM learning_rules ORDER BY confidenceScore DESC, usageCount DESC")
    fun getAllRules(): Flow<List<LearningRuleEntity>>
    
    /**
     * Insert or update a learning rule
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRule(rule: LearningRuleEntity): Long
    
    /**
     * Update existing rule (for incrementing usage/confidence)
     */
    @Update
    suspend fun updateRule(rule: LearningRuleEntity)
    
    /**
     * Delete rules associated with a category (when category is deleted)
     */
    @Query("DELETE FROM learning_rules WHERE categoryId = :categoryId")
    suspend fun deleteRulesForCategory(categoryId: Long)
    
    /**
     * Update rules to new category (when merging categories)
     */
    @Query("UPDATE learning_rules SET categoryId = :newCategoryId WHERE categoryId = :oldCategoryId")
    suspend fun updateRulesCategory(oldCategoryId: Long, newCategoryId: Long)
    
    /**
     * Clear all learning data (reset feature)
     */
    @Query("DELETE FROM learning_rules")
    suspend fun clearAllRules()
}
