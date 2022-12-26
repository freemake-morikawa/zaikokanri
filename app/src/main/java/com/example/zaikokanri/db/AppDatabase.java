package com.example.zaikokanri.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {InventoryData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract InventoryDataDao inventoryDataDao();
}