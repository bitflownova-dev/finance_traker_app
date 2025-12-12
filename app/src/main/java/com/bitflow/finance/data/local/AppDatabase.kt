package com.bitflow.finance.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bitflow.finance.data.local.dao.AccountDao
import com.bitflow.finance.data.local.dao.CategoryDao
import com.bitflow.finance.data.local.dao.LearningRuleDao
import com.bitflow.finance.data.local.dao.TransactionDao
import com.bitflow.finance.data.local.dao.InvoiceDao
import com.bitflow.finance.data.local.dao.UserAccountDao
import com.bitflow.finance.data.local.dao.FriendDao
import com.bitflow.finance.data.local.dao.SplitDao
import com.bitflow.finance.data.local.entity.AccountEntity
import com.bitflow.finance.data.local.entity.CategoryEntity
import com.bitflow.finance.data.local.entity.LearningRuleEntity
import com.bitflow.finance.data.local.entity.TransactionEntity
import com.bitflow.finance.data.local.entity.InvoiceEntity
import com.bitflow.finance.data.local.entity.UserAccountEntity
import com.bitflow.finance.data.local.entity.FriendEntity
import com.bitflow.finance.data.local.entity.SplitGroupEntity
import com.bitflow.finance.data.local.entity.SplitGroupMemberEntity
import com.bitflow.finance.data.local.entity.SplitExpenseEntity
import com.bitflow.finance.data.local.entity.SplitExpenseShareEntity

