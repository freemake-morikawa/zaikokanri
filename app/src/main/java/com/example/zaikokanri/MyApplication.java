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

    public void setImage(final int position, final Bitmap bitmap) {
        if (imageList.size() < position) {
            imageList.add(null);
        } else {
            imageList.set(position, bitmap);
        }
    }

    public void removeImage(final int position) {
        imageList.remove(position);
    }

    public boolean hasImage(final int position) {
        if (imageList.get(position) == null) {
            return false;
        }
        return true;
    }

}
