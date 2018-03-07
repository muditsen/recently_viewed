package com.mudit.recentlyviewed;

import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import com.mudit.recentlyviewed.interfaces.IActions;
import com.mudit.recentlyviewed.interfaces.LoadImageOnView;

import java.util.HashMap;

/**
 * Created by mudit on 27/02/18.
 */

class EventFactory {

    private static EventFactory eventManager;

    public static final String IMAGE_LOAD_EVENT = "ImageLoadEvent";
    public static final String GOTO_PDP = "GotoPDP";

    private HashMap<String, IActions> events = new HashMap<>();

    private EventFactory() {
        try {
            events.put(IMAGE_LOAD_EVENT,null);
            events.put(GOTO_PDP,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EventFactory getInstance() {
        return eventManager != null ? eventManager : (eventManager = new EventFactory());
    }

    public void fireEvent(final String key, final ProductModel productModel) {
        if (events.get(key) != null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                events.get(key).performAction(productModel);
            } else {
                if (Looper.getMainLooper() != null) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            events.get(key).performAction(productModel);
                        }
                    });
                } else {
                    throw new IllegalStateException("Activity is not running on main thread");
                }

            }

        } else {
            throw new IllegalStateException("Please Create Your Event First");
        }
    }

    public void fireEvent(final String key, final String url, final ImageView imageView) {
        if (events.get(key) != null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if(events.get(key) instanceof LoadImageOnView){
                    ((LoadImageOnView)events.get(key)).onLoadRequest(url,imageView);
                }
            } else {
                if (Looper.getMainLooper() != null) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((LoadImageOnView)events.get(key)).onLoadRequest(url,imageView);
                        }
                    });
                } else {
                    throw new IllegalStateException("Activity is not running on main thread");
                }

            }

        } else {
            throw new IllegalStateException("Please Create Your Event First");
        }
    }

    public void registerEvent(String key, IActions iActions){
        switch (key){
            case IMAGE_LOAD_EVENT:
                events.put(key,iActions);
                break;
            case GOTO_PDP:
                events.put(key,iActions);
                break;
            default:
                break;
        }

    }

}