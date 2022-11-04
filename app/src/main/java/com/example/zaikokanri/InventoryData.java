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

    public String getTime() {
        return time;
    }

    public String getStockCount() {
        return stockCount;
    }

    public String getComment() {
        return comment;
    }
}
