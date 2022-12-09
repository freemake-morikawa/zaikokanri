package com.example.zaikokanri;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public final class ViewHolder {
    private CheckBox checkBox;
    private TextView timeTextView;
    private TextView stockCountTextView;
    private TextView commentTextView;
    private Button detailButton;
    private Button deleteButton;

    public ViewHolder(CheckBox checkBox,
                      TextView timeTextView,
                      TextView stockCountTextView,
                      TextView commentTextView,
                      Button detailButton,
                      Button deleteButton) {
        this.checkBox = checkBox;
        this.timeTextView = timeTextView;
        this.stockCountTextView = stockCountTextView;
        this.commentTextView = commentTextView;
        this.detailButton = detailButton;
        this.deleteButton = deleteButton;
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

    public Button getDetailButton() {
        return detailButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
