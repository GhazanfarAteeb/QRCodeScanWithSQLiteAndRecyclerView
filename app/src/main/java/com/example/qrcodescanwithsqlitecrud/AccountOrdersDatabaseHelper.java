package com.example.qrcodescanwithsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * to destroy many to many relationship between accounts and orders placed
 * therefore this class will contain information regarding IDs of both Accounts and Orders placed
 */
public class AccountOrdersDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB = "products.db";
    public static final String TABLE_NAME = "accountOrders";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "OrderID";
    public static final String COL_3 = "AccountID";

    AccountOrdersDatabaseHelper(Context context) {
        super(context,DB,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
