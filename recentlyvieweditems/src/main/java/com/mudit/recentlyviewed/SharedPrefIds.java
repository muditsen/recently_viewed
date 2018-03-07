package com.mudit.recentlyviewed;

import android.content.Context;
import android.content.SharedPreferences;


import com.mudit.recentlyviewed.interfaces.BaseRVBehaviour;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by mudit on 23/02/18.
 */

public class SharedPrefIds implements BaseRVBehaviour {

    /*
        Change sku
     */

    private SharedPreferences sharedPreferences;

    public SharedPrefIds(Context context){
        sharedPreferences = context.getSharedPreferences("RecentlyViewedPrefs", Context.MODE_PRIVATE);
    }

    @Override
    public void addToRecentlyViewed(ProductModel productModel) {
        try {
            JSONObject ids = new JSONObject(sharedPreferences.getString("skuList","{}"));
            ids.put(productModel.getSku(),productModel.getSku());
            sharedPreferences.edit().putString("skuList",ids.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFromRecentlyViewed(String id) {
        try {
            JSONObject ids = new JSONObject(sharedPreferences.getString("skuList","{}"));
            ids.remove(id);
            sharedPreferences.edit().putString("skuList",ids.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateRecentlyViewed(BaseProductModel id) {
        return 0;
    }

    @Override
    public void clearRecentlyViewed() {
        sharedPreferences.edit().putString("skuList",new JSONObject().toString()).apply();
    }

    @Override
    public BaseProductModel getRowById(String id) {
        return null;
    }

    @Override
    public List<? extends BaseProductModel> getAllRows() {
        return null;
    }

    @Override
    public int getProductCount() {
        return 0;
    }
}
