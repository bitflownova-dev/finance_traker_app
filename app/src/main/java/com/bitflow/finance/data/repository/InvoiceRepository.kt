package com.bitflow.finance.data.repository

import com.bitflow.finance.data.local.dao.InvoiceDao
import com.bitflow.finance.data.local.entity.InvoiceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InvoiceRepository @Inject constructor(
    private val invoiceDao: InvoiceDao
) {
    fun getAllInvoices(): Flow<List<InvoiceEntity>> = invoiceDao.getAllInvoices()

    suspend fun getInvoiceById(id: Long): InvoiceEntity? = invoiceDao.getInvoiceById(id)

    suspend fun saveInvoice(invoice: InvoiceEntity): Long = invoiceDao.insertInvoice(invoice)

    suspend fun updateInvoice(invoice: InvoiceEntity) = invoiceDao.updateInvoice(invoice)

    suspend fun deleteInvoice(id: Long) = invoiceDao.deleteInvoice(id)
}
