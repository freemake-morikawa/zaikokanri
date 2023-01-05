package com.example.zaikokanri.db;

import android.graphics.Bitmap;

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
    public Bitmap image;

    @ColumnInfo(name = "is_delete", defaultValue = "0")
    @NonNull
    public boolean isDelete;

    @ColumnInfo(name = "create_at", defaultValue = "CURRENT_TIMESTAMP")
    @NonNull
    public Timestamp createAt;

    @ColumnInfo(name = "update_at", defaultValue = "CURRENT_TIMESTAMP")
    @NonNull
    public Timestamp updateAt;

    public InventoryData(final int id, final int count, final String comment,
                         final Bitmap image, final boolean isDelete, final Timestamp createAt,
                         final Timestamp updateAt) {
        this.id = id;
        this.count = count;
        this.comment = comment;
        this.image = image;
        this.isDelete = isDelete;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}