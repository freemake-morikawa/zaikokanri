package com.example.zaikokanri;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.room.Room;

import com.example.zaikokanri.db.AppDatabase;
import com.example.zaikokanri.db.InventoryData;
import com.example.zaikokanri.db.InventoryDataDao;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    private static final String DATABASE_NAME = "inventory_data";

    private static MyApplication instance = new MyApplication();
    private static final List<Bitmap> imageList = new ArrayList<>();
    private static InventoryDataDao dao;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        final AppDatabase db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME).build();
        dao = db.inventoryDataDao();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public Bitmap getImage(final int position) {
        return imageList.get(position);
    }

    public void setImage(final int position, final Bitmap bitmap) {
        if (imageList.size() < position) {
            imageList.add(bitmap);
            return;
        }
        imageList.set(position, bitmap);
    }

    public void removeImage(final int position) {
        imageList.remove(position);
    }

    public boolean hasImage(final int position) {
        if (imageList.size() < position) {
            return false;
        }
        return imageList.get(position) != null;
    }

    public static InventoryDataDao getDao() {
        return dao;
    }

    public static List<InventoryData> getInventoryDataList() {
        return dao.getAll();
    }
}
