package com.supinfo.geekquote.manager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.supinfo.geekquote.dao.QuoteSQLHelper;
import com.supinfo.geekquote.model.Quote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.supinfo.geekquote.dao.QuoteSQLHelper.QUOTE_CREATE_DATE_COLUMN;
import static com.supinfo.geekquote.dao.QuoteSQLHelper.QUOTE_ID_COLUMN;
import static com.supinfo.geekquote.dao.QuoteSQLHelper.QUOTE_RATING_COLUMN;
import static com.supinfo.geekquote.dao.QuoteSQLHelper.QUOTE_STR_QUOTE_COLUMN;
import static com.supinfo.geekquote.dao.QuoteSQLHelper.QUOTE_TABLE_NAME;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class QuoteManager {

    private QuoteSQLHelper sqlHelper;

    public QuoteManager(QuoteSQLHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

    public void insertQuote(Quote quote) {
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
        database.insert(QUOTE_TABLE_NAME, null, getContentValues(quote));
    }

    public void updateQuote(Quote quote) {
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
        database.update(QUOTE_TABLE_NAME, getContentValues(quote), QUOTE_ID_COLUMN + "=?",
                new String[]{String.valueOf(quote.getId())});
    }

    public List<Quote> getAllQuotes() {
        SQLiteDatabase database = sqlHelper.getWritableDatabase();
        Cursor cursor = database.query(QUOTE_TABLE_NAME,
                new String[]{QUOTE_ID_COLUMN,
                        QUOTE_STR_QUOTE_COLUMN,
                        QUOTE_RATING_COLUMN,
                        QUOTE_CREATE_DATE_COLUMN},
                null, null, null, null, null);

        List<Quote> quotes = new ArrayList<Quote>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Quote quote = new Quote();
            quote.setId(cursor.getLong(cursor.getColumnIndex(QUOTE_ID_COLUMN)));
            quote.setStrQuote(cursor.getString(cursor.getColumnIndex(QUOTE_STR_QUOTE_COLUMN)));
            quote.setRating(cursor.getInt(cursor.getColumnIndex(QUOTE_RATING_COLUMN)));
            quote.setCreationDate(new Date(cursor.getLong(cursor.getColumnIndex(QUOTE_CREATE_DATE_COLUMN))));
            quotes.add(quote);

            cursor.moveToNext();
        }
        return quotes;
    }

    private ContentValues getContentValues(Quote quote) {
        ContentValues values = new ContentValues();
        values.put(QUOTE_ID_COLUMN, quote.getId());
        values.put(QUOTE_STR_QUOTE_COLUMN, quote.getStrQuote());
        values.put(QUOTE_RATING_COLUMN, quote.getRating());
        values.put(QUOTE_CREATE_DATE_COLUMN, quote.getCreationDate().getTime());
        return values;
    }
}
