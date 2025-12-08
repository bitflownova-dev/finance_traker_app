package com.bitflow.finance.di

import android.content.Context
import androidx.room.Room
import com.bitflow.finance.data.local.AppDatabase
import com.bitflow.finance.data.local.dao.AccountDao
import com.bitflow.finance.data.local.dao.CategoryDao
import com.bitflow.finance.data.local.dao.InvoiceDao
import com.bitflow.finance.data.local.dao.LearningRuleDao
import com.bitflow.finance.data.local.dao.TransactionDao
import com.bitflow.finance.data.parser.UniversalStatementParser
import com.bitflow.finance.data.parser.StatementParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "finance_app.db"
        )
        .addMigrations(
            AppDatabase.MIGRATION_1_2, 
            AppDatabase.MIGRATION_2_3,
            AppDatabase.MIGRATION_3_4,
            AppDatabase.MIGRATION_4_5,
            AppDatabase.MIGRATION_5_6,
            AppDatabase.MIGRATION_6_7
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideAccountDao(database: AppDatabase): AccountDao = database.accountDao()

    @Provides
    fun provideTransactionDao(database: AppDatabase): TransactionDao = database.transactionDao()

    @Provides
    fun provideCategoryDao(database: AppDatabase): CategoryDao = database.categoryDao()

    @Provides
    fun provideLearningRuleDao(database: AppDatabase): LearningRuleDao = database.learningRuleDao()

    @Provides
    fun provideInvoiceDao(database: AppDatabase): InvoiceDao = database.invoiceDao()

    @Provides
    fun provideStatementParser(@ApplicationContext context: Context): StatementParser {
        val parser = UniversalStatementParser()
        parser.initialize(context)
        return parser
    }
}
