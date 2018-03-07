package com.mudit.recentlyviewed;

import android.content.Context;
import android.content.SharedPreferences;

import com.mudit.recentlyviewed.interfaces.BaseRVBehaviour;
import com.mudit.recentlyviewed.interfaces.QueueBehaviour;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mudit on 24/01/18.
 */

class SharedPrefRVBehaviour implements BaseRVBehaviour,QueueBehaviour {

    private static final int PRODUCT_COUNT = 10;

    private SharedPreferences sharedPreferences;

    private SharedPrefIds sharedPrefIds;

    public SharedPrefRVBehaviour(Context context){
        sharedPreferences = context.getSharedPreferences("RecentlyViewedPrefs", Context.MODE_PRIVATE);
        sharedPrefIds = new SharedPrefIds(context);
    }

    @Override
    public void addToRecentlyViewed(ProductModel productModel) {
        try {
            JSONObject jsonObject = new JSONObject(sharedPreferences.getString("items","{}"));
            jsonObject.put(productModel.getSku(),getJsonObjectFrom(productModel));
            sharedPreferences.edit().putString("items",jsonObject.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFromRecentlyViewed(String id) {
        try {

            JSONObject jsonObject = new JSONObject(sharedPreferences.getString("items","{}"));
            jsonObject.remove(id);
            sharedPreferences.edit().putString("items",jsonObject.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int updateRecentlyViewed(BaseProductModel productModel) {
        try {
            JSONObject jsonObject = new JSONObject(sharedPreferences.getString("items","{}"));
            jsonObject.put(productModel.getSku(),getJsonObjectFrom(productModel));
            sharedPreferences.edit().putString("items",jsonObject.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }


    @Override
    public void clearRecentlyViewed() {
        sharedPreferences.edit().putString("items",new JSONObject().toString()).apply();
    }

    @Override
    public BaseProductModel getRowById(String id) {
        try {
            JSONObject jsonObject = new JSONObject(sharedPreferences.getString("items","{}"));
            return  new BaseProductModel(jsonObject.getJSONObject("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public List<? extends BaseProductModel> getAllRows() {
        List<BaseProductModel> baseProductModels = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(sharedPreferences.getString("items","{}"));
            Iterator<?> keys = jsonObject.keys();

            while( keys.hasNext() ) {
                String key = (String)keys.next();
                if ( jsonObject.get(key) instanceof JSONObject) {
                    baseProductModels.add(new BaseProductModel(jsonObject));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return baseProductModels;
    }

    @Override
    public int getProductCount() {
        return 0;
    }

    public static JSONObject getJsonObjectFrom(Object object) {
        JSONObject jsonObject = new JSONObject();
        if (object == null) {
            return jsonObject;
        }
        Field[] fields = object.getClass().getFields();
        for (Field field : fields) {
                try {
                    if (isPrimitiveType(field.getType().getName())) {
                        jsonObject.put(field.getName(), field.get(object));
                    } else if (isStringType(field.getType().getName())) {
                        if (field.get(object) == null) {
                            jsonObject.put(field.getName(), "");
                        } else {
                            jsonObject.put(field.getName(), field.get(object));
                        }
                    } else {
                        jsonObject.put(field.getName(), getJsonObjectFrom(field.get(object)));
                    }

                } catch (IllegalAccessException | JSONException e) {
                    e.printStackTrace();
                }
            }
        return jsonObject;
    }

    private static boolean isStringType(String type) {
        return type.equalsIgnoreCase("java.lang.String");
    }

    private static boolean isPrimitiveType(String type) {
        if (type.equalsIgnoreCase("int")) {
            return true;
        } else if (type.equalsIgnoreCase("float") || type.equalsIgnoreCase("java.lang.Float")) {
            return true;
        } else if (type.equalsIgnoreCase("double") || type.equalsIgnoreCase("java.lang.Double")) {
            return true;
        } else if (type.equalsIgnoreCase("short") || type.equalsIgnoreCase("java.lang.Short")) {
            return true;
        } else if (type.equalsIgnoreCase("boolean") || type.equalsIgnoreCase("java.lang.Boolean")) {
            return true;
        } else if (type.equalsIgnoreCase("byte") || type.equalsIgnoreCase("java.lang.Byte")) {
            return true;
        } else if (type.equalsIgnoreCase("long") || type.equalsIgnoreCase("java.lang.Long")) {
            return true;
        } else if (type.equalsIgnoreCase("char") || type.equalsIgnoreCase("java.lang.Char")) {
            return true;
        }
        return false;
    }

    @Override
    public void peek() {

    }

    @Override
    public void removeLast() {
        try {
            JSONObject jsonObject = new JSONObject(sharedPreferences.getString("items","{}"));
            jsonObject.remove(jsonObject.keys().next());
            sharedPreferences.edit().putString("items",jsonObject.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
