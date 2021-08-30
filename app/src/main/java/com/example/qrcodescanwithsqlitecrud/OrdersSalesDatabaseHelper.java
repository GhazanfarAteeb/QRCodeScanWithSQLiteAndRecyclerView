package com.example.qrcodescanwithsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OrdersSalesDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB = "products.db";
    public static final String TABLE_NAME = "ordersSales";
    public static final String COL_1 = "ID";

    OrdersSalesDatabaseHelper(Context context) {
        super(context, DB,null, 1);
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
