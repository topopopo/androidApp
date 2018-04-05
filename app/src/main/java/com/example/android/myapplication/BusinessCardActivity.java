package com.example.android.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardActivity extends AppCompatActivity {
    private TextView company;
    private TextView dept;
    private TextView user;
    private TextView tel;
    private TextView mail;
    private TextView rmrks;
    private List<MyListItem> items;

    private String columns[] = {BizCardDb.COL_CO_NAME,BizCardDb.COL_DEPT_NAME,BizCardDb.COL_USER_NAME,BizCardDb.COL_TEL,BizCardDb.COL_MAIL,BizCardDb.COL_RMRKS};
    private String column = BizCardDb.COL_ID;
    private String name[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_card);

        BizCardDb dbAdapter = new BizCardDb(this);
        dbAdapter.openDB();     // DBの読み込み(読み書きの方)

        // itemsのArrayList生成
        items = new ArrayList<>();

        /*遷移元の引数*/
        //name =

        //カーソルに代入
        Cursor c = dbAdapter.searchDB(columns,column,name);

        company = (TextView) findViewById(R.id.textView2);
        dept = (TextView) findViewById(R.id.textView4);
        user = (TextView) findViewById(R.id.textView6);
        tel = (TextView) findViewById(R.id.textView8);
        mail = (TextView) findViewById(R.id.textView10);
        rmrks = (TextView) findViewById(R.id.textView12);

    }
}
