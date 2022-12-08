package com.example.zaikokanri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class InventoryItemDetailsDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_item_details_display);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();

        final String time = intent.getStringExtra(Constants.INTENT_KEY_TIME_STRING);
        final TextView timeTextView = findViewById(R.id.details_display_activity_time_text_view);
        timeTextView.setText(time);

        final int inventoryCount = intent.getIntExtra(Constants.INTENT_KEY_INVENTORY_COUNT, 0);
        final TextView inventoryCountTextView = findViewById(R.id.details_display_activity_inventory_count_text_view);
        inventoryCountTextView.setText(String.valueOf(inventoryCount));

        final String comment = intent.getStringExtra(Constants.INTENT_KEY_COMMENT);
        final TextView commentTextView = findViewById(R.id.details_display_activity_comment_text_view);
        commentTextView.setText(comment);

    }
}
