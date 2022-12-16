package com.example.zaikokanri;

import android.app.Application;
import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

public class MyApplication extends Application {

    public static Map<Integer, Bitmap> imageMap = new HashMap<>();
}
