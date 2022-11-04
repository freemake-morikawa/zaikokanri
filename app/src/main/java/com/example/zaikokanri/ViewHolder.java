package com.example.zaikokanri;

import android.widget.CheckBox;
import android.widget.TextView;

public final class ViewHolder {
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TextView getTimeTextView() {
        return timeTextView;
    }

    public TextView getStockCountTextView() {
        return stockCountTextView;
    }

    public TextView getCommentTextView() {
        return commentTextView;
    }
}
