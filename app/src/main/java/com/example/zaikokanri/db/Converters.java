package com.example.zaikokanri.db;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

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
    public static Bitmap stringToBitmap(final String string) {
        final byte[] bytes = Base64.decode(string, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    @TypeConverter
    public static String bitmapToString(final Bitmap bitmap) {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        final byte[] bytes = stream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
}
