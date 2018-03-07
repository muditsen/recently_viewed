package com.mudit.recentlyviewed.interfaces;


import com.mudit.recentlyviewed.BaseProductModel;
import com.mudit.recentlyviewed.ProductModel;

import java.util.List;

/**
 * Created by mudit on 24/01/18.
 */

public interface BaseRVBehaviour {

    void addToRecentlyViewed(ProductModel productModel);

    void removeFromRecentlyViewed(String id);

    int updateRecentlyViewed(BaseProductModel id);

    void clearRecentlyViewed();

    BaseProductModel getRowById(String id);

    List<? extends BaseProductModel> getAllRows();

    int getProductCount();
}
