package com.mudit.recentlyviewed;

import android.content.Context;

import com.mudit.recentlyviewed.interfaces.BaseRVBehaviour;


/**
 * Created by mudit on 23/01/18.
 */

public class RecentlyViewed {

    public static final int MAX_PRODUCT_COUNT = 10;

    public static final int SQLITE = 0;

    public static final int SHARED_PREFS = 1;

    private BaseRVBehaviour baseRV;

    public RecentlyViewed(Context context, int type) {
        //TODO convert this to Factory and add ROOM and Network;
        if (type == SQLITE) {
            baseRV = new SQLiteRVBehaviour(context);
        } else if (type == SHARED_PREFS) {
            baseRV = new SharedPrefRVBehaviour(context);
        }else {
            baseRV = new SharedPrefIds(context);
        }
    }

    public void add(ProductModel model) {
        baseRV.addToRecentlyViewed(model);
    }

    public BaseRVBehaviour getBaseRV() {
        return baseRV;
    }
}
