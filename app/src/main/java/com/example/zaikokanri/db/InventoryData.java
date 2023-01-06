package com.example.zaikokanri.db;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Entity(tableName = "inventory_data")
public class InventoryData {

    private static final String TIME_FORMAT_PATTERN = "hh:mm:ss";

    @PrimaryKey(autoGenerate = true)
    private final int id;

    @ColumnInfo(defaultValue = "0")
    private final int count;

    @ColumnInfo(defaultValue = "NULL")
    private final String comment;

    @ColumnInfo(defaultValue = "NULL", typeAffinity = ColumnInfo.BLOB)
    private final Bitmap image;

    @ColumnInfo(name = "is_delete", defaultValue = "0")
    private final boolean isDelete;

    @ColumnInfo(name = "create_at", defaultValue = "CURRENT_TIMESTAMP")
    @NonNull
    private final Timestamp createAt;

    @ColumnInfo(name = "update_at", defaultValue = "CURRENT_TIMESTAMP")
    @NonNull
    private final Timestamp updateAt;

    public InventoryData(final int id, final int count, final String comment,
                         final Bitmap image, final boolean isDelete,
                         @NonNull final Timestamp createAt, @NonNull final Timestamp updateAt) {
        this.id = id;
        this.count = count;
        this.comment = comment;
        this.image = image;
        this.isDelete = isDelete;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public String getComment() {
        return comment;
    }

    public Bitmap getImage() {
        return image;
    }

    public boolean isDelete() {
        return isDelete;
    }

    @NonNull
    public Timestamp getCreateAt() {
        return createAt;
    }

    @NonNull
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public String getCreateAtString() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT_PATTERN);
        return simpleDateFormat.format(createAt);
    }
}