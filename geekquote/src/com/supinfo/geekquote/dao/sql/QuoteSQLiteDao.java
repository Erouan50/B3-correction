package com.supinfo.geekquote.dao.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.supinfo.geekquote.dao.QuoteDao;
import com.supinfo.geekquote.model.Quote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class QuoteSQLiteDao implements QuoteDao {

    private QuoteSQLHelper sqlHelper;

    public QuoteSQLiteDao(QuoteSQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

    public void insertQuote(Quote quote) {
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
        database.insert(QuoteSQLHelper.QUOTE_TABLE_NAME, null, getContentValues(quote));
    }

    public void updateQuote(Quote quote) {
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
        database.update(QuoteSQLHelper.QUOTE_TABLE_NAME, getContentValues(quote), QuoteSQLHelper.QUOTE_ID_COLUMN + "=?",
                new String[]{String.valueOf(quote.getId())});
    }

    public List<Quote> getAllQuotes() {
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
        Cursor cursor = database.query(QuoteSQLHelper.QUOTE_TABLE_NAME,
                new String[]{QuoteSQLHelper.QUOTE_ID_COLUMN,
                        QuoteSQLHelper.QUOTE_STR_QUOTE_COLUMN,
                        QuoteSQLHelper.QUOTE_RATING_COLUMN,
                        QuoteSQLHelper.QUOTE_CREATE_DATE_COLUMN},
                null, null, null, null, null);

        List<Quote> quotes = new ArrayList<Quote>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Quote quote = new Quote();
            quote.setId(cursor.getLong(cursor.getColumnIndex(QuoteSQLHelper.QUOTE_ID_COLUMN)));
            quote.setStrQuote(cursor.getString(cursor.getColumnIndex(QuoteSQLHelper.QUOTE_STR_QUOTE_COLUMN)));
            quote.setRating(cursor.getInt(cursor.getColumnIndex(QuoteSQLHelper.QUOTE_RATING_COLUMN)));
            quote.setCreationDate(new Date(cursor.getLong(cursor.getColumnIndex(QuoteSQLHelper.QUOTE_CREATE_DATE_COLUMN))));
            quotes.add(quote);

            cursor.moveToNext();
        }
        return quotes;
    }

    private ContentValues getContentValues(Quote quote) {
        ContentValues values = new ContentValues();
        values.put(QuoteSQLHelper.QUOTE_ID_COLUMN, quote.getId());
        values.put(QuoteSQLHelper.QUOTE_STR_QUOTE_COLUMN, quote.getStrQuote());
        values.put(QuoteSQLHelper.QUOTE_RATING_COLUMN, quote.getRating());
        values.put(QuoteSQLHelper.QUOTE_CREATE_DATE_COLUMN, quote.getCreationDate().getTime());
        return values;
    }
}
