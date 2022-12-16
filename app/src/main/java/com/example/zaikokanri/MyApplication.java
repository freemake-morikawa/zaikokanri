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

    public static MyApplication getInstance() {
        return instance;
    }

    public static Map<Integer, Bitmap> getImageMap() {
        return imageMap;
    }

    public static void setImageMap(Map<Integer, Bitmap> imageMap) {
        MyApplication.imageMap = imageMap;
    }
}
