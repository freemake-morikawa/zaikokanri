package com.example.zaikokanri;

public class InventoryData {
    boolean check;
    String time;
    String count;
    String comment;

    InventoryData(final String time, final String count, final String comment) {
        this.check = false;
        this.time = time;
        this.count = count;
        this.comment = comment;
    }
}
