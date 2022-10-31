package com.example.zaikokanri;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
    private ListView listView;

    private Timer timer;

    private ArrayAdapter<CellData> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初期化
        count = 0;
        cellDataList = new ArrayList<>();
        adapter = new ListViewAdapter(this, R.layout.list);

        // アクションバーの変更
        getSupportActionBar().setTitle(R.string.action_bar);

        // Viewの取得
        final TextView countText = findViewById(R.id.count_text);
        final Button plusButton = findViewById(R.id.plus_button);
        final Button minusButton = findViewById(R.id.minus_button);
        final Button addButton = findViewById(R.id.add_button);

        clockText = findViewById(R.id.clock_text);
        listView = findViewById(R.id.list_view);

        // 加算・減算
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count > 9999) {
                    count = 9999;
                }
                countText.setText(formatThousand(count));
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if (count < 0) {
                    count = 0;
                }
                countText.setText(formatThousand(count));
            }
        });

        // リスト追加
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

    @Override
    protected void onStart() {
        super.onStart();
        timer = new Timer();
        timer.schedule(new MainTimerTask(), 0, 100);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }

    // 3桁ごとにカンマ挿入
    private String formatThousand(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
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

                viewHolder.viewCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (buttonView.isPressed()) {
                            // リストデータを変更
                            final CellData buf = cellDataList.get(position);
                            buf.check = isChecked;
                            cellDataList.set(position, buf);
                            Log.i("Test", "cellDataList[" + position + "]のcheckに[" + isChecked + "]を入れました");

                            // 背景色を変更
                            final View parentView = (View) buttonView.getParent();
                            changeBackgroundColor(parentView, position, isChecked);
                        }
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
        private void changeBackgroundColor(View view, int position, boolean isChecked) {
            if (position % 2 == 0) {
                view.setBackgroundColor(Color.rgb(100, 149, 237));
            } else {
                view.setBackgroundColor(Color.WHITE);
            }

            if (isChecked) {
                view.setBackgroundColor(Color.GREEN);
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
