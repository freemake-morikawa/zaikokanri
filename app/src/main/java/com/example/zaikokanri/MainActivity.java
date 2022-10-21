package com.example.zaikokanri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

// Debug
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    TextView textView;
    Button plusButton;
    Button minusButton;
    TextView clock;
    Timer myTimer;
    MainTimerTask myTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ActionBar(画面上部のバー)の表示文字を変更する
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Freemake");
        }

        // TextViewの数値部分を取得！！
        textView = findViewById(R.id.number);
        // Buttonの取得！
        plusButton = findViewById(R.id.plusButton);
        minusButton = findViewById(R.id.minusButton);

        // 加算
        plusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count++;
                if(count > 9999) {
                    count = 9999;
                }
                textView.setText(formatThousand(count));
            }
        });

        // 減算
        minusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                count--;
                if(count < 0){
                    count = 0;
                }
                textView.setText(formatThousand(count));
            }
        });

        // ここから下は時計機能
        clock = findViewById(R.id.clock);


    }

    // 3桁ごとにカンマを入れて返します
    private String formatThousand(int num) {
        DecimalFormat decFormat = new DecimalFormat("#,###");
        return decFormat.format(num);
    }

    // Timerで呼び出すタスクを作成
    public class MainTimerTask extends TimerTask {
        @Override
        public void run(){
            clock.setText((new SimpleDateFormat("HH:mm:ss")).format(new Date()));

            // /* Debug
            Log.v("Test",(new SimpleDateFormat("HH:mm:ss")).format(new Date()));
            // */
        }
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

}
