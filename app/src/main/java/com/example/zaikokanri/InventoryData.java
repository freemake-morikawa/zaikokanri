package com.example.zaikokanri;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class InventoryData {

    private static final String SIMPLE_DATE_FORMAT_PATTERN = "HH:mm:ss";

    private boolean isCheck;
    private Date time;
    private int stockCount;
    private String comment;

    public InventoryData(final String time, final String stockCount, final String comment) {
        this.time = parseDate(time);
        this.stockCount = parseInt(stockCount);
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

    public int getStockCount() {
        return stockCount;
    }

    public String getStockCountString() {
        return Integer.valueOf(stockCount).toString();
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

    private int parseInt(final String stockCountString) {

        int stockCount = 0;
        try {
            stockCount = NumberFormat.getInstance().parse(stockCountString).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return stockCount;
    }
}
