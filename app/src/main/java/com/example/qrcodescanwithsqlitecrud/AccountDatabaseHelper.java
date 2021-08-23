package com.example.qrcodescanwithsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountDatabaseHelper extends SQLiteOpenHelper {
    public final static String DB = "products.db";
    public static final String TABLE_NAME = "accounts";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Username";
    public static final String COL_4 = "Password";
    public static final String COL_5 = "DateOfBirth";
    AccountDatabaseHelper(Context context) {
        super(context, DB,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME + "("+
                    COL_1 + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_2 + "TEXT NOT NULL, " +
                    COL_3 + "TEXT NOT NULL, " +
                    COL_4 + "TEXT NOT NULL, " +
                    COL_5 + "DATE NOT NULL " +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
