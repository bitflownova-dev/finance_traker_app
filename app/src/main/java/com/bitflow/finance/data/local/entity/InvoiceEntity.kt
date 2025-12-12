package com.bitflow.finance.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoices")
data class InvoiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: String, // Owner of this invoice
    val invoiceNumber: String,
    val clientName: String,
    val clientAddress: String = "",
    val date: Long, // Timestamp
    val dueDate: Long = 0,
    val itemsJson: String = "[]",
    val taxRate: Double = 0.0,
    val amount: Double,
    val isPaid: Boolean = false,
    val pdfPath: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
