package com.example.zaikokanri.db;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

public class InventoryData {

    @PrimaryKey
    public int id;

    public int count;
    public String comment;
    public Bitmap image;

    @ColumnInfo(name = "is_delete")
    public boolean isDelete;
    @ColumnInfo(name = "create_at")
    public Timestamp createAt;
    @ColumnInfo(name = "update_at")
    public Timestamp updateAt;
}
