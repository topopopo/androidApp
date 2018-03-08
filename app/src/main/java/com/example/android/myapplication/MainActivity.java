package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ent = (Button) findViewById(R.id.button1);
        ent.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1://検索
                Intent intent = new Intent(getApplicationContext(), BusinessCardEntActivity.class);//検索画面に遷移
                startActivity(intent);
                break;
        }
    }
}