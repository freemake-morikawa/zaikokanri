package com.example.zaikokanri.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InventoryDataDao {
    @Query("SELECT * FROM inventory_data WHERE is_delete = 0 ORDER BY id")
    List<InventoryData> getAll();

    @Query("SELECT * FROM inventory_data WHERE is_delete = 0 ORDER BY id LIMIT 1 OFFSET (:position - 1)")
    InventoryData loadInventoryDataFromPosition(final int position);

    @Insert
    void insertAll(final InventoryData inventoryData);

    @Query("DELETE FROM inventory_data WHERE id = (:id)")
    void deleteFromPosition(final int id);

    // TODO : 必要に応じてSQL文を追加
}
