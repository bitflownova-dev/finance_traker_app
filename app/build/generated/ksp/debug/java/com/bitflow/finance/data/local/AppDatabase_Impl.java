package com.bitflow.finance.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.bitflow.finance.data.local.dao.AccountDao;
import com.bitflow.finance.data.local.dao.AccountDao_Impl;
import com.bitflow.finance.data.local.dao.CategoryDao;
import com.bitflow.finance.data.local.dao.CategoryDao_Impl;
import com.bitflow.finance.data.local.dao.InvoiceDao;
import com.bitflow.finance.data.local.dao.InvoiceDao_Impl;
import com.bitflow.finance.data.local.dao.LearningRuleDao;
import com.bitflow.finance.data.local.dao.LearningRuleDao_Impl;
import com.bitflow.finance.data.local.dao.TransactionDao;
import com.bitflow.finance.data.local.dao.TransactionDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile AccountDao _accountDao;

  private volatile TransactionDao _transactionDao;

  private volatile CategoryDao _categoryDao;

  private volatile LearningRuleDao _learningRuleDao;

  private volatile InvoiceDao _invoiceDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(7) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `accounts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `color` INTEGER NOT NULL, `icon` TEXT NOT NULL, `initialBalance` REAL NOT NULL, `currentBalance` REAL NOT NULL, `currency` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `transactions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `accountId` INTEGER NOT NULL, `txnDate` TEXT NOT NULL, `valueDate` TEXT, `description` TEXT NOT NULL, `reference` TEXT, `amount` REAL NOT NULL, `direction` TEXT NOT NULL, `categoryId` INTEGER, `merchantName` TEXT, `tags` TEXT NOT NULL, `billPhotoUri` TEXT, `notes` TEXT, `balanceAfterTxn` REAL, `isAutoCategorized` INTEGER NOT NULL, `createdAt` TEXT NOT NULL, `updatedAt` TEXT NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `idx_account_date` ON `transactions` (`accountId`, `txnDate`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `idx_txn_date` ON `transactions` (`txnDate`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `idx_dedup` ON `transactions` (`accountId`, `txnDate`, `amount`, `description`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `type` TEXT NOT NULL, `icon` TEXT NOT NULL, `color` INTEGER NOT NULL, `usageCount` INTEGER NOT NULL, `isUserDeletable` INTEGER NOT NULL, `isHidden` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `learning_rules` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `merchantPattern` TEXT NOT NULL, `categoryId` INTEGER NOT NULL, `confidenceScore` REAL NOT NULL, `usageCount` INTEGER NOT NULL, `createdAt` TEXT NOT NULL, `lastUsedAt` TEXT NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_learning_rules_merchantPattern` ON `learning_rules` (`merchantPattern`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `invoices` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `invoiceNumber` TEXT NOT NULL, `clientName` TEXT NOT NULL, `clientAddress` TEXT NOT NULL, `date` INTEGER NOT NULL, `dueDate` INTEGER NOT NULL, `itemsJson` TEXT NOT NULL, `taxRate` REAL NOT NULL, `amount` REAL NOT NULL, `isPaid` INTEGER NOT NULL, `pdfPath` TEXT, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c5d10fa4e84fb9aab43c6a86c1dbcb20')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `accounts`");
        db.execSQL("DROP TABLE IF EXISTS `transactions`");
        db.execSQL("DROP TABLE IF EXISTS `categories`");
        db.execSQL("DROP TABLE IF EXISTS `learning_rules`");
        db.execSQL("DROP TABLE IF EXISTS `invoices`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAccounts = new HashMap<String, TableInfo.Column>(8);
        _columnsAccounts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("color", new TableInfo.Column("color", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("icon", new TableInfo.Column("icon", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("initialBalance", new TableInfo.Column("initialBalance", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("currentBalance", new TableInfo.Column("currentBalance", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAccounts.put("currency", new TableInfo.Column("currency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAccounts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAccounts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAccounts = new TableInfo("accounts", _columnsAccounts, _foreignKeysAccounts, _indicesAccounts);
        final TableInfo _existingAccounts = TableInfo.read(db, "accounts");
        if (!_infoAccounts.equals(_existingAccounts)) {
          return new RoomOpenHelper.ValidationResult(false, "accounts(com.bitflow.finance.data.local.entity.AccountEntity).\n"
                  + " Expected:\n" + _infoAccounts + "\n"
                  + " Found:\n" + _existingAccounts);
        }
        final HashMap<String, TableInfo.Column> _columnsTransactions = new HashMap<String, TableInfo.Column>(17);
        _columnsTransactions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("accountId", new TableInfo.Column("accountId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("txnDate", new TableInfo.Column("txnDate", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("valueDate", new TableInfo.Column("valueDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("reference", new TableInfo.Column("reference", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("direction", new TableInfo.Column("direction", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("merchantName", new TableInfo.Column("merchantName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("tags", new TableInfo.Column("tags", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("billPhotoUri", new TableInfo.Column("billPhotoUri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("balanceAfterTxn", new TableInfo.Column("balanceAfterTxn", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("isAutoCategorized", new TableInfo.Column("isAutoCategorized", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("updatedAt", new TableInfo.Column("updatedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactions = new HashSet<TableInfo.Index>(3);
        _indicesTransactions.add(new TableInfo.Index("idx_account_date", false, Arrays.asList("accountId", "txnDate"), Arrays.asList("ASC", "ASC")));
        _indicesTransactions.add(new TableInfo.Index("idx_txn_date", false, Arrays.asList("txnDate"), Arrays.asList("ASC")));
        _indicesTransactions.add(new TableInfo.Index("idx_dedup", false, Arrays.asList("accountId", "txnDate", "amount", "description"), Arrays.asList("ASC", "ASC", "ASC", "ASC")));
        final TableInfo _infoTransactions = new TableInfo("transactions", _columnsTransactions, _foreignKeysTransactions, _indicesTransactions);
        final TableInfo _existingTransactions = TableInfo.read(db, "transactions");
        if (!_infoTransactions.equals(_existingTransactions)) {
          return new RoomOpenHelper.ValidationResult(false, "transactions(com.bitflow.finance.data.local.entity.TransactionEntity).\n"
                  + " Expected:\n" + _infoTransactions + "\n"
                  + " Found:\n" + _existingTransactions);
        }
        final HashMap<String, TableInfo.Column> _columnsCategories = new HashMap<String, TableInfo.Column>(8);
        _columnsCategories.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("icon", new TableInfo.Column("icon", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("color", new TableInfo.Column("color", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("usageCount", new TableInfo.Column("usageCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("isUserDeletable", new TableInfo.Column("isUserDeletable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("isHidden", new TableInfo.Column("isHidden", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategories = new TableInfo("categories", _columnsCategories, _foreignKeysCategories, _indicesCategories);
        final TableInfo _existingCategories = TableInfo.read(db, "categories");
        if (!_infoCategories.equals(_existingCategories)) {
          return new RoomOpenHelper.ValidationResult(false, "categories(com.bitflow.finance.data.local.entity.CategoryEntity).\n"
                  + " Expected:\n" + _infoCategories + "\n"
                  + " Found:\n" + _existingCategories);
        }
        final HashMap<String, TableInfo.Column> _columnsLearningRules = new HashMap<String, TableInfo.Column>(7);
        _columnsLearningRules.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRules.put("merchantPattern", new TableInfo.Column("merchantPattern", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRules.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRules.put("confidenceScore", new TableInfo.Column("confidenceScore", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRules.put("usageCount", new TableInfo.Column("usageCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRules.put("createdAt", new TableInfo.Column("createdAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLearningRules.put("lastUsedAt", new TableInfo.Column("lastUsedAt", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLearningRules = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLearningRules = new HashSet<TableInfo.Index>(1);
        _indicesLearningRules.add(new TableInfo.Index("index_learning_rules_merchantPattern", false, Arrays.asList("merchantPattern"), Arrays.asList("ASC")));
        final TableInfo _infoLearningRules = new TableInfo("learning_rules", _columnsLearningRules, _foreignKeysLearningRules, _indicesLearningRules);
        final TableInfo _existingLearningRules = TableInfo.read(db, "learning_rules");
        if (!_infoLearningRules.equals(_existingLearningRules)) {
          return new RoomOpenHelper.ValidationResult(false, "learning_rules(com.bitflow.finance.data.local.entity.LearningRuleEntity).\n"
                  + " Expected:\n" + _infoLearningRules + "\n"
                  + " Found:\n" + _existingLearningRules);
        }
        final HashMap<String, TableInfo.Column> _columnsInvoices = new HashMap<String, TableInfo.Column>(12);
        _columnsInvoices.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("invoiceNumber", new TableInfo.Column("invoiceNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("clientName", new TableInfo.Column("clientName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("clientAddress", new TableInfo.Column("clientAddress", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("dueDate", new TableInfo.Column("dueDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("itemsJson", new TableInfo.Column("itemsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("taxRate", new TableInfo.Column("taxRate", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("isPaid", new TableInfo.Column("isPaid", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("pdfPath", new TableInfo.Column("pdfPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvoices.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvoices = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvoices = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvoices = new TableInfo("invoices", _columnsInvoices, _foreignKeysInvoices, _indicesInvoices);
        final TableInfo _existingInvoices = TableInfo.read(db, "invoices");
        if (!_infoInvoices.equals(_existingInvoices)) {
          return new RoomOpenHelper.ValidationResult(false, "invoices(com.bitflow.finance.data.local.entity.InvoiceEntity).\n"
                  + " Expected:\n" + _infoInvoices + "\n"
                  + " Found:\n" + _existingInvoices);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "c5d10fa4e84fb9aab43c6a86c1dbcb20", "cf2cc011c97bb37dd4718735ee5e7967");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "accounts","transactions","categories","learning_rules","invoices");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `accounts`");
      _db.execSQL("DELETE FROM `transactions`");
      _db.execSQL("DELETE FROM `categories`");
      _db.execSQL("DELETE FROM `learning_rules`");
      _db.execSQL("DELETE FROM `invoices`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AccountDao.class, AccountDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransactionDao.class, TransactionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CategoryDao.class, CategoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LearningRuleDao.class, LearningRuleDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(InvoiceDao.class, InvoiceDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AccountDao accountDao() {
    if (_accountDao != null) {
      return _accountDao;
    } else {
      synchronized(this) {
        if(_accountDao == null) {
          _accountDao = new AccountDao_Impl(this);
        }
        return _accountDao;
      }
    }
  }

  @Override
  public TransactionDao transactionDao() {
    if (_transactionDao != null) {
      return _transactionDao;
    } else {
      synchronized(this) {
        if(_transactionDao == null) {
          _transactionDao = new TransactionDao_Impl(this);
        }
        return _transactionDao;
      }
    }
  }

  @Override
  public CategoryDao categoryDao() {
    if (_categoryDao != null) {
      return _categoryDao;
    } else {
      synchronized(this) {
        if(_categoryDao == null) {
          _categoryDao = new CategoryDao_Impl(this);
        }
        return _categoryDao;
      }
    }
  }

  @Override
  public LearningRuleDao learningRuleDao() {
    if (_learningRuleDao != null) {
      return _learningRuleDao;
    } else {
      synchronized(this) {
        if(_learningRuleDao == null) {
          _learningRuleDao = new LearningRuleDao_Impl(this);
        }
        return _learningRuleDao;
      }
    }
  }

  @Override
  public InvoiceDao invoiceDao() {
    if (_invoiceDao != null) {
      return _invoiceDao;
    } else {
      synchronized(this) {
        if(_invoiceDao == null) {
          _invoiceDao = new InvoiceDao_Impl(this);
        }
        return _invoiceDao;
      }
    }
  }
}
