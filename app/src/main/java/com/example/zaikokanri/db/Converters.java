package com.example.zaikokanri.db;

import android.net.Uri;

import androidx.room.TypeConverter;

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
    public static Uri stringToUri(final String string) {
        return Uri.parse(string);
    }

    @TypeConverter
    public static String uriToString(final Uri uri) {
        if (uri == null) {
            return null;
        }
        return uri.toString();
    }
}
