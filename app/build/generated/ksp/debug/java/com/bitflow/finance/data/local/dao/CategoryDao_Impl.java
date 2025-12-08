package com.bitflow.finance.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.bitflow.finance.data.local.Converters;
import com.bitflow.finance.data.local.entity.CategoryEntity;
import com.bitflow.finance.domain.model.CategoryType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
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
public final class CategoryDao_Impl implements CategoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CategoryEntity> __insertionAdapterOfCategoryEntity;

  private final Converters __converters = new Converters();

  private final EntityInsertionAdapter<CategoryEntity> __insertionAdapterOfCategoryEntity_1;

  private final SharedSQLiteStatement __preparedStmtOfIncrementUsageCount;

  private final SharedSQLiteStatement __preparedStmtOfUpdateVisibility;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCategory;

  public CategoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCategoryEntity = new EntityInsertionAdapter<CategoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `categories` (`id`,`name`,`type`,`icon`,`color`,`usageCount`,`isUserDeletable`,`isHidden`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CategoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final String _tmp = __converters.toCategoryType(entity.getType());
        statement.bindString(3, _tmp);
        statement.bindString(4, entity.getIcon());
        statement.bindLong(5, entity.getColor());
        statement.bindLong(6, entity.getUsageCount());
        final int _tmp_1 = entity.isUserDeletable() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        final int _tmp_2 = entity.isHidden() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
      }
    };
    this.__insertionAdapterOfCategoryEntity_1 = new EntityInsertionAdapter<CategoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR IGNORE INTO `categories` (`id`,`name`,`type`,`icon`,`color`,`usageCount`,`isUserDeletable`,`isHidden`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CategoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getName());
        final String _tmp = __converters.toCategoryType(entity.getType());
        statement.bindString(3, _tmp);
        statement.bindString(4, entity.getIcon());
        statement.bindLong(5, entity.getColor());
        statement.bindLong(6, entity.getUsageCount());
        final int _tmp_1 = entity.isUserDeletable() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        final int _tmp_2 = entity.isHidden() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
      }
    };
    this.__preparedStmtOfIncrementUsageCount = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE categories SET usageCount = usageCount + 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateVisibility = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE categories SET isHidden = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteCategory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM categories WHERE id = ? AND isUserDeletable = 1";
        return _query;
      }
    };
  }

  @Override
  public Object insertCategory(final CategoryEntity category,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCategoryEntity.insertAndReturnId(category);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCategories(final List<CategoryEntity> categories,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCategoryEntity_1.insert(categories);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object incrementUsageCount(final long categoryId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementUsageCount.acquire();
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
          __preparedStmtOfIncrementUsageCount.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateVisibility(final long categoryId, final boolean isHidden,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateVisibility.acquire();
        int _argIndex = 1;
        final int _tmp = isHidden ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
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
          __preparedStmtOfUpdateVisibility.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCategory(final long categoryId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCategory.acquire();
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
          __preparedStmtOfDeleteCategory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CategoryEntity>> getAllCategories() {
    final String _sql = "SELECT * FROM categories WHERE isHidden = 0 ORDER BY usageCount DESC, name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"categories"}, new Callable<List<CategoryEntity>>() {
      @Override
      @NonNull
      public List<CategoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfIsUserDeletable = CursorUtil.getColumnIndexOrThrow(_cursor, "isUserDeletable");
          final int _cursorIndexOfIsHidden = CursorUtil.getColumnIndexOrThrow(_cursor, "isHidden");
          final List<CategoryEntity> _result = new ArrayList<CategoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final CategoryType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.fromCategoryType(_tmp);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            final int _tmpColor;
            _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final boolean _tmpIsUserDeletable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsUserDeletable);
            _tmpIsUserDeletable = _tmp_1 != 0;
            final boolean _tmpIsHidden;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsHidden);
            _tmpIsHidden = _tmp_2 != 0;
            _item = new CategoryEntity(_tmpId,_tmpName,_tmpType,_tmpIcon,_tmpColor,_tmpUsageCount,_tmpIsUserDeletable,_tmpIsHidden);
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
  public Flow<List<CategoryEntity>> getTopCategories(final int limit) {
    final String _sql = "SELECT * FROM categories WHERE isHidden = 0 ORDER BY usageCount DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"categories"}, new Callable<List<CategoryEntity>>() {
      @Override
      @NonNull
      public List<CategoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfIsUserDeletable = CursorUtil.getColumnIndexOrThrow(_cursor, "isUserDeletable");
          final int _cursorIndexOfIsHidden = CursorUtil.getColumnIndexOrThrow(_cursor, "isHidden");
          final List<CategoryEntity> _result = new ArrayList<CategoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final CategoryType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.fromCategoryType(_tmp);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            final int _tmpColor;
            _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final boolean _tmpIsUserDeletable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsUserDeletable);
            _tmpIsUserDeletable = _tmp_1 != 0;
            final boolean _tmpIsHidden;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsHidden);
            _tmpIsHidden = _tmp_2 != 0;
            _item = new CategoryEntity(_tmpId,_tmpName,_tmpType,_tmpIcon,_tmpColor,_tmpUsageCount,_tmpIsUserDeletable,_tmpIsHidden);
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
  public Object getCategoryById(final long categoryId,
      final Continuation<? super CategoryEntity> $completion) {
    final String _sql = "SELECT * FROM categories WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, categoryId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CategoryEntity>() {
      @Override
      @Nullable
      public CategoryEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIcon = CursorUtil.getColumnIndexOrThrow(_cursor, "icon");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfIsUserDeletable = CursorUtil.getColumnIndexOrThrow(_cursor, "isUserDeletable");
          final int _cursorIndexOfIsHidden = CursorUtil.getColumnIndexOrThrow(_cursor, "isHidden");
          final CategoryEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final CategoryType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.fromCategoryType(_tmp);
            final String _tmpIcon;
            _tmpIcon = _cursor.getString(_cursorIndexOfIcon);
            final int _tmpColor;
            _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            final int _tmpUsageCount;
            _tmpUsageCount = _cursor.getInt(_cursorIndexOfUsageCount);
            final boolean _tmpIsUserDeletable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsUserDeletable);
            _tmpIsUserDeletable = _tmp_1 != 0;
            final boolean _tmpIsHidden;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsHidden);
            _tmpIsHidden = _tmp_2 != 0;
            _result = new CategoryEntity(_tmpId,_tmpName,_tmpType,_tmpIcon,_tmpColor,_tmpUsageCount,_tmpIsUserDeletable,_tmpIsHidden);
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
