package com.example.qrcodescanwithsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Orders extends SQLiteOpenHelper {
    public final static String DB = "products.db";
    public static final String TABLE_NAME = "Accounts";
    public static final String COL_1 = "ID";
    Orders(Context context) {
        super(context, DB,null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
