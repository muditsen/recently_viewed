package com.mudit.recentlyviewed;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mudit on 02/02/18.
 */

class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "RECENTLY_VIEWED_DB";

    // Contacts table name
    public static final String TABLE_RECENTLY_VIEWED= "recentlyViewed";

    // Contacts Table Columns names
    public static final String KEY_SKU = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_BRAND_NAME = "brand_name";
    public static final String KEY_PRICE = "price";
    public static final String KEY_SPECIAL_PRICE = "special_price";
    public static final String KEY_DISCOUNT = "discount";
    public static final String KEY_IMAGE = "image";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_RECENTLY_VIEWED + "("
                + KEY_SKU + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_BRAND_NAME + " TEXT,"
                + KEY_PRICE + " TEXT,"
                + KEY_SPECIAL_PRICE + " TEXT,"
                + KEY_DISCOUNT + " TEXT,"
                + KEY_IMAGE + " TEXT"
                + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RECENTLY_VIEWED);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
}
