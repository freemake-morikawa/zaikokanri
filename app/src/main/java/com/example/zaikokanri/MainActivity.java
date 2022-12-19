package com.example.zaikokanri;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int INVENTORY_COUNT_MIN = 0;
    private static final int INVENTORY_COUNT_MAX = 9_999;
    private static final int TIMER_DELAY = 0;
    private static final int TIMER_PERIOD = 100;
    private static final String DEFAULT_NUMBER_FORMAT = "#,###";
    private static final String TAG_DIALOG = "dialog";

    private int inventoryCount;
    private ArrayAdapter<InventoryInfo> adapter;
    private TextView clockTextView;
    private Timer timer;

    public MainActivity() {
        inventoryCount = 0;
        adapter = null;
        clockTextView = null;
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

    @Override
    public void onClick(final View v) {
        if (v.getTag() == null) {
            return;
        }

        final int position = (int) v.getTag();
        final InventoryInfo inventoryInfo = adapter.getItem(position);

        final Intent intent = new Intent(this, InventoryItemDetailActivity.class);
        intent.putExtra(Constants.INTENT_KEY_POSITION, position);
        intent.putExtra(Constants.INTENT_KEY_TIME_STRING, inventoryInfo.getTimeString());
        intent.putExtra(Constants.INTENT_KEY_INVENTORY_COUNT, inventoryInfo.getInventoryCount());
        intent.putExtra(Constants.INTENT_KEY_COMMENT, inventoryInfo.getComment());

        startActivity(intent);
    }

    // 時計のタスククラス
    private class TextViewClockTimerTask extends TimerTask {
        @Override
        public void run() {
            final String clockText =
                    new SimpleDateFormat(getString(R.string.format_24_hour)).format(new Date());
            clockTextView.setText(clockText);
        }
    }

    // アプリ起動時のView操作
    private void initView() {
        // 加算・減算
        inventoryCount = INVENTORY_COUNT_MIN;

        final Button plusButton = findViewById(R.id.plus_button);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                inventoryCount++;
                if (INVENTORY_COUNT_MAX < inventoryCount) {
                    inventoryCount = INVENTORY_COUNT_MAX;
                }

                final TextView inventoryCountTextView = findViewById(R.id.inventory_count_text_view);
                inventoryCountTextView.setText(formatCommaThreeDigit(inventoryCount));
            }
        });

        final Button minusButton = findViewById(R.id.minus_button);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                inventoryCount--;
                if (inventoryCount < INVENTORY_COUNT_MIN) {
                    inventoryCount = INVENTORY_COUNT_MIN;
                }

                final TextView inventoryCountTextView = findViewById(R.id.inventory_count_text_view);
                inventoryCountTextView.setText(formatCommaThreeDigit(inventoryCount));
            }
        });

        // 時計の処理のためViewを取得
        clockTextView = findViewById(R.id.clock_text_view);

        // リスト追加
        final ListView listView = findViewById(R.id.inventory_info_list_view);
        adapter = new InventoryInfoListViewAdapter(this, R.layout.list_item, this);

        final Button addInventoryInfoButton = findViewById(R.id.add_inventory_info_button);
        addInventoryInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final TextView clockTextView = findViewById(R.id.clock_text_view);
                final TextView inventoryCountTextView = findViewById(R.id.inventory_count_text_view);
                final EditText commentEditText = findViewById(R.id.comment_edit_text);

                final InventoryInfo inventoryInfo = new InventoryInfo(
                        clockTextView.getText().toString(),
                        inventoryCountTextView.getText().toString(),
                        commentEditText.getText().toString());
                adapter.add(inventoryInfo);
                MyApplication.getInstance().addImageSpace();
                listView.setAdapter(adapter);
            }
        });

        // クリア
        final Button clearButton = findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                adapter.clear();
            }
        });

        // 選択された合計数量
        final Button checkedTotalInventoryCountShowButton =
                findViewById(R.id.checked_total_inventory_count_show_button);
        checkedTotalInventoryCountShowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (!isListItemChecked()) {
                    return;
                }

                final int totalInventoryCount = sumCheckedInventoryCount();
                final DialogFragment dialogFragment = new TotalInventoryCountDialogFragment();
                final Bundle args = new Bundle();

                args.putInt(Constants.BUNDLE_KEY_COUNT, totalInventoryCount);
                dialogFragment.setArguments(args);
                dialogFragment.show(getSupportFragmentManager(), TAG_DIALOG);
            }
        });
    }

    // 3桁を超える場合、カンマを入れる
    private String formatCommaThreeDigit(final int number) {
        final DecimalFormat decimalFormat = new DecimalFormat(DEFAULT_NUMBER_FORMAT);
        return decimalFormat.format(number);
    }

    // 合計数量を求める
    private int sumCheckedInventoryCount() {
        int totalInventoryCount = 0;

        for (int i = 0; i < adapter.getCount(); i++) {
            final InventoryInfo inventoryInfo = adapter.getItem(i);
            totalInventoryCount += inventoryInfo.getInventoryCount();
        }

        return totalInventoryCount;
    }

    // 選択されている項目があるかの確認
    private boolean isListItemChecked() {
        for (int i = 0; i < adapter.getCount(); i++) {
            final InventoryInfo inventoryInfo = adapter.getItem(i);
            if (inventoryInfo.isChecked()) {
                return true;
            }
        }
        return false;
    }
}
