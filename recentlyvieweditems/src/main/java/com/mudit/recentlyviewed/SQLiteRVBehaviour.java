package com.mudit.recentlyviewed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mudit.recentlyviewed.interfaces.BaseRVBehaviour;
import com.mudit.recentlyviewed.interfaces.Constants;

import java.util.ArrayList;
import java.util.List;

import static com.mudit.recentlyviewed.DatabaseHandler.TABLE_RECENTLY_VIEWED;


/**
 * Created by mudit on 24/01/18.
 */

class SQLiteRVBehaviour implements BaseRVBehaviour {

    public static final String TAG = SQLiteRVBehaviour.class.getName();

    private final DatabaseHandler dbHandler;

    public SQLiteRVBehaviour(Context context){
        dbHandler = new DatabaseHandler(context);
    }

    @Override
    public void addToRecentlyViewed(ProductModel productModel) {

        if(getRowById(productModel.getSku()) !=null){
            if(Constants.IS_LOG_ENABLED)
                Log.w(TAG,"A row already exist with this SKU..!! Updating");
            updateRecentlyViewed(productModel);
            return;
        }

        if(getProductCount() == RecentlyViewed.MAX_PRODUCT_COUNT){
            deleteFirstRow();
        }
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_SKU, productModel.getSku()); // Contact Name
        values.put(DatabaseHandler.KEY_NAME, productModel.getName()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_BRAND_NAME, productModel.getBrandName()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_PRICE, productModel.getPrice()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_SPECIAL_PRICE, productModel.getSpecialPrice()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_DISCOUNT, productModel.getDiscount()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_IMAGE, productModel.getImageUrl()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_RECENTLY_VIEWED, null, values);
        db.close(); // Closing database connection
        if(Constants.IS_LOG_ENABLED)
            Log.w(TAG,"Added new row with id"+productModel.getSku());
    }

    @Override
    public void removeFromRecentlyViewed(String sku) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.delete(TABLE_RECENTLY_VIEWED, DatabaseHandler.KEY_SKU + " = ?",
                new String[] { String.valueOf(sku) });
        db.close();
        if(Constants.IS_LOG_ENABLED)
            Log.w(TAG,"Removed  row with id "+sku);
    }

    @Override
    public int updateRecentlyViewed(BaseProductModel productModel) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseHandler.KEY_SKU, productModel.getSku()); // Contact Name
        values.put(DatabaseHandler.KEY_NAME, productModel.getName()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_BRAND_NAME, productModel.getBrandName()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_PRICE, productModel.getPrice()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_SPECIAL_PRICE, productModel.getSpecialPrice()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_DISCOUNT, productModel.getDiscount()); // Contact Phone Number
        values.put(DatabaseHandler.KEY_IMAGE, productModel.getImageUrl()); // Contact Phone Number

        // updating row
        if(Constants.IS_LOG_ENABLED)
            Log.w(TAG,"Updated row with id "+productModel.getSku());
        return db.update(TABLE_RECENTLY_VIEWED, values, DatabaseHandler.KEY_SKU + " = ?",
                new String[] { String.valueOf(productModel.getSku()) });
    }


    @Override
    public void clearRecentlyViewed() {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.delete(TABLE_RECENTLY_VIEWED, null, null);
        db.close();
        if(Constants.IS_LOG_ENABLED)
            Log.w(TAG,"Cleared Recently viewed");
    }

    @Override
    public BaseProductModel getRowById(String id) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECENTLY_VIEWED, new String[] { DatabaseHandler.KEY_SKU,
                        DatabaseHandler.KEY_NAME, DatabaseHandler.KEY_BRAND_NAME, DatabaseHandler.KEY_PRICE,DatabaseHandler.KEY_SPECIAL_PRICE,DatabaseHandler.KEY_DISCOUNT,DatabaseHandler.KEY_IMAGE}, DatabaseHandler.KEY_SKU + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            BaseProductModel baseProductModel = new BaseProductModel();
            baseProductModel.setSku(cursor.getString(0));
            baseProductModel.setName(cursor.getString(1));
            baseProductModel.setBrandName(cursor.getString(2));
            baseProductModel.setPrice(cursor.getString(3));
            baseProductModel.setSpecialPrice(cursor.getString(4));
            baseProductModel.setDiscount(cursor.getString(5));
            baseProductModel.setImageUrl(cursor.getString(6));
            cursor.close();
            return baseProductModel;
        }



        // return contact
        return null;
    }

    @Override
    public List<? extends BaseProductModel> getAllRows() {
        List<BaseProductModel> productList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RECENTLY_VIEWED;

        SQLiteDatabase db = dbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst() && cursor.getCount() > 0) {
            do {
                BaseProductModel baseProductModel = new BaseProductModel();
                baseProductModel.setSku(cursor.getString(0));
                baseProductModel.setName(cursor.getString(1));
                baseProductModel.setBrandName(cursor.getString(2));
                baseProductModel.setPrice(cursor.getString(3));
                baseProductModel.setSpecialPrice(cursor.getString(4));
                baseProductModel.setDiscount(cursor.getString(5));
                baseProductModel.setImageUrl(cursor.getString(6));
                // Adding contact to list
                productList.add(baseProductModel);
            } while (cursor.moveToNext());
        }
        cursor.close();
        // return contact list
        return productList;
    }

    @Override
    public int getProductCount(){
        String countQuery = "SELECT  * FROM " + TABLE_RECENTLY_VIEWED;
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    private void deleteFirstRow() {
        String selectQuery = "SELECT  * FROM " + TABLE_RECENTLY_VIEWED;

        SQLiteDatabase db = dbHandler.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst() && cursor.getCount() > 0) {
            String rowId = cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_SKU));
            db.delete(TABLE_RECENTLY_VIEWED, DatabaseHandler.KEY_SKU + "=?",  new String[]{rowId});
        }
        cursor.close();
        db.close();
    }

}
