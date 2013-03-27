package com.supinfo.geekquote.dao.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class QuoteSQLHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.quote";
    private static final int DATABASE_VERSION = 1;
    public static final String QUOTE_TABLE_NAME = "quote";
    public static final String QUOTE_ID_COLUMN = "id";
    public static final String QUOTE_STR_QUOTE_COLUMN = "strQuote";
    public static final String QUOTE_RATING_COLUMN = "rating";
    public static final String QUOTE_CREATE_DATE_COLUMN = "createDate";
    private static final String QUOTE_TABLE_DDL = "CREATE TABLE " + QUOTE_TABLE_NAME + " (" +
            QUOTE_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            QUOTE_STR_QUOTE_COLUMN + " TEXT NOT NULL, " +
            QUOTE_RATING_COLUMN + " INTEGER NOT NULL, " +
            QUOTE_CREATE_DATE_COLUMN + " NUMERIC NOT NULL);";


    public QuoteSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUOTE_TABLE_DDL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QUOTE_TABLE_NAME);
        db.execSQL(QUOTE_TABLE_DDL);
    }
}
