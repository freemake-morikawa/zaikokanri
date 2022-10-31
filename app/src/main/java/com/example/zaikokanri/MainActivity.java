package com.example.zaikokanri;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    List<CellData> cellDataList;
    private int count;
    private TextView clockText;
    private Timer timer;
    private ArrayAdapter<CellData> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初期化
        cellDataList = new ArrayList<>();
        adapter = new ListViewAdapter(this, R.layout.list);

        // アクションバーの変更
        getSupportActionBar().setTitle(R.string.action_bar);

        // 時計のViewを取得
        clockText = findViewById(R.id.clock_text);

        // 加算・減算
        final TextView countText = findViewById(R.id.count_text);
        final Button plusButton = findViewById(R.id.plus_button);

        final int COUNT_MAX = 9999;
        final int COUNT_MIN = 0;

        count = COUNT_MIN;

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count > COUNT_MAX) {
                    count = COUNT_MAX;
                }
                countText.setText(formatThousand(count));
            }
        });

        final Button minusButton = findViewById(R.id.minus_button);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if (count < COUNT_MIN) {
                    count = COUNT_MIN;
                }
                countText.setText(formatThousand(count));
            }
        });

        // リスト追加
        final Button addButton = findViewById(R.id.add_button);
        final ListView listView = findViewById(R.id.list_view);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView time = findViewById(R.id.clock_text);
                TextView count = findViewById(R.id.count_text);
                EditText comment = findViewById(R.id.comment_text);

                CellData cellData = new CellData(time.getText().toString(), count.getText().toString(), comment.getText().toString());
                cellDataList.add(cellData);
                adapter.add(cellData);
                listView.setAdapter(adapter);
            }
        });
    }

    // 時計を動かす
    private final int TIMER_DELAY = 0;
    private final int TIMER_PERIOD = 100;

    @Override
    protected void onStart() {
        super.onStart();
        timer = new Timer();
        timer.schedule(new MainTimerTask(), TIMER_DELAY, TIMER_PERIOD);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    // 3桁ごとにカンマ挿入
    private String formatThousand(int num) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(num);
    }

    // リストアイテムの削除
    private void deleteListItem(int position) {
        adapter.remove(cellDataList.remove(position));
        adapter.notifyDataSetChanged();
    }

    // データを保持するクラス
    private class CellData {
        boolean check;
        String time;
        String count;
        String comment;

        CellData(String time, String count, String comment) {
            this.check = false;
            this.time = time;
            this.count = count;
            this.comment = comment;
        }
    }

    // カスタムアダプタークラス
    private class ListViewAdapter extends ArrayAdapter<CellData> {

        private CellData cellDataItem;
        private LayoutInflater inflater;
        private int itemLayout;

        ListViewAdapter(Context context, int itemLayout) {
            super(context, itemLayout);
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemLayout = itemLayout;
        }

        @Override
        public @NonNull
        View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = inflater.inflate(itemLayout, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.viewCheck = convertView.findViewById(R.id.listCheckBox);
                viewHolder.viewTime = convertView.findViewById(R.id.listTime);
                viewHolder.viewCount = convertView.findViewById(R.id.listCount);
                viewHolder.viewComment = convertView.findViewById(R.id.listComment);
                convertView.setTag(viewHolder);

                // チェックボックスのリスナー
                viewHolder.viewCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (buttonView.isPressed()) {
                            // リストデータを変更
                            final CellData buf = cellDataList.get(position);
                            buf.check = isChecked;
                            cellDataList.set(position, buf);

                            // 背景色を変更
                            final View parentView = (View) buttonView.getParent();
                            changeBackgroundColor(parentView, position, isChecked);
                        }
                    }
                });

                // 削除ボタンのリスナー
                convertView.findViewById(R.id.listButton).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteListItem(position);
                    }
                });
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            cellDataItem = getItem(position);
            if (cellDataItem != null) {
                viewHolder.viewCheck.setChecked(cellDataItem.check);
                viewHolder.viewTime.setText(cellDataItem.time);
                viewHolder.viewCount.setText(cellDataItem.count);
                viewHolder.viewComment.setText(cellDataItem.comment);
            }

            // 背景色の変更
            changeBackgroundColor(convertView, position, viewHolder.viewCheck.isChecked());

            return convertView;
        }

        // 色変え処理
        private final int EVEN_ITEM_BACKGROUND_COLOR = Color.rgb(100, 149, 237);
        private final int ODD_ITEM_BACKGROUND_COLOR = Color.WHITE;
        private final int CHECKED_ITEM_BACKGROUND_COLOR = Color.GREEN;

        private void changeBackgroundColor(View view, int position, boolean isChecked) {
            if (position % 2 == 0) {
                view.setBackgroundColor(EVEN_ITEM_BACKGROUND_COLOR);
            } else {
                view.setBackgroundColor(ODD_ITEM_BACKGROUND_COLOR);
            }

            if (isChecked) {
                view.setBackgroundColor(CHECKED_ITEM_BACKGROUND_COLOR);
            }
        }

        // Viewを保持するクラス
        private class ViewHolder {
            CheckBox viewCheck;
            TextView viewTime;
            TextView viewCount;
            TextView viewComment;
        }
    }

    // Timerで呼び出すタスクのクラス
    public class MainTimerTask extends TimerTask {
        @Override
        public void run() {
            clockText.setText((new SimpleDateFormat("HH:mm:ss")).format(new Date()));
        }
    }
}
