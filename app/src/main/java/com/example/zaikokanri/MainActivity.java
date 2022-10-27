package com.example.zaikokanri;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    TextView textView;
    Button plusButton;
    Button minusButton;
    Button addButton;
    TextView clock;
    Timer myTimer;
    MainTimerTask myTimerTask;
    List<CellData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Freemake");
        }

        textView = findViewById(R.id.number);
        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);
        addButton = findViewById(R.id.add);

        // 加算
        plusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count++;
                if (count > 9999) {
                    count = 9999;
                }
                textView.setText(formatThousand(count));
            }
        });

        // 減算
        minusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count--;
                if (count < 0) {
                    count = 0;
                }
                textView.setText(formatThousand(count));
            }
        });

        // ここから下は時計機能
        clock = findViewById(R.id.clock);

        // リスト
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addStringData(list);
            }
        });
    }

    private void addStringData(List<CellData> list) {
        TextView time = findViewById(R.id.clock);
        TextView number = findViewById(R.id.number);
        EditText comment = findViewById(R.id.comment);
        ListView listView = findViewById(R.id.list);

        CellData data = new CellData(time.getText().toString(), number.getText().toString(), comment.getText().toString());
        list.add(data);

        listView.setAdapter(new ListViewAdapter(this, R.layout.list, list));
    }

    // 3桁ごとにカンマを入れて返します
    private String formatThousand(int num) {
        DecimalFormat decFormat = new DecimalFormat("#,###");
        return decFormat.format(num);
    }

    @Override
    protected void onStop() {
        super.onStop();
        myTimer.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 100ミリ秒に１回タスクを実行する
        myTimer = new Timer();
        // タスクを作成
        myTimerTask = new MainTimerTask();
        myTimer.schedule(myTimerTask, 0, 100);
    }

    // itemのデータを保持するクラス
    class CellData {
        String time;
        String number;
        String comment;

        CellData(String time, String number, String comment) {
            this.time = time;
            this.number = number;
            this.comment = comment;
        }
    }

    class ViewHolder {
        TextView viewTime;
        TextView viewNumber;
        TextView viewComment;
    }

    // ArrayAdapterを継承したカスタムのアダプタークラス
    class ListViewAdapter extends ArrayAdapter<CellData> {
        CellData data;
        private LayoutInflater inflater;
        private int itemLayout;

        ListViewAdapter(Context context, int itemLayout, List<CellData> list) {
            super(context, 0, list);
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.itemLayout = itemLayout;
        }

        @Override
        public @NonNull
        View getView(int position, View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = inflater.inflate(itemLayout, parent, false);
                holder = new ViewHolder();
                holder.viewTime = convertView.findViewById(R.id.listTime);
                holder.viewNumber = convertView.findViewById(R.id.listNumber);
                holder.viewComment = convertView.findViewById(R.id.listComment);
                convertView.setTag(holder);

                // 偶数行の色を変更
                if (position % 2 == 0) {
                    convertView.setBackgroundColor(Color.rgb(100, 149, 237));
                }
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            data = getItem(position);
            if (data != null) {
                holder.viewTime.setText(data.time);
                holder.viewNumber.setText(data.number);
                holder.viewComment.setText(data.comment);
            }
            return convertView;
        }
    }

    // Timerで呼び出すタスクを作成
    public class MainTimerTask extends TimerTask {
        @Override
        public void run() {
            clock.setText((new SimpleDateFormat("HH:mm:ss")).format(new Date()));
        }
    }
}
