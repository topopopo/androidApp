package com.example.android.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView01;
    private List<MyListItem> items;
    protected MyListItem myListItem;

    private String[] where = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView01 = (ListView) findViewById(R.id.listView1);

        Button ent = (Button) findViewById(R.id.button1);
        ent.setOnClickListener(this);

        // ArrayListを生成
        items = new ArrayList<>();

        findViews();           // 各部品の結び付け
        loadList();            //リストを格納

    }

    /**
     * 各部品の結びつけ処理
     * findViews()
     */
    private void findViews() {
               //mButton02AllDelete = (Button) findViewById(R.id.button02AllDelete);         // 全削除ボタン
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1://検索
                Intent intent = new Intent(getApplicationContext(), BusinessCardEntActivity.class);//検索画面に遷移
                startActivity(intent);
                break;
        }
    }

   private void loadList() {
        BizCardDb dbAdapter = new BizCardDb(this);

        items.clear();
        dbAdapter.openDB();     // DBの読み込み(読み書きの方)


        Cursor c = dbAdapter.getDB(where);



        if (c.moveToFirst()) {
            do {
                // MyListItemのコンストラクタ呼び出し(myListItemのオブジェクト生成)
                myListItem = new MyListItem(

                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6));
                        //c.getInt(c.getColumnIndex("Id")),
                        //c.getString(c.getColumnIndex("Co_Name")),
                        //c.getString(c.getColumnIndex("User_Name")),
                        //c.getString(c.getColumnIndex("Dept_Name")),
                        //c.getString(c.getColumnIndex("Tel")),
                        //c.getString(c.getColumnIndex("Mail")),
                        //c.getString(c.getColumnIndex("Rmrks")));

                items.add(myListItem);          // 取得した要素をitemsに追加

            } while (c.moveToNext());
        }
        c.close();
        dbAdapter.closeDB();
       ItemListAdapter adapter = new ItemListAdapter(this, 0,items);
        mListView01.setAdapter(adapter);  // ListViewのRowにをセット
    }

}