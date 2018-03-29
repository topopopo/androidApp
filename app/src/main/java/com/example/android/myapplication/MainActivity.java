package com.example.android.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BizCardDb dbAdapter;                // DBAdapter
    private ArrayAdapter<String> adapter;       // ArrayAdapter
    private ArrayList<String> items;            // ArrayList

    private ListView mListView01;        // ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ent = (Button) findViewById(R.id.button1);
        ent.setOnClickListener(this);

        BizCardDb dbAdapter = new BizCardDb(this);
        dbAdapter.openDB();     // DBの読み込み(読み書きの方)

        findViews();           // 各部品の結び付け

        // ArrayListを生成
        items = new ArrayList<>();

        // DBのデータを取得
        String[] columns = {BizCardDb.COL_USER_NAME};     // DBのカラム：品名
        Cursor c = dbAdapter.getDB(columns);

        if (c.moveToFirst()) {
            do {
                items.add(c.getString(0));
            } while (c.moveToNext());
        }
        c.close();
        dbAdapter.closeDB();    // DBを閉じる

        // ArrayAdapterのコンストラクタ
        // 第1引数：Context
        // 第2引数：リソースとして登録されたTextViewに対するリソースID 今回は元々用意されている定義済みのレイアウトファイルのID
        // 第3引数：一覧させたいデータの配列
        adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, items);

        mListView01.setAdapter(adapter);     //ListViewにアダプターをセット(=表示)

        // ArrayAdapterに対してListViewのリスト(items)の更新
        adapter.notifyDataSetChanged();



    }

    /**
     * 各部品の結びつけ処理
     * findViews()
     */
    private void findViews() {
        mListView01 = (ListView) findViewById(R.id.listView1);       // 品名一覧用のListView
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
}