package com.example.zaikokanri.db;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.sql.Timestamp;

public class Converters {
    @TypeConverter
    public static Timestamp longToTimestamp(final Long value) {
        return value == null ? null : new Timestamp(value);
    }

    @TypeConverter
    public static Long timestampToLong(final Timestamp timestamp) {
        return timestamp == null ? null : timestamp.getTime();
    }

    @TypeConverter
    public static byte[] bitmapToByteArray(final Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(byteBuffer);
        return byteBuffer.array();
    }
    
    @TypeConverter
    public static Bitmap byteArrayToBitmap(final byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
