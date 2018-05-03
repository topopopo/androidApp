package com.example.android.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BusinessCardActivity extends AppCompatActivity {

    private String aCompany;

    private TextView company;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_card);

        company = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        aCompany = intent.getStringExtra("Co_Name");

        this.company.setText(aCompany);

    }





}

