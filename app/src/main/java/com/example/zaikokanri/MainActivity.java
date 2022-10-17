package com.example.zaikokanri;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    int count = 0;
    TextView textView;
    Button plusButton;
    Button minusButton;

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

    }

    // 3桁ごとにカンマを入れて返します
    private String formatThousand(int num) {
        DecimalFormat decFormat = new DecimalFormat("#,###");
        return decFormat.format(num);
    }

}
