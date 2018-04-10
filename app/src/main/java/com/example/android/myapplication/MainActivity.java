package com.example.android.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BizCardDb dbAdapter;                // DBAdapter
    private MyBaseAdapter myBaseAdapter;

    private ListView mListView01;
    private List<MyListItem> items;
    protected MyListItem myListItem;


    private String[] where = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button ent = (Button) findViewById(R.id.button1);
        ent.setOnClickListener(this);

        BizCardDb dbAdapter = new BizCardDb(this);

        // ArrayListを生成
        items = new ArrayList<>();
        // MyBaseAdapterのコンストラクタ呼び出し(myBaseAdapterのオブジェクト生成)
        myBaseAdapter = new MyBaseAdapter(this, items);

        findViews();           // 各部品の結び付け
        loadList();            //リストを格納



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
                //Toast.makeText(this, (String.valueOf(c.getInt(0))), Toast.LENGTH_SHORT).show();
                Log.d("取得したCursor(ID):", String.valueOf(c.getInt(0)));
                Log.d("取得したCursor(品名):", c.getString(1));
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
        dbAdapter.closeDB();                    // DBを閉じる
        mListView01.setAdapter(myBaseAdapter);  // ListViewにmyBaseAdapterをセット
        myBaseAdapter.notifyDataSetChanged();   // Viewの更新
    }

    public class MyBaseAdapter extends BaseAdapter {
        private Context context;
        private List<MyListItem> items;

        // 毎回findViewByIdをする事なく、高速化が出来るようするholderクラス
        private class ViewHolder {
            TextView textView2;
            TextView textView4;
            TextView textView6;
            TextView textView8;
            //TextView textView10;
            //TextView textView12;
        }

        // コンストラクタの生成
        public MyBaseAdapter(Context context, List<MyListItem> items) {
            this.context = context;
            this.items = items;
        }

        // Listの要素数を返す
        @Override
        public int getCount() {

            return items.size();
        }

        // indexやオブジェクトを返す
        @Override
        public Object getItem(int position) {

            return items.get(position);
        }

        // IDを他のindexに返す
        @Override
        public long getItemId(int position) {

            return position;
        }

        // 新しいデータが表示されるタイミングで呼び出される
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View view = convertView;
            ViewHolder holder;

            // データを取得
            myListItem = items.get(position);




            if (view == null) {
                LayoutInflater inflater =
                        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_detail_row, parent, false);

                TextView textView2 = (TextView) findViewById(R.id.textView2);
                TextView textView4 = (TextView) findViewById(R.id.textView4);
                TextView textView6 = (TextView) findViewById(R.id.textView6);
                TextView textView8 = (TextView) findViewById(R.id.textView8);
                //TextView textView10 = (TextView) findViewById(R.id.textView10);
                //TextView textView12 = (TextView) findViewById(R.id.textView12);

                // holderにviewを持たせておく
                holder = new ViewHolder();
                holder.textView2 = textView2;
                holder.textView4 = textView4;
                holder.textView6 = textView6;
                holder.textView8 = textView8;
                //holder.textView10 = textView10;
                //holder.textView12 = textView12;
                view.setTag(holder);

            } else {
                // 初めて表示されるときにつけておいたtagを元にviewを取得する
                holder = (ViewHolder) view.getTag();
            }



            // 取得した各データを各TextViewにセット
            holder.textView2.setText(myListItem.getCo_Name());
            holder.textView4.setText(myListItem.getUser_Name());
            holder.textView6.setText(myListItem.getDept_Name());
            holder.textView8.setText(myListItem.getTel());
            //holder.textView10.setText(myListItem.getMail());
            //holder.textView12.setText(myListItem.getRmrks());


            return view;
        }
    }
}