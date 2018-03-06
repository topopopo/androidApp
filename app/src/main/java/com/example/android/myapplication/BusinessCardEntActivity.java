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
    private EditText name;
    public static final String Id = "Id";
    public static final String Co_Name = "Co_Name";
    public static final String User_name = "User_name";
    public static final String Position = "Position";
    public static final String Tel  = "Tel";
    public static final String Mail = "Mail";
    public static final String Rmrks = "Rmrks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisiness_card_ent);

        Button ins = (Button) findViewById(R.id.insert);
        ins.setOnClickListener(this);

        Button upd = (Button) findViewById(R.id.update);
        upd.setOnClickListener(this);

        name = (EditText) findViewById(R.id.editText1);

        MSSQLhelper = new BizCardDb(BusinessCardEntActivity.this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.insert://登録
                String user = name.getText().toString();
                enter("2","co",user,"president","080","gmail","rmrks");
                break;

            /*case R.id.search://検索
                Intent intent = new Intent(getApplicationContext(), MemosearchActivity.class);//検索画面に遷移します。
                intent.putExtra("検索","1");
                startActivity(intent);
                break;*/
        }
    }
    private void enter(String id,String co,String user,String position,String tel,String mail,String rmrks) {

        SQLiteDatabase db = MSSQLhelper.getWritableDatabase();

        ContentValues values = new ContentValues();     // ContentValuesでデータを設定していく
        values.put(Id, id);
        values.put(Co_Name, co);
        values.put(User_name, user);
        values.put(Position, position);
        values.put(Tel, tel);
        values.put(Mail, mail);
        values.put(Rmrks, rmrks);

        long rt = db.insert("BizCard", null, values);

        db.close();

        if (0 < rt) {
            Toast.makeText(this, "追加できました", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "追加できませんでした", Toast.LENGTH_LONG).show();

        }

    }
}