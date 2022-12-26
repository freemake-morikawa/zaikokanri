package com.example.zaikokanri.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InventoryDataDao {
    @Query("SELECT * FROM inventory_data WHERE is_delete = 0 ORDER BY update_at DESC")
    List<InventoryData> getAll();

    @Query("SELECT * FROM inventory_data WHERE id = (:id)")
    InventoryData getOne(final int id);

    @Insert
    void add(final InventoryData inventoryData);

    @Query("DELETE FROM inventory_data WHERE id = (:id)")
    void remove(final int id);
}
