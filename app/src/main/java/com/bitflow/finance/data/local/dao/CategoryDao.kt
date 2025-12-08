package com.bitflow.finance.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bitflow.finance.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories WHERE isHidden = 0 ORDER BY usageCount DESC, name ASC")
    fun getAllCategories(): Flow<List<CategoryEntity>>
    
    @Query("SELECT * FROM categories WHERE isHidden = 0 ORDER BY usageCount DESC LIMIT :limit")
    fun getTopCategories(limit: Int = 8): Flow<List<CategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity): Long
    
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategories(categories: List<CategoryEntity>)
    
    @Query("UPDATE categories SET usageCount = usageCount + 1 WHERE id = :categoryId")
    suspend fun incrementUsageCount(categoryId: Long)
    
    @Query("UPDATE categories SET isHidden = :isHidden WHERE id = :categoryId")
    suspend fun updateVisibility(categoryId: Long, isHidden: Boolean)
    
    @Query("DELETE FROM categories WHERE id = :categoryId AND isUserDeletable = 1")
    suspend fun deleteCategory(categoryId: Long)
    
    @Query("SELECT * FROM categories WHERE id = :categoryId")
    suspend fun getCategoryById(categoryId: Long): CategoryEntity?
}
