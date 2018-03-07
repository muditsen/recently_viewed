package com.mudit.recentlyviewed;

import org.json.JSONObject;

/**
 * Created by mudit on 24/01/18.
 */

public class BaseProductModel {

    private String sku;
    private String name;
    private String brandName;
    private String price;
    private String specialPrice;
    private String discount;
    private String imageUrl;

    public BaseProductModel(){

    }
    public BaseProductModel(JSONObject jsonObject){
        sku = jsonObject.optString("sku");
        name = jsonObject.optString("name");
        brandName = jsonObject.optString("brandName");
        price = jsonObject.optString("price");
        specialPrice = jsonObject.optString("specialPrice");
        discount = jsonObject.optString("discount");
        imageUrl = jsonObject.optString("imageUrl");
    }


    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(String specialPrice) {
        this.specialPrice = specialPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
