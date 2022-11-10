package com.example.zaikokanri;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int STOCK_COUNT_MIN = 0;
    private static final int STOCK_COUNT_MAX = 9_999;
    private static final int TIMER_DELAY = 0;
    private static final int TIMER_PERIOD = 100;
    private static final String DEFAULT_NUMBER_FORMAT = "#,###";

    private int stockCount;
    private ArrayAdapter<InventoryData> adapter;
    private TextView textViewClock;
    private Timer timer;

    public MainActivity() {
        stockCount = 0;
        adapter = null;
        textViewClock = null;
        timer = null;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.action_bar_text);

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        timer = new Timer();
        timer.schedule(new TextViewClockTimerTask(), TIMER_DELAY, TIMER_PERIOD);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    // 時計のタスククラス
    private class TextViewClockTimerTask extends TimerTask {
        @Override
        public void run() {
            final String clockText = new SimpleDateFormat(getString(R.string.format_24_hour)).format(new Date());
            textViewClock.setText(clockText);
        }
    }

    // アプリ起動時のView操作
    private void initView (){
        // 加算・減算
        stockCount = STOCK_COUNT_MIN;

        final Button buttonPlus = findViewById(R.id.button_plus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                stockCount++;
                if (STOCK_COUNT_MAX < stockCount) {
                    stockCount = STOCK_COUNT_MAX;
                }

                final TextView textViewStockCount = findViewById(R.id.text_view_stock_count);
                textViewStockCount.setText(formatCommaThreeDigit(stockCount));
            }
        });

        final Button minusButton = findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                stockCount--;
                if (stockCount < STOCK_COUNT_MIN) {
                    stockCount = STOCK_COUNT_MIN;
                }

                final TextView textViewStockCount = findViewById(R.id.text_view_stock_count);
                textViewStockCount.setText(formatCommaThreeDigit(stockCount));
            }
        });

        // 時計の処理のためViewを取得
        textViewClock = findViewById(R.id.text_view_clock);

        // リスト追加
        final ListView listView = findViewById(R.id.list_view_inventory_data_list);
        adapter = new ListViewAdapter(this, R.layout.list_item);

        final Button buttonAddInventoryData = findViewById(R.id.button_add_inventory_data);
        buttonAddInventoryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final TextView textViewClock= findViewById(R.id.text_view_clock);
                final TextView textViewStockCount = findViewById(R.id.text_view_stock_count);
                final EditText editTextComment = findViewById(R.id.edit_text_comment);

                final InventoryData inventoryData = new InventoryData(
                        textViewClock.getText().toString(),
                        textViewStockCount.getText().toString(),
                        editTextComment.getText().toString());
                adapter.add(inventoryData);
                listView.setAdapter(adapter);
            }
        });
    }

    // 3桁を超える場合、カンマを入れる
    private String formatCommaThreeDigit(final int number) {
        final DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_NUMBER_FORMAT);
        return decimalFormat.format(number);
    }
}
