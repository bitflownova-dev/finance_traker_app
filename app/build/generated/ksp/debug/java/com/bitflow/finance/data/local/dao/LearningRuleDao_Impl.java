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
import com.bitflow.finance.data.local.entity.LearningRuleEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
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
public final class LearningRuleDao_Impl implements LearningRuleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LearningRuleEntity> __insertionAdapterOfLearningRuleEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<LearningRuleEntity> __updateAdapterOfLearningRuleEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteRulesForCategory;

  private final SharedSQLiteStatement __preparedStmtOfUpdateRulesCategory;

  private final SharedSQLiteStatement __preparedStmtOfClearAllRules;

  public LearningRuleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLearningRuleEntity = new EntityInsertionAdapter<LearningRuleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `learning_rules` (`id`,`merchantPattern`,`categoryId`,`confidenceScore`,`usageCount`,`createdAt`,`lastUsedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LearningRuleEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getMerchantPattern());
        statement.bindLong(3, entity.getCategoryId());
        statement.bindDouble(4, entity.getConfidenceScore());
        statement.bindLong(5, entity.getUsageCount());
        final String _tmp = __converters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        final String _tmp_1 = __converters.dateToTimestamp(entity.getLastUsedAt());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
      }
    };
    this.__updateAdapterOfLearningRuleEntity = new EntityDeletionOrUpdateAdapter<LearningRuleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `learning_rules` SET `id` = ?,`merchantPattern` = ?,`categoryId` = ?,`confidenceScore` = ?,`usageCount` = ?,`createdAt` = ?,`lastUsedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LearningRuleEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getMerchantPattern());
        statement.bindLong(3, entity.getCategoryId());
        statement.bindDouble(4, entity.getConfidenceScore());
        statement.bindLong(5, entity.getUsageCount());
        final String _tmp = __converters.dateToTimestamp(entity.getCreatedAt());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        final String _tmp_1 = __converters.dateToTimestamp(entity.getLastUsedAt());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteRulesForCategory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM learning_rules WHERE categoryId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateRulesCategory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE learning_rules SET categoryId = ? WHERE categoryId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearAllRules = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM learning_rules";
        return _query;
      }
    };
  }

  @Override
  public Object insertRule(final LearningRuleEntity rule,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfLearningRuleEntity.insertAndReturnId(rule);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRule(final LearningRuleEntity rule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLearningRuleEntity.handle(rule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRulesForCategory(final long categoryId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteRulesForCategory.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, categoryId);
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
          __preparedStmtOfDeleteRulesForCategory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRulesCategory(final long oldCategoryId, final long newCategoryId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateRulesCategory.acquire();
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
          __preparedStmtOfUpdateRulesCategory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAllRules(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAllRules.acquire();
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
          __preparedStmtOfClearAllRules.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object findRuleByMerchant(final String merchantPattern,
      final Continuation<? super LearningRuleEntity> $completion) {
    final String _sql = "SELECT * FROM learning_rules WHERE merchantPattern = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, merchantPattern);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<LearningRuleEntity>() {
      @Override
      @Nullable
      public LearningRuleEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMerchantPattern = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantPattern");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastUsedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsedAt");
          final LearningRuleEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMerchantPattern;
            _tmpMerchantPattern = _cursor.getString(_cursorIndexOfMerchantPattern);
            final long _tmpCategoryId;
            _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final LocalDateTime _tmpCreatedAt;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_1;
            }
            final LocalDateTime _tmpLastUsedAt;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfLastUsedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfLastUsedAt);
            }
            final LocalDateTime _tmp_3 = __converters.fromTimestamp(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpLastUsedAt = _tmp_3;
            }
            _result = new LearningRuleEntity(_tmpId,_tmpMerchantPattern,_tmpCategoryId,_tmpConfidenceScore,_tmpUsageCount,_tmpCreatedAt,_tmpLastUsedAt);
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
  public Flow<List<LearningRuleEntity>> getAllRules() {
    final String _sql = "SELECT * FROM learning_rules ORDER BY confidenceScore DESC, usageCount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"learning_rules"}, new Callable<List<LearningRuleEntity>>() {
      @Override
      @NonNull
      public List<LearningRuleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfMerchantPattern = CursorUtil.getColumnIndexOrThrow(_cursor, "merchantPattern");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastUsedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUsedAt");
          final List<LearningRuleEntity> _result = new ArrayList<LearningRuleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LearningRuleEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpMerchantPattern;
            _tmpMerchantPattern = _cursor.getString(_cursorIndexOfMerchantPattern);
            final long _tmpCategoryId;
            _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final LocalDateTime _tmpCreatedAt;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCreatedAt)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCreatedAt);
            }
            final LocalDateTime _tmp_1 = __converters.fromTimestamp(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpCreatedAt = _tmp_1;
            }
            final LocalDateTime _tmpLastUsedAt;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfLastUsedAt)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfLastUsedAt);
            }
            final LocalDateTime _tmp_3 = __converters.fromTimestamp(_tmp_2);
            if (_tmp_3 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.time.LocalDateTime', but it was NULL.");
            } else {
              _tmpLastUsedAt = _tmp_3;
            }
            _item = new LearningRuleEntity(_tmpId,_tmpMerchantPattern,_tmpCategoryId,_tmpConfidenceScore,_tmpUsageCount,_tmpCreatedAt,_tmpLastUsedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
