package com.example.qrcodescanwithsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * this class contains the information regarding which order is placed and when is placed
 * and when order is updated
 */
public class OrdersDatabaseHelper extends SQLiteOpenHelper {
    public final static String DB = "products.db";
    public static final String TABLE_NAME = "orders";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "status";
    public static final String COL_3 = "on_create";
    public static final String COL_4 = "on_update";

    OrdersDatabaseHelper(Context context) {
        super(context, DB,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE "+ TABLE_NAME+"("+
                    COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COL_2 + " TEXT NOT NULL, "+
                    COL_3 + " DATETIME NOT NULL, "+
                    COL_4 + " DATETIME NOT NULL" +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
