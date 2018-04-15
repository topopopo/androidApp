package com.example.android.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BusinessCardEntActivity extends AppCompatActivity implements View.OnClickListener{
    private BizCardDb MSSQLhelper = null;
    private EditText user;
    private EditText company;
    private EditText dept;
    private EditText tel;
    private EditText mail;
    private EditText rmrks;
    long rt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisiness_card_ent);

        Button ins = (Button) findViewById(R.id.insert);
        ins.setOnClickListener(this);

        Button upd = (Button) findViewById(R.id.update);
        upd.setOnClickListener(this);

        company = (EditText) findViewById(R.id.editText1);
        dept = (EditText) findViewById(R.id.editText2);
        user = (EditText) findViewById(R.id.editText3);
        tel = (EditText) findViewById(R.id.editText4);
        mail = (EditText) findViewById(R.id.editText5);
        rmrks = (EditText) findViewById(R.id.editText6);

        MSSQLhelper = new BizCardDb(BusinessCardEntActivity.this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert://登録
                String Co = company.getText().toString();
                String Dept = dept.getText().toString();
                String User = user.getText().toString();
                String Tel = tel.getText().toString();
                String Mail = mail.getText().toString();
                String Rmrks = rmrks.getText().toString();

                BizCardDb dbAdapter = new BizCardDb(this);
                dbAdapter.openDB();                               // DBの読み書き
                rt = dbAdapter.saveDB(Co,Dept,User,Tel,Mail,Rmrks);   // DBに登録
                dbAdapter.closeDB();

            if(0 < rt) {
                Toast.makeText(this, "追加できました", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "追加できませんでした", Toast.LENGTH_LONG).show();
              }
            break;

            /*case R.id.search://検索
                Intent intent = new Intent(getApplicationContext(), MemosearchActivity.class);//検索画面に遷移します。
                //値を引き渡す。
                intent.putExtra("検索","1");
                startActivity(intent);
                break;*/
        }
    }

}