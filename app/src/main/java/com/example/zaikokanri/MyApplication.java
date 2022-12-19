package com.example.zaikokanri;

import android.app.Application;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private static MyApplication instance = null;
    private static List<Bitmap> imageList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Bitmap getImage(final int position) {
        return imageList.get(position);
    }

    public static void addImage( final Bitmap bitmap, final int position) {
        imageList.remove(position);
        imageList.add(position, bitmap);
    }

    public static void addImageSpace() {
        imageList.add(null);
    }

    public static boolean isEmpty() {
        return imageList.isEmpty();
    }

    public static void removeImage(final int position) {
        imageList.remove(position);
    }

}
