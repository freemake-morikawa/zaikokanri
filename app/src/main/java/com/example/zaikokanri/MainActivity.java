package com.example.zaikokanri;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private int stockCount;
    private ArrayAdapter<InventoryData> adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.action_bar_text);

        // 加算・減算
        final TextView stockCountTextView = findViewById(R.id.stock_count_textview);
        final Button plusButton = findViewById(R.id.plus_button);
        final Button minusButton = findViewById(R.id.minus_button);

        final int STOCK_COUNT_MIN = 0;
        final int STOCK_COUNT_MAX = 9999;

        stockCount = STOCK_COUNT_MIN;

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                stockCount++;
                if (stockCount > STOCK_COUNT_MAX) {
                    stockCount = STOCK_COUNT_MAX;
                }
                stockCountTextView.setText(formatCommaThreeDigit(stockCount));
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                stockCount--;
                if (stockCount < STOCK_COUNT_MIN) {
                    stockCount = STOCK_COUNT_MIN;
                }
                stockCountTextView.setText(formatCommaThreeDigit(stockCount));
            }
        });

        // 時計の処理のためViewを取得
        clockTextView = findViewById(R.id.clock_textview);

        // リスト追加
        final Button addButton = findViewById(R.id.add_button);
        final ListView listView = findViewById(R.id.inventory_data_list_listview);
        adapter = new ListViewAdapter(this, R.layout.list_item);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final TextView clockTextView = findViewById(R.id.clock_textview);
                final TextView stockCountTextView = findViewById(R.id.stock_count_textview);
                final EditText commentEditText = findViewById(R.id.comment_edittext);

                final InventoryData inventoryData = new InventoryData(
                        clockTextView.getText().toString(),
                        stockCountTextView.getText().toString(),
                        commentEditText.getText().toString());
                adapter.add(inventoryData);
                listView.setAdapter(adapter);
            }
        });
    }

    // 3桁を超える場合、カンマを入れる
    private String formatCommaThreeDigit(final int number) {
        final DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    // 時計処理
    private static final int TIMER_DELAY = 0;
    private static final int TIMER_PERIOD = 100;
    private TextView clockTextView;
    private Timer timer;

    @Override
    protected void onStart() {
        super.onStart();
        timer = new Timer();
        timer.schedule(new MyTimerTask(), TIMER_DELAY, TIMER_PERIOD);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            final String clockText = new SimpleDateFormat(getString(R.string.format_24_hour)).format(new Date());
            clockTextView.setText(clockText);
        }
    }
}
