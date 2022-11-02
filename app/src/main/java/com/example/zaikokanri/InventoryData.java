package com.example.zaikokanri;

public class InventoryData {
    private boolean check;
    private String time;
    private String stockCount;
    private String comment;

    public InventoryData(final String time, final String stockCount, final String comment) {
        this.check = false;
        this.time = time;
        this.stockCount = stockCount;
        this.comment = comment;
    }

    public void setCheck(final boolean check) {
        this.check = check;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public void setStockCount(final String stockCount) {
        this.stockCount = stockCount;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public boolean getCheck() {
        return check;
    }

    public String getTime() {
        return time;
    }

    public String getStockCount(){
        return stockCount;
    }

    public String getComment() {
        return comment;
    }
}
