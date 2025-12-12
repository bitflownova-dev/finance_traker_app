package com.bitflow.finance.di

import com.bitflow.finance.data.repository.AccountRepositoryImpl
import com.bitflow.finance.data.repository.SettingsRepositoryImpl
import com.bitflow.finance.data.repository.TransactionRepositoryImpl
import com.bitflow.finance.domain.repository.AccountRepository
import com.bitflow.finance.domain.repository.SettingsRepository
import com.bitflow.finance.domain.repository.TransactionRepository
import com.bitflow.finance.data.repository.AuthRepositoryImpl
import com.bitflow.finance.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAccountRepository(
        accountRepositoryImpl: AccountRepositoryImpl
    ): AccountRepository

    @Binds
    abstract fun bindTransactionRepository(
        transactionRepositoryImpl: TransactionRepositoryImpl
    ): TransactionRepository

    @Binds
    abstract fun bindSettingsRepository(
        settingsRepositoryImpl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}
