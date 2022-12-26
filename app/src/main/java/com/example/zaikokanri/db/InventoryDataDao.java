package com.example.zaikokanri.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InventoryDataDao {
    @Query("SELECT * FROM inventory_data")
    List<InventoryData> getAll();

    @Insert
    void insertAll(InventoryData inventoryData);

    @Delete
    void delete(InventoryData inventoryData);

    // TODO : 必要に応じてSQL文を追加
}
