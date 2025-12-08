package com.bitflow.finance.data.repository

import com.bitflow.finance.data.local.dao.AccountDao
import com.bitflow.finance.data.local.entity.AccountEntity
import com.bitflow.finance.domain.model.Account
import com.bitflow.finance.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val dao: AccountDao
) : AccountRepository {

    override fun getAllAccounts(): Flow<List<Account>> {
        return dao.getAllAccounts().map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun getAccountById(id: Long): Account? {
        return dao.getAccountById(id)?.toDomain()
    }

    override suspend fun insertAccount(account: Account): Long {
        return dao.insertAccount(account.toEntity())
    }

    override suspend fun updateAccount(account: Account) {
        dao.updateAccount(account.toEntity())
    }
    
    override suspend fun updateBalance(accountId: Long, newBalance: Double) {
        dao.updateBalance(accountId, newBalance)
    }

    private fun AccountEntity.toDomain(): Account {
        return Account(
            id = id,
            name = name,
            type = type,
            color = color,
            icon = icon,
            initialBalance = initialBalance,
            currentBalance = currentBalance,
            currency = currency
        )
    }

    private fun Account.toEntity(): AccountEntity {
        return AccountEntity(
            id = id,
            name = name,
            type = type,
            color = color,
            icon = icon,
            initialBalance = initialBalance,
            currentBalance = currentBalance,
            currency = currency
        )
    }
}
