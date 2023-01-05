package com.example.zaikokanri.db;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;
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
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @TypeConverter
    public static Bitmap byteArrayToBitmap(final byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
