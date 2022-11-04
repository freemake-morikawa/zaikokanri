package com.example.zaikokanri;

import android.widget.CheckBox;
import android.widget.TextView;

public class ViewHolder {
    private CheckBox checkBox;
    private TextView timeTextView;
    private TextView stockCountTextView;
    private TextView commentTextView;

    public ViewHolder(CheckBox checkBox, TextView timeTextView, TextView stockCountTextView, TextView commentTextView) {
        this.checkBox = checkBox;
        this.timeTextView = timeTextView;
        this.stockCountTextView = stockCountTextView;
        this.commentTextView = commentTextView;
    }

    public void setCheckBox(final CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setTimeTextView(final TextView timeTextView) {
        this.timeTextView = timeTextView;
    }

    public TextView getTimeTextView() {
        return timeTextView;
    }

    public void setStockCountTextView(final TextView stockCountTextView) {
        this.stockCountTextView = stockCountTextView;
    }

    public TextView getStockCountTextView() {
        return stockCountTextView;
    }

    public void setCommentTextView(final TextView commentTextView) {
        this.commentTextView = commentTextView;
    }

    public TextView getCommentTextView() {
        return commentTextView;
    }
}
