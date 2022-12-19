package com.example.zaikokanri;

import android.app.Application;
import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {

    private static MyApplication instance = null;
    private static Map<Integer, Bitmap> imageMap = new HashMap<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Map<Integer, Bitmap> getImageMap() {
        return imageMap;
    }

    public static void alignPositionImageMap(final int position) {
        imageMap.remove(position);
        final Map<Integer, Bitmap> buf = new HashMap<>();
        for (Map.Entry<Integer, Bitmap> entry : imageMap.entrySet()) {
            if (position < entry.getKey()) {
                buf.put(entry.getKey() - 1, entry.getValue());
            }
            if (buf.size() > 0) {
                imageMap = buf;
            }
        }
    }

    public static void putImageMap(final int position, final Bitmap bitmap) {
        imageMap.put(position, bitmap);
    }

    public static void clearImageMap() {
        imageMap.clear();
    }
}
