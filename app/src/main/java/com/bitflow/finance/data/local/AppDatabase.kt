package com.bitflow.finance.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bitflow.finance.data.local.dao.AccountDao
import com.bitflow.finance.data.local.dao.CategoryDao
import com.bitflow.finance.data.local.dao.LearningRuleDao
import com.bitflow.finance.data.local.dao.TransactionDao
import com.bitflow.finance.data.local.dao.InvoiceDao
import com.bitflow.finance.data.local.entity.AccountEntity
import com.bitflow.finance.data.local.entity.CategoryEntity
import com.bitflow.finance.data.local.entity.LearningRuleEntity
import com.bitflow.finance.data.local.entity.TransactionEntity
import com.bitflow.finance.data.local.entity.InvoiceEntity

@Database(
    entities = [
        AccountEntity::class, 
        TransactionEntity::class, 
        CategoryEntity::class,
        LearningRuleEntity::class,
        InvoiceEntity::class
    ],
    version = 7,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun learningRuleDao(): LearningRuleDao
    abstract fun invoiceDao(): InvoiceDao
    
    companion object {
        val MIGRATION_1_2 = object : androidx.room.migration.Migration(1, 2) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                // Add currency column to accounts table with default value
                database.execSQL("ALTER TABLE accounts ADD COLUMN currency TEXT NOT NULL DEFAULT '₹'")
            }
        }
        
        val MIGRATION_2_3 = object : androidx.room.migration.Migration(2, 3) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                // Add new fields to categories table for usage tracking
                database.execSQL("ALTER TABLE categories ADD COLUMN usageCount INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE categories ADD COLUMN isUserDeletable INTEGER NOT NULL DEFAULT 1")
                database.execSQL("ALTER TABLE categories ADD COLUMN isHidden INTEGER NOT NULL DEFAULT 0")
                
                // Add new fields to transactions table for auto-learning
                database.execSQL("ALTER TABLE transactions ADD COLUMN merchantName TEXT")
                database.execSQL("ALTER TABLE transactions ADD COLUMN isAutoCategorized INTEGER NOT NULL DEFAULT 0")
                
                // Create learning_rules table for silent auto-categorization
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS learning_rules (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        merchantPattern TEXT NOT NULL,
                        categoryId INTEGER NOT NULL,
                        confidenceScore REAL NOT NULL,
                        usageCount INTEGER NOT NULL,
                        createdAt TEXT NOT NULL,
                        lastUsedAt TEXT NOT NULL
                    )
                """)
                
                // Create index for fast merchant lookup
                database.execSQL("CREATE INDEX IF NOT EXISTS index_learning_rules_merchant ON learning_rules(merchantPattern)")
            }
        }
        
        val MIGRATION_3_4 = object : androidx.room.migration.Migration(3, 4) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                // Add indices for faster queries and deduplication
                database.execSQL("CREATE INDEX IF NOT EXISTS idx_account_date ON transactions(accountId, txnDate)")
                database.execSQL("CREATE INDEX IF NOT EXISTS idx_txn_date ON transactions(txnDate)")
                database.execSQL("CREATE INDEX IF NOT EXISTS idx_dedup ON transactions(accountId, txnDate, amount, description)")
            }
        }
        
        val MIGRATION_4_5 = object : androidx.room.migration.Migration(4, 5) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                // Add balanceAfterTxn column to transactions table for statement-based balance
                // Check if column already exists to handle partial migration scenarios
                val cursor = database.query("PRAGMA table_info(transactions)")
                var columnExists = false
                while (cursor.moveToNext()) {
                    val columnName = cursor.getString(cursor.getColumnIndex("name"))
                    if (columnName == "balanceAfterTxn") {
                        columnExists = true
                        break
                    }
                }
                cursor.close()
                
                if (!columnExists) {
                    database.execSQL("ALTER TABLE transactions ADD COLUMN balanceAfterTxn REAL")
                }
            }
        }

        val MIGRATION_5_6 = object : androidx.room.migration.Migration(5, 6) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS invoices (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        invoiceNumber TEXT NOT NULL,
                        clientName TEXT NOT NULL,
                        date INTEGER NOT NULL,
                        amount REAL NOT NULL,
                        pdfPath TEXT,
                        createdAt INTEGER NOT NULL
                    )
                """)
            }
        }

        val MIGRATION_6_7 = object : androidx.room.migration.Migration(6, 7) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE invoices ADD COLUMN clientAddress TEXT NOT NULL DEFAULT ''")
                database.execSQL("ALTER TABLE invoices ADD COLUMN dueDate INTEGER NOT NULL DEFAULT 0")
                database.execSQL("ALTER TABLE invoices ADD COLUMN itemsJson TEXT NOT NULL DEFAULT '[]'")
                database.execSQL("ALTER TABLE invoices ADD COLUMN taxRate REAL NOT NULL DEFAULT 0.0")
                database.execSQL("ALTER TABLE invoices ADD COLUMN isPaid INTEGER NOT NULL DEFAULT 0")
            }
        }
    }
}
