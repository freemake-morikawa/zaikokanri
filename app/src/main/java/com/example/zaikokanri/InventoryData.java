package com.example.zaikokanri;

public class InventoryData {
    private boolean isCheck;
    private String time;
    private String stockCount;
    private String comment;

    public InventoryData(final String time, final String stockCount, final String comment) {
        this.isCheck = false;
        this.time = time;
        this.stockCount = stockCount;
        this.comment = comment;
    }

    public void setCheck(final boolean check) {
        this.isCheck = check;
    }

    public boolean isChecked() {
        return isCheck;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setStockCount(final String stockCount) {
        this.stockCount = stockCount;
    }

    public String getStockCount() {
        return stockCount;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