@Database(
    entities = [
        AccountEntity::class, 
        TransactionEntity::class, 
        CategoryEntity::class,
        LearningRuleEntity::class,
        InvoiceEntity::class,
        UserAccountEntity::class,
        FriendEntity::class,
        SplitGroupEntity::class,
        SplitGroupMemberEntity::class,
        SplitExpenseEntity::class,
        SplitExpenseShareEntity::class
    ],
    version = 10,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
    abstract fun learningRuleDao(): LearningRuleDao
    abstract fun invoiceDao(): InvoiceDao
    abstract fun userAccountDao(): UserAccountDao
    abstract fun friendDao(): FriendDao
    abstract fun splitDao(): SplitDao
    
    companion object {
        // Built-in Indian expense categories - accessible to all users (userId = NULL)
        val BUILT_IN_CATEGORIES = listOf(
            // Food & Dining
            Triple("Groceries", "🛒", "#4CAF50"),
            Triple("Vegetables", "🥬", "#66BB6A"),
            Triple("Fruits", "🍎", "#FF6B6B"),
            Triple("Milk & Dairy", "🥛", "#FFFFFF"),
            Triple("Eating Out", "🍽️", "#FF5722"),
            Triple("Chai/Coffee", "☕", "#795548"),
            Triple("Street Food", "🍲", "#FFA726"),
            Triple("Sweets", "🍬", "#E91E63"),
            
            // Transportation
            Triple("Fuel/Petrol", "⛽", "#F44336"),
            Triple("Auto/Taxi", "🚕", "#FFC107"),
            Triple("Bus/Metro", "🚌", "#9C27B0"),
            Triple("Train", "🚆", "#3F51B5"),
            Triple("Two-Wheeler", "🏍️", "#FF9800"),
            
            // Utilities & Bills
            Triple("Electricity", "💡", "#FFEB3B"),
            Triple("Water Bill", "💧", "#2196F3"),
            Triple("Gas Cylinder", "🔥", "#FF5722"),
            Triple("Mobile Recharge", "📱", "#00BCD4"),
            Triple("Internet/WiFi", "📡", "#009688"),
            Triple("DTH/Cable", "📺", "#673AB7"),
            Triple("Rent", "🏠", "#8D6E63"),
            
            // Shopping & Personal
            Triple("Clothing", "👕", "#E91E63"),
            Triple("Footwear", "👟", "#9C27B0"),
            Triple("Personal Care", "💄", "#F06292"),
            Triple("Salon/Grooming", "💇", "#BA68C8"),
            Triple("Shopping", "🛍️", "#AB47BC"),
            
            // Health & Medical
            Triple("Medicine", "💊", "#00BCD4"),
            Triple("Doctor Visit", "🩺", "#26C6DA"),
            Triple("Medical Tests", "🏥", "#4DD0E1"),
            
            // Education
            Triple("School/College", "🎓", "#3F51B5"),
            Triple("Books/Stationery", "📚", "#5C6BC0"),
            Triple("Tuition", "📖", "#7E57C2"),
            
            // Entertainment
            Triple("Movies", "🎬", "#F44336"),
            Triple("Streaming", "📱", "#E91E63"),
            Triple("Games", "🎮", "#9C27B0"),
            
            // Financial
            Triple("UPI Transfer", "💸", "#607D8B"),
            Triple("Savings", "💰", "#4CAF50"),
            Triple("Investment", "📈", "#8BC34A"),
            Triple("EMI", "💳", "#FF9800"),
            Triple("Insurance", "🛡️", "#009688"),
            
            // Others
            Triple("Gifts", "🎁", "#E91E63"),
            Triple("Donations", "🙏", "#FF9800"),
            Triple("Pet Care", "🐕", "#8D6E63"),
            Triple("Home Maintenance", "🔧", "#607D8B"),
            Triple("Other", "📋", "#9E9E9E")
        )
        
        fun insertBuiltInCategories(database: androidx.sqlite.db.SupportSQLiteDatabase) {
            for ((name, icon, color) in BUILT_IN_CATEGORIES) {
                try {
                    // Insert with NULL userId (built-in category accessible to all users)
                    database.execSQL("INSERT OR IGNORE INTO categories (name, icon, color, type, usageCount, isUserDeletable, isHidden, userId) VALUES ('$name', '$icon', '$color', 'EXPENSE', 0, 1, 0, NULL)")
                } catch (e: Exception) {
                    // Fallback if type is stored as integer
                    try {
                        database.execSQL("INSERT OR IGNORE INTO categories (name, icon, color, type, usageCount, isUserDeletable, isHidden, userId) VALUES ('$name', '$icon', '$color', 1, 0, 1, 0, NULL)")
                    } catch (e2: Exception) {
                        // Ignore errors
                    }
                }
            }
        }
        
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
                // Check if column exists before adding to avoid duplicate column error
                val cursor = database.query("PRAGMA table_info(invoices)")
                var hasClientAddress = false
                var hasDueDate = false
                var hasItemsJson = false
                var hasTaxRate = false
                var hasIsPaid = false

                while (cursor.moveToNext()) {
                    val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                    when (name) {
                        "clientAddress" -> hasClientAddress = true
                        "dueDate" -> hasDueDate = true
                        "itemsJson" -> hasItemsJson = true
                        "taxRate" -> hasTaxRate = true
                        "isPaid" -> hasIsPaid = true
                    }
                }
                cursor.close()

                if (!hasClientAddress) database.execSQL("ALTER TABLE invoices ADD COLUMN clientAddress TEXT NOT NULL DEFAULT ''")
                if (!hasDueDate) database.execSQL("ALTER TABLE invoices ADD COLUMN dueDate INTEGER NOT NULL DEFAULT 0")
                if (!hasItemsJson) database.execSQL("ALTER TABLE invoices ADD COLUMN itemsJson TEXT NOT NULL DEFAULT '[]'")
                if (!hasTaxRate) database.execSQL("ALTER TABLE invoices ADD COLUMN taxRate REAL NOT NULL DEFAULT 0.0")
                if (!hasIsPaid) database.execSQL("ALTER TABLE invoices ADD COLUMN isPaid INTEGER NOT NULL DEFAULT 0")
            }
        }

        val MIGRATION_7_8 = object : androidx.room.migration.Migration(7, 8) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                // Insert built-in Indian categories
                insertBuiltInCategories(database)
            }
        }

        val MIGRATION_8_9 = object : androidx.room.migration.Migration(8, 9) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                // Add userId column to all tables for data isolation between users
                
                // Check and add userId to accounts table if not exists
                var cursor = database.query("PRAGMA table_info(accounts)")
                var hasUserId = false
                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("name")) == "userId") {
                        hasUserId = true
                        break
                    }
                }
                cursor.close()
                if (!hasUserId) {
                    database.execSQL("ALTER TABLE accounts ADD COLUMN userId TEXT NOT NULL DEFAULT 'default_user'")
                }
                
                // Check and add userId to transactions table if not exists
                cursor = database.query("PRAGMA table_info(transactions)")
                hasUserId = false
                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("name")) == "userId") {
                        hasUserId = true
                        break
                    }
                }
                cursor.close()
                if (!hasUserId) {
                    database.execSQL("ALTER TABLE transactions ADD COLUMN userId TEXT NOT NULL DEFAULT 'default_user'")
                }
                
                // Check and add userId to categories table if not exists (nullable)
                cursor = database.query("PRAGMA table_info(categories)")
                hasUserId = false
                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("name")) == "userId") {
                        hasUserId = true
                        break
                    }
                }
                cursor.close()
                if (!hasUserId) {
                    database.execSQL("ALTER TABLE categories ADD COLUMN userId TEXT")
                }
                
                // Check and add userId to learning_rules table if not exists
                cursor = database.query("PRAGMA table_info(learning_rules)")
                hasUserId = false
                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("name")) == "userId") {
                        hasUserId = true
                        break
                    }
                }
                cursor.close()
                if (!hasUserId) {
                    database.execSQL("ALTER TABLE learning_rules ADD COLUMN userId TEXT NOT NULL DEFAULT 'default_user'")
                }
                
                // Check and add userId to invoices table if not exists
                cursor = database.query("PRAGMA table_info(invoices)")
                hasUserId = false
                while (cursor.moveToNext()) {
                    if (cursor.getString(cursor.getColumnIndex("name")) == "userId") {
                        hasUserId = true
                        break
                    }
                }
                cursor.close()
                if (!hasUserId) {
                    database.execSQL("ALTER TABLE invoices ADD COLUMN userId TEXT NOT NULL DEFAULT 'default_user'")
                }
                
                // Drop old indices and create new ones with userId
                database.execSQL("DROP INDEX IF EXISTS idx_account_date")
                database.execSQL("DROP INDEX IF EXISTS idx_txn_date")
                database.execSQL("CREATE INDEX IF NOT EXISTS idx_user_account_date ON transactions(userId, accountId, txnDate)")
                database.execSQL("CREATE INDEX IF NOT EXISTS idx_user_date ON transactions(userId, txnDate)")
                
                // Drop ALL old learning_rules indices and create the correct one
                database.execSQL("DROP INDEX IF EXISTS index_learning_rules_merchantPattern")
                database.execSQL("DROP INDEX IF EXISTS index_learning_rules_merchant")
                database.execSQL("DROP INDEX IF EXISTS index_learning_rules_user_merchant")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_learning_rules_userId_merchantPattern ON learning_rules(userId, merchantPattern)")
            }
        }

        val MIGRATION_9_10 = object : androidx.room.migration.Migration(9, 10) {
            override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
                // Create user_accounts table for multi-user support with username + security question
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS user_accounts (
                        userId TEXT PRIMARY KEY NOT NULL,
                        username TEXT NOT NULL,
                        displayName TEXT NOT NULL,
                        passwordHash TEXT NOT NULL,
                        securityQuestion TEXT NOT NULL,
                        securityAnswerHash TEXT NOT NULL,
                        createdAt INTEGER NOT NULL,
                        lastLoginAt INTEGER NOT NULL,
                        isActive INTEGER NOT NULL DEFAULT 1
                    )
                """)
                // Username must be unique
                database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_user_accounts_username ON user_accounts(username)")
                
                // Create friends table - UUID-only, no nickname
                // Users add friends by UUID only, display name from UserAccountEntity
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS friends (
                        userId TEXT NOT NULL,
                        friendUserId TEXT NOT NULL,
                        addedAt INTEGER NOT NULL,
                        PRIMARY KEY(userId, friendUserId)
                    )
                """)
                database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_friends_userId_friendUserId ON friends(userId, friendUserId)")
                
                // Create split_groups table for bill splitting groups
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS split_groups (
                        groupId TEXT PRIMARY KEY NOT NULL,
                        groupName TEXT NOT NULL,
                        description TEXT,
                        createdBy TEXT NOT NULL,
                        createdAt INTEGER NOT NULL,
                        isActive INTEGER NOT NULL DEFAULT 1
                    )
                """)
                database.execSQL("CREATE INDEX IF NOT EXISTS index_split_groups_createdBy ON split_groups(createdBy)")
                
                // Create split_group_members table for group membership
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS split_group_members (
                        groupId TEXT NOT NULL,
                        userId TEXT NOT NULL,
                        addedAt INTEGER NOT NULL,
                        isActive INTEGER NOT NULL DEFAULT 1,
                        PRIMARY KEY(groupId, userId)
                    )
                """)
                database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_split_group_members_groupId_userId ON split_group_members(groupId, userId)")
                
                // Create split_expenses table for individual expenses
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS split_expenses (
                        expenseId TEXT PRIMARY KEY NOT NULL,
                        groupId TEXT NOT NULL,
                        description TEXT NOT NULL,
                        totalAmount REAL NOT NULL,
                        paidBy TEXT NOT NULL,
                        expenseDate INTEGER NOT NULL,
                        createdAt INTEGER NOT NULL,
                        isSettled INTEGER NOT NULL DEFAULT 0
                    )
                """)
                database.execSQL("CREATE INDEX IF NOT EXISTS index_split_expenses_groupId ON split_expenses(groupId)")
                
                // Create split_expense_shares table for individual shares
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS split_expense_shares (
                        expenseId TEXT NOT NULL,
                        userId TEXT NOT NULL,
                        shareAmount REAL NOT NULL,
                        isPaid INTEGER NOT NULL DEFAULT 0,
                        paidAt INTEGER,
                        PRIMARY KEY(expenseId, userId)
                    )
                """)
                database.execSQL("CREATE INDEX IF NOT EXISTS index_split_expense_shares_expenseId ON split_expense_shares(expenseId)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_split_expense_shares_userId ON split_expense_shares(userId)")
                
                // Ensure built-in Indian categories exist for all users
                insertBuiltInCategories(database)
            }
        }
    }
}
