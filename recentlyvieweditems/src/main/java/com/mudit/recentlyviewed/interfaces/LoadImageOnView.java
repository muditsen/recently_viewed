package com.mudit.recentlyviewed.interfaces;

import android.widget.ImageView;

/**
 * Created by mudit on 27/02/18.
 */

public interface LoadImageOnView extends IActions{
    void onLoadRequest(String url, ImageView imageView);
}
