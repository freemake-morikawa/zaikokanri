package com.example.zaikokanri;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class InventoryInfo {

    private static final String SIMPLE_DATE_FORMAT_PATTERN = "HH:mm:ss";

    private boolean isCheck;
    private Date time;
    private int inventoryCount;
    private String comment;

    public InventoryInfo(final String time, final String inventoryCount, final String comment) {
        this.time = parseDate(time);
        this.inventoryCount = parseInt(inventoryCount);
        this.comment = comment;
    }

    public void setCheck(final boolean check) {
        this.isCheck = check;
    }

    public boolean isChecked() {
        return isCheck;
    }

    public String getTimeString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN);
        return simpleDateFormat.format(time);
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public String getInventoryCountString() {
        return String.valueOf(inventoryCount);
    }

    public String getComment() {
        return comment;
    }

    private Date parseDate(final String timeString) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(SIMPLE_DATE_FORMAT_PATTERN);
        Date time = null;
        try {
            time = simpleDateFormat.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    private int parseInt(final String inventoryCountString) {

        int inventoryCount = 0;
        try {
            inventoryCount = NumberFormat.getInstance().parse(inventoryCountString).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return inventoryCount;
    }
}
