package com.example.zaikokanri.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "inventory_data")
public class InventoryData {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(defaultValue = "0")
    @NonNull
    public int count;

    @ColumnInfo(defaultValue = "NULL")
    public String comment;

    @ColumnInfo(defaultValue = "NULL", typeAffinity = ColumnInfo.BLOB)
    public byte[] image;

    @ColumnInfo(name = "is_delete", defaultValue = "0")
    @NonNull
    public boolean isDelete;

    @ColumnInfo(name = "create_at", defaultValue = "CURRENT_TIMESTAMP")
    @NonNull
    public Timestamp createAt;

    @ColumnInfo(name = "update_at", defaultValue = "CURRENT_TIMESTAMP")
    @NonNull
    public Timestamp updateAt;
}