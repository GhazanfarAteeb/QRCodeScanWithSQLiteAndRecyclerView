package com.example.qrcodescanwithsqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



// class for helping in database making
public class ProductDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB = "product.db";
    public static final String TABLE_NAME = "products";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "ProductName";
    public static final String COL_3 = "Quantity";
    public static final String COL_4 = "Price";
    public static final String COL_5 = "ImageURL";

    public ProductDatabaseHelper(Context context) {
        super(context, DB, null, 1);
    }

    // SQL Query to create the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_2 + " TEXT NOT NULL," +
                COL_3 + " INTEGER NOT NULL," +
                COL_4 + " DOUBLE NOT NULL," +
                COL_5 + " TEXT NOT NULL)"
        );
    }

    // SQL Query when there is update in the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}