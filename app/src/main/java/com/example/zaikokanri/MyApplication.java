package com.example.zaikokanri;

import android.app.Application;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private static MyApplication instance = new MyApplication();
    private static List<Bitmap> imageList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public Bitmap getImage(final int position) {
        return imageList.get(position);
    }

    public void addImage( final Bitmap bitmap, final int position) {
        imageList.remove(position);
        imageList.add(position, bitmap);
    }

    public void addImageSpace() {
        imageList.add(null);
    }

    public int imagesCount() {
        return imageList.size();
    }

    public void removeImage(final int position) {
        imageList.remove(position);
    }

}
