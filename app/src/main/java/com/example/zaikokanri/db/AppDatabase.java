package com.example.zaikokanri.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {InventoryData.class}, version = 1)
@TypeConverters({Converters.class})

public abstract class AppDatabase extends RoomDatabase {
    public abstract InventoryDataDao inventoryDataDao();
}