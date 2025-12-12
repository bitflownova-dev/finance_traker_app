package com.bitflow.finance.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bitflow.finance.data.local.entity.InvoiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {
    @Query("SELECT * FROM invoices WHERE userId = :userId ORDER BY date DESC")
    fun getAllInvoices(userId: String): Flow<List<InvoiceEntity>>

    @Query("SELECT * FROM invoices WHERE id = :id AND userId = :userId")
    suspend fun getInvoiceById(id: Long, userId: String): InvoiceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInvoice(invoice: InvoiceEntity): Long

    @androidx.room.Update
    suspend fun updateInvoice(invoice: InvoiceEntity)

    @Query("DELETE FROM invoices WHERE id = :id AND userId = :userId")
    suspend fun deleteInvoice(id: Long, userId: String)
}
