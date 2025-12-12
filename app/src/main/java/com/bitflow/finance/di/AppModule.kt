package com.bitflow.finance.di

import android.content.Context
import androidx.room.Room
import com.bitflow.finance.data.local.AppDatabase
import com.bitflow.finance.data.local.dao.AccountDao
import com.bitflow.finance.data.local.dao.CategoryDao
import com.bitflow.finance.data.local.dao.InvoiceDao
import com.bitflow.finance.data.local.dao.LearningRuleDao
import com.bitflow.finance.data.local.dao.TransactionDao
import com.bitflow.finance.data.local.dao.UserAccountDao
import com.bitflow.finance.data.local.dao.FriendDao
import com.bitflow.finance.data.local.dao.SplitDao
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
            AppDatabase.MIGRATION_6_7,
            AppDatabase.MIGRATION_7_8,
            AppDatabase.MIGRATION_8_9,
            AppDatabase.MIGRATION_9_10
        )
        .addCallback(object : androidx.room.RoomDatabase.Callback() {
            override fun onCreate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
                super.onCreate(db)
                // Insert built-in categories on fresh database creation
                AppDatabase.insertBuiltInCategories(db)
            }
        })
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
    fun provideUserAccountDao(database: AppDatabase): UserAccountDao = database.userAccountDao()

    @Provides
    fun provideFriendDao(database: AppDatabase): FriendDao = database.friendDao()

    @Provides
    fun provideSplitDao(database: AppDatabase): SplitDao = database.splitDao()

    @Provides
    fun provideStatementParser(@ApplicationContext context: Context): StatementParser {
        val parser = UniversalStatementParser()
        parser.initialize(context)
        return parser
    }
}
