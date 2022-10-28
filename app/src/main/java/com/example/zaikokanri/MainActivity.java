package com.example.zaikokanri;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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

    List<CellData> list;
    private int count;
    private TextView clockText;
    private ListView listView;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初期化
        count = 0;
        list = new ArrayList<>();

        // アクションバーの変更
        getSupportActionBar().setTitle("Freemake");

        // Viewの取得
        final TextView countText = findViewById(R.id.count);
        final Button plusButton = findViewById(R.id.plusButton);
        final Button minusButton = findViewById(R.id.minusButton);
        final Button addButton = findViewById(R.id.addButton);

        clockText = findViewById(R.id.clock);
        listView = findViewById(R.id.list);

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
                addStringData(list);
            }
        });
    }

    // リスト追加処理
    private void addStringData(List<CellData> list) {
        TextView time = findViewById(R.id.clock);
        TextView count = findViewById(R.id.count);
        EditText comment = findViewById(R.id.comment);
        ListView listView = findViewById(R.id.list);

        CellData data = new CellData(time.getText().toString(), count.getText().toString(), comment.getText().toString());
        list.add(data);

        listView.setAdapter(new ListViewAdapter(this, R.layout.list, list));
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
        private CellData data;
        private LayoutInflater inflater;
        private int itemLayout;

        ListViewAdapter(Context context, int itemLayout, List<CellData> list) {
            super(context, 0, list);
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemLayout = itemLayout;
        }

        @Override
        public @NonNull
        View getView(final int position, View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            Log.i("Test", "positionは" + position + "です");
            if (convertView == null) {
                convertView = inflater.inflate(itemLayout, parent, false);
                holder = new ViewHolder();
                holder.viewCheck = convertView.findViewById(R.id.listCheckBox);
                holder.viewTime = convertView.findViewById(R.id.listTime);
                holder.viewCount = convertView.findViewById(R.id.listCount);
                holder.viewComment = convertView.findViewById(R.id.listComment);
                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            data = getItem(position);
            if (data != null) {
                holder.viewTime.setText(data.time);
                holder.viewCount.setText(data.count);
                holder.viewComment.setText(data.comment);
            }
            return convertView;
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
