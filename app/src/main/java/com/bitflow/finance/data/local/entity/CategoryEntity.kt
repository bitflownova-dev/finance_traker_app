package com.bitflow.finance.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bitflow.finance.domain.model.CategoryType

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: CategoryType,
    val icon: String,
    val color: Int,
    val usageCount: Int = 0, // Track frequency for smart sorting
    val isUserDeletable: Boolean = true, // Allow deletion of custom categories
    val isHidden: Boolean = false // Support hiding without deleting
)
