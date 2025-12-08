package com.bitflow.finance.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.bitflow.finance.data.local.Converters;
import com.bitflow.finance.data.local.entity.TransactionEntity;
import com.bitflow.finance.domain.model.ActivityType;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransactionEntity> __insertionAdapterOfTransactionEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __updateAdapterOfTransactionEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateTransactionsCategory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTransaction;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionEntity = new EntityInsertionAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `transactions` (`id`,`accountId`,`txnDate`,`valueDate`,`description`,`reference`,`amount`,`direction`,`categoryId`,`merchantName`,`tags`,`billPhotoUri`,`notes`,`balanceAfterTxn`,`isAutoCategorized`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAccountId());
        final String _tmp = __converters.dateToString(entity.getTxnDate());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        final String _tmp_1 = __converters.dateToString(entity.getValueDate());
        if (_tmp_1 == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp_1);
        }
        statement.bindString(5, entity.getDescription());
        if (entity.getReference() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getReference());
        }
        statement.bindDouble(7, entity.getAmount());
        final String _tmp_2 = __converters.toTransactionDirection(entity.getDirection());
        statement.bindString(8, _tmp_2);
        if (entity.getCategoryId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getCategoryId());
        }
        if (entity.getMerchantName() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getMerchantName());
        }
        final String _tmp_3 = __converters.toStringList(entity.getTags());
        statement.bindString(11, _tmp_3);
        if (entity.getBillPhotoUri() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getBillPhotoUri());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getNotes());
        }
        if (entity.getBalanceAfterTxn() == null) {
          statement.bindNull(14);
        } else {
          statement.bindDouble(14, entity.getBalanceAfterTxn());
        }
        final int _tmp_4 = entity.isAutoCategorized() ? 1 : 0;
        statement.bindLong(15, _tmp_4);
        final String _tmp_5 = __converters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp_5 == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, _tmp_5);
        }
        final String _tmp_6 = __converters.dateToTimestamp(entity.getUpdatedAt());
        if (_tmp_6 == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, _tmp_6);
        }
      }
    };
    this.__updateAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `id` = ?,`accountId` = ?,`txnDate` = ?,`valueDate` = ?,`description` = ?,`reference` = ?,`amount` = ?,`direction` = ?,`categoryId` = ?,`merchantName` = ?,`tags` = ?,`billPhotoUri` = ?,`notes` = ?,`balanceAfterTxn` = ?,`isAutoCategorized` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getAccountId());
        final String _tmp = __converters.dateToString(entity.getTxnDate());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        final String _tmp_1 = __converters.dateToString(entity.getValueDate());
        if (_tmp_1 == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp_1);
        }
        statement.bindString(5, entity.getDescription());
        if (entity.getReference() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getReference());
        }
        statement.bindDouble(7, entity.getAmount());
        final String _tmp_2 = __converters.toTransactionDirection(entity.getDirection());
        statement.bindString(8, _tmp_2);
        if (entity.getCategoryId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getCategoryId());
        }
        if (entity.getMerchantName() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getMerchantName());
        }
        final String _tmp_3 = __converters.toStringList(entity.getTags());
        statement.bindString(11, _tmp_3);
        if (entity.getBillPhotoUri() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getBillPhotoUri());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getNotes());
        }
        if (entity.getBalanceAfterTxn() == null) {
          statement.bindNull(14);
        } else {
          statement.bindDouble(14, entity.getBalanceAfterTxn());
        }
        final int _tmp_4 = entity.isAutoCategorized() ? 1 : 0;
        statement.bindLong(15, _tmp_4);
        final String _tmp_5 = __converters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp_5 == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, _tmp_5);
        }
        final String _tmp_6 = __converters.dateToTimestamp(entity.getUpdatedAt());
        if (_tmp_6 == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, _tmp_6);
        }
        statement.bindLong(18, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateTransactionsCategory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE transactions SET categoryId = ? WHERE categoryId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteTransaction = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transactions WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTransaction(final TransactionEntity transaction,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTransactionEntity.insertAndReturnId(transaction);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTransactions(final List<TransactionEntity> transactions,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTransactionEntity.insert(transactions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTransaction(final TransactionEntity transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransactionEntity.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTransactionsCategory(final long oldCategoryId, final long newCategoryId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateTransactionsCategory.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, newCategoryId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, oldCategoryId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateTransactionsCategory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTransaction(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTransaction.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteTransaction.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TransactionEntity>> getTransactionsForAccount(final long accountId) {
    final String _sql = "SELECT * FROM transactions WHERE accountId = ? ORDER BY txnDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_1 = __converters.fromDateString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_1;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_2);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_3);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_4);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_5 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_7 = __converters.fromTimestamp(_tmp_6);
            if (_tmp_7 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_7;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_8;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_8 = null;
            } else {
              _tmp_8 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_9 = __converters.fromTimestamp(_tmp_8);
            if (_tmp_9 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_9;
            }
            _item = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TransactionEntity>> getAllTransactions() {
    final String _sql = "SELECT * FROM transactions ORDER BY txnDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_1 = __converters.fromDateString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_1;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_2);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_3);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_4);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_5 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_7 = __converters.fromTimestamp(_tmp_6);
            if (_tmp_7 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_7;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_8;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_8 = null;
            } else {
              _tmp_8 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_9 = __converters.fromTimestamp(_tmp_8);
            if (_tmp_9 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_9;
            }
            _item = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<TransactionEntity>> getTransactionsInPeriod(final LocalDate startDate,
      final LocalDate endDate) {
    final String _sql = "SELECT * FROM transactions WHERE txnDate BETWEEN ? AND ? ORDER BY txnDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final String _tmp = __converters.dateToString(startDate);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    final String _tmp_1 = __converters.dateToString(endDate);
    if (_tmp_1 == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp_1);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_3 = __converters.fromDateString(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_3;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_4);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_5);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_6;
            _tmp_6 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_6);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_7;
            _tmp_7 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_7 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_8;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_8 = null;
            } else {
              _tmp_8 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_9 = __converters.fromTimestamp(_tmp_8);
            if (_tmp_9 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_9;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_10;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_10 = null;
            } else {
              _tmp_10 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_11 = __converters.fromTimestamp(_tmp_10);
            if (_tmp_11 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_11;
            }
            _item = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object findExistingTransaction(final long accountId, final LocalDate date,
      final double amount, final String description,
      final Continuation<? super TransactionEntity> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE accountId = ? AND txnDate = ? AND amount = ? AND description = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountId);
    _argIndex = 2;
    final String _tmp = __converters.dateToString(date);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 3;
    _statement.bindDouble(_argIndex, amount);
    _argIndex = 4;
    _statement.bindString(_argIndex, description);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TransactionEntity>() {
      @Override
      @Nullable
      public TransactionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final TransactionEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_2 = __converters.fromDateString(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_2;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_3);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_4);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_5);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_6 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_7;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_8 = __converters.fromTimestamp(_tmp_7);
            if (_tmp_8 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_8;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_9;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_9 = null;
            } else {
              _tmp_9 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_10 = __converters.fromTimestamp(_tmp_9);
            if (_tmp_10 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_10;
            }
            _result = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTransactionById(final long id,
      final Continuation<? super TransactionEntity> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TransactionEntity>() {
      @Override
      @Nullable
      public TransactionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final TransactionEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_1 = __converters.fromDateString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_1;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_2);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_3);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_4);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_5 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_7 = __converters.fromTimestamp(_tmp_6);
            if (_tmp_7 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_7;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_8;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_8 = null;
            } else {
              _tmp_8 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_9 = __converters.fromTimestamp(_tmp_8);
            if (_tmp_9 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_9;
            }
            _result = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTransactionsByMerchant(final String merchantName,
      final Continuation<? super List<TransactionEntity>> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE merchantName = ? ORDER BY txnDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, merchantName);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_1 = __converters.fromDateString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_1;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_2);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_3);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_4);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_5 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_7 = __converters.fromTimestamp(_tmp_6);
            if (_tmp_7 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_7;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_8;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_8 = null;
            } else {
              _tmp_8 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_9 = __converters.fromTimestamp(_tmp_8);
            if (_tmp_9 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_9;
            }
            _item = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTransactionsForSubscriptionDetection(final LocalDate startDate,
      final Continuation<? super List<TransactionEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM transactions \n"
            + "        WHERE txnDate >= ? \n"
            + "        AND merchantName IS NOT NULL \n"
            + "        ORDER BY merchantName, txnDate DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.dateToString(startDate);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_2 = __converters.fromDateString(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_2;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_3);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_4);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_5);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_6 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_7;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_8 = __converters.fromTimestamp(_tmp_7);
            if (_tmp_8 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_8;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_9;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_9 = null;
            } else {
              _tmp_9 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_10 = __converters.fromTimestamp(_tmp_9);
            if (_tmp_10 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_10;
            }
            _item = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TransactionEntity>> getRecentTransactions(final int limit) {
    final String _sql = "SELECT * FROM transactions ORDER BY txnDate DESC, createdAt DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_1 = __converters.fromDateString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_1;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_2);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_3);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_4);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_5 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_7 = __converters.fromTimestamp(_tmp_6);
            if (_tmp_7 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_7;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_8;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_8 = null;
            } else {
              _tmp_8 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_9 = __converters.fromTimestamp(_tmp_8);
            if (_tmp_9 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_9;
            }
            _item = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAllTransactionsSync(final long accountId,
      final Continuation<? super List<TransactionEntity>> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE accountId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_1 = __converters.fromDateString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_1;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_2);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_3);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_4);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_5 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_7 = __converters.fromTimestamp(_tmp_6);
            if (_tmp_7 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_7;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_8;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_8 = null;
            } else {
              _tmp_8 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_9 = __converters.fromTimestamp(_tmp_8);
            if (_tmp_9 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_9;
            }
            _item = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object calculateBalance(final long accountId, final double initialBalance,
      final Continuation<? super Double> $completion) {
    final String _sql = "\n"
            + "        SELECT ? + \n"
            + "        COALESCE((SELECT SUM(amount) FROM transactions WHERE accountId = ? AND direction = 'INCOME'), 0) -\n"
            + "        COALESCE((SELECT SUM(amount) FROM transactions WHERE accountId = ? AND direction = 'EXPENSE'), 0)\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, initialBalance);
    _argIndex = 2;
    _statement.bindLong(_argIndex, accountId);
    _argIndex = 3;
    _statement.bindLong(_argIndex, accountId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @NonNull
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final double _tmp;
            _tmp = _cursor.getDouble(0);
            _result = _tmp;
          } else {
            _result = 0.0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLatestTransactionWithBalance(final long accountId,
      final Continuation<? super TransactionEntity> $completion) {
    final String _sql = "SELECT * FROM transactions WHERE accountId = ? ORDER BY txnDate DESC, createdAt DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TransactionEntity>() {
      @Override
      @Nullable
      public TransactionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfTxnDate = CursorUtil.getColumnIndexOrThrow(_cursor, "txnDate");
          final int _cursorIndexOfValueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "valueDate");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfReference = CursorUtil.getColumnIndexOrThrow(_cursor, "reference");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "direction");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfMerchantName = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantName");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfBillPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "billPhotoUri");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfBalanceAfterTxn = CursorUtil.getColumnIndexOrThrow(_cursor, "balanceAfterTxn");
          final int _cursorIndexOfIsAutoCategorized = CursorUtil.getColumnIndexOrThrow(_cursor, "isAutoCategorized");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final TransactionEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final LocalDate _tmpTxnDate;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTxnDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTxnDate);
            }
            final LocalDate _tmp_1 = __converters.fromDateString(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDate', but it was NULL.");
            } else {
              _tmpTxnDate = _tmp_1;
            }
            final LocalDate _tmpValueDate;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfValueDate)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfValueDate);
            }
            _tmpValueDate = __converters.fromDateString(_tmp_2);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final String _tmpReference;
            if (_cursor.isNull(_cursorIndexOfReference)) {
              _tmpReference = null;
            } else {
              _tmpReference = _cursor.getString(_cursorIndexOfReference);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final ActivityType _tmpDirection;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfDirection);
            _tmpDirection = __converters.fromTransactionDirection(_tmp_3);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpMerchantName;
            if (_cursor.isNull(_cursorIndexOfMerchantName)) {
              _tmpMerchantName = null;
            } else {
              _tmpMerchantName = _cursor.getString(_cursorIndexOfMerchantName);
            }
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.fromStringList(_tmp_4);
            final String _tmpBillPhotoUri;
            if (_cursor.isNull(_cursorIndexOfBillPhotoUri)) {
              _tmpBillPhotoUri = null;
            } else {
              _tmpBillPhotoUri = _cursor.getString(_cursorIndexOfBillPhotoUri);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Double _tmpBalanceAfterTxn;
            if (_cursor.isNull(_cursorIndexOfBalanceAfterTxn)) {
              _tmpBalanceAfterTxn = null;
            } else {
              _tmpBalanceAfterTxn = _cursor.getDouble(_cursorIndexOfBalanceAfterTxn);
            }
            final boolean _tmpIsAutoCategorized;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAutoCategorized);
            _tmpIsAutoCategorized = _tmp_5 != 0;
            final LocalDateTime _tmpCreatedAt;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_7 = __converters.fromTimestamp(_tmp_6);
            if (_tmp_7 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_7;
            }
            final LocalDateTime _tmpUpdatedAt;
            final String _tmp_8;
            if (_cursor.isNull(_cursorIndexOfUpdatedAt)) {
              _tmp_8 = null;
            } else {
              _tmp_8 = _cursor.getString(_cursorIndexOfUpdatedAt);
            }
            final LocalDateTime _tmp_9 = __converters.fromTimestamp(_tmp_8);
            if (_tmp_9 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpUpdatedAt = _tmp_9;
            }
            _result = new TransactionEntity(_tmpId,_tmpAccountId,_tmpTxnDate,_tmpValueDate,_tmpDescription,_tmpReference,_tmpAmount,_tmpDirection,_tmpCategoryId,_tmpMerchantName,_tmpTags,_tmpBillPhotoUri,_tmpNotes,_tmpBalanceAfterTxn,_tmpIsAutoCategorized,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
