package com.example.qrcodescanwithsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * this sales class will contains information of the sales done
 */



public class SalesDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB = "products.db";
    public static final String TABLE_NAME = "sales";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ProductID";
    public static final String COL_3 = "ProductName";
    public static final String COL_4 = "Quantity";
    public static final String COL_5 = "Price";
    public static final String COL_6 = "ImageURL";

    SalesDatabaseHelper(Context context) {
        super(context, DB, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COL_1 + " INTEGER PRIMARY KEY," +
                COL_2 + " INTEGER NOT NULL," +
                COL_3 + " TEXT NOT NULL," +
                COL_4 + " INTEGER NOT NULL," +
                COL_5 + " DOUBLE NOT NULL," +
                COL_6 + " TEXT NOT NULL" +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
