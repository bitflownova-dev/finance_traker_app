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
import com.bitflow.finance.data.local.entity.InvoiceEntity;
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
public final class InvoiceDao_Impl implements InvoiceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<InvoiceEntity> __insertionAdapterOfInvoiceEntity;

  private final EntityDeletionOrUpdateAdapter<InvoiceEntity> __updateAdapterOfInvoiceEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteInvoice;

  public InvoiceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInvoiceEntity = new EntityInsertionAdapter<InvoiceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `invoices` (`id`,`invoiceNumber`,`clientName`,`clientAddress`,`date`,`dueDate`,`itemsJson`,`taxRate`,`amount`,`isPaid`,`pdfPath`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvoiceEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getInvoiceNumber());
        statement.bindString(3, entity.getClientName());
        statement.bindString(4, entity.getClientAddress());
        statement.bindLong(5, entity.getDate());
        statement.bindLong(6, entity.getDueDate());
        statement.bindString(7, entity.getItemsJson());
        statement.bindDouble(8, entity.getTaxRate());
        statement.bindDouble(9, entity.getAmount());
        final int _tmp = entity.isPaid() ? 1 : 0;
        statement.bindLong(10, _tmp);
        if (entity.getPdfPath() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getPdfPath());
        }
        statement.bindLong(12, entity.getCreatedAt());
      }
    };
    this.__updateAdapterOfInvoiceEntity = new EntityDeletionOrUpdateAdapter<InvoiceEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `invoices` SET `id` = ?,`invoiceNumber` = ?,`clientName` = ?,`clientAddress` = ?,`date` = ?,`dueDate` = ?,`itemsJson` = ?,`taxRate` = ?,`amount` = ?,`isPaid` = ?,`pdfPath` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvoiceEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getInvoiceNumber());
        statement.bindString(3, entity.getClientName());
        statement.bindString(4, entity.getClientAddress());
        statement.bindLong(5, entity.getDate());
        statement.bindLong(6, entity.getDueDate());
        statement.bindString(7, entity.getItemsJson());
        statement.bindDouble(8, entity.getTaxRate());
        statement.bindDouble(9, entity.getAmount());
        final int _tmp = entity.isPaid() ? 1 : 0;
        statement.bindLong(10, _tmp);
        if (entity.getPdfPath() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getPdfPath());
        }
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteInvoice = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM invoices WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertInvoice(final InvoiceEntity invoice,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfInvoiceEntity.insertAndReturnId(invoice);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateInvoice(final InvoiceEntity invoice,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfInvoiceEntity.handle(invoice);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteInvoice(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteInvoice.acquire();
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
          __preparedStmtOfDeleteInvoice.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<InvoiceEntity>> getAllInvoices() {
    final String _sql = "SELECT * FROM invoices ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"invoices"}, new Callable<List<InvoiceEntity>>() {
      @Override
      @NonNull
      public List<InvoiceEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInvoiceNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceNumber");
          final int _cursorIndexOfClientName = CursorUtil.getColumnIndexOrThrow(_cursor, "clientName");
          final int _cursorIndexOfClientAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "clientAddress");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfItemsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "itemsJson");
          final int _cursorIndexOfTaxRate = CursorUtil.getColumnIndexOrThrow(_cursor, "taxRate");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfIsPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "isPaid");
          final int _cursorIndexOfPdfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "pdfPath");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<InvoiceEntity> _result = new ArrayList<InvoiceEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvoiceEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpInvoiceNumber;
            _tmpInvoiceNumber = _cursor.getString(_cursorIndexOfInvoiceNumber);
            final String _tmpClientName;
            _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
            final String _tmpClientAddress;
            _tmpClientAddress = _cursor.getString(_cursorIndexOfClientAddress);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpDueDate;
            _tmpDueDate = _cursor.getLong(_cursorIndexOfDueDate);
            final String _tmpItemsJson;
            _tmpItemsJson = _cursor.getString(_cursorIndexOfItemsJson);
            final double _tmpTaxRate;
            _tmpTaxRate = _cursor.getDouble(_cursorIndexOfTaxRate);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final boolean _tmpIsPaid;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPaid);
            _tmpIsPaid = _tmp != 0;
            final String _tmpPdfPath;
            if (_cursor.isNull(_cursorIndexOfPdfPath)) {
              _tmpPdfPath = null;
            } else {
              _tmpPdfPath = _cursor.getString(_cursorIndexOfPdfPath);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new InvoiceEntity(_tmpId,_tmpInvoiceNumber,_tmpClientName,_tmpClientAddress,_tmpDate,_tmpDueDate,_tmpItemsJson,_tmpTaxRate,_tmpAmount,_tmpIsPaid,_tmpPdfPath,_tmpCreatedAt);
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
  public Object getInvoiceById(final long id,
      final Continuation<? super InvoiceEntity> $completion) {
    final String _sql = "SELECT * FROM invoices WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<InvoiceEntity>() {
      @Override
      @Nullable
      public InvoiceEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInvoiceNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "invoiceNumber");
          final int _cursorIndexOfClientName = CursorUtil.getColumnIndexOrThrow(_cursor, "clientName");
          final int _cursorIndexOfClientAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "clientAddress");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfDueDate = CursorUtil.getColumnIndexOrThrow(_cursor, "dueDate");
          final int _cursorIndexOfItemsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "itemsJson");
          final int _cursorIndexOfTaxRate = CursorUtil.getColumnIndexOrThrow(_cursor, "taxRate");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfIsPaid = CursorUtil.getColumnIndexOrThrow(_cursor, "isPaid");
          final int _cursorIndexOfPdfPath = CursorUtil.getColumnIndexOrThrow(_cursor, "pdfPath");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final InvoiceEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpInvoiceNumber;
            _tmpInvoiceNumber = _cursor.getString(_cursorIndexOfInvoiceNumber);
            final String _tmpClientName;
            _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
            final String _tmpClientAddress;
            _tmpClientAddress = _cursor.getString(_cursorIndexOfClientAddress);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final long _tmpDueDate;
            _tmpDueDate = _cursor.getLong(_cursorIndexOfDueDate);
            final String _tmpItemsJson;
            _tmpItemsJson = _cursor.getString(_cursorIndexOfItemsJson);
            final double _tmpTaxRate;
            _tmpTaxRate = _cursor.getDouble(_cursorIndexOfTaxRate);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final boolean _tmpIsPaid;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPaid);
            _tmpIsPaid = _tmp != 0;
            final String _tmpPdfPath;
            if (_cursor.isNull(_cursorIndexOfPdfPath)) {
              _tmpPdfPath = null;
            } else {
              _tmpPdfPath = _cursor.getString(_cursorIndexOfPdfPath);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new InvoiceEntity(_tmpId,_tmpInvoiceNumber,_tmpClientName,_tmpClientAddress,_tmpDate,_tmpDueDate,_tmpItemsJson,_tmpTaxRate,_tmpAmount,_tmpIsPaid,_tmpPdfPath,_tmpCreatedAt);
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
