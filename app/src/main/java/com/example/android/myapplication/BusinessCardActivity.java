package com.example.android.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class BusinessCardActivity extends AppCompatActivity {
    private List<MyListItem> items;
    protected MyListItem myListItem;
    private MyBaseAdapter myBaseAdapter;

    private String[] where = {"1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_card);

        // itemsのArrayList生成
        items = new ArrayList<>();

        loadList();


        // MyBaseAdapterのコンストラクタ呼び出し(myBaseAdapterのオブジェクト生成)
        myBaseAdapter = new MyBaseAdapter(this, items);

    }

    private void loadList() {

        BizCardDb dbAdapter = new BizCardDb(this);
        dbAdapter.openDB();     // DBの読み込み(読み書きの方)


        Cursor c = dbAdapter.selectDB(where);

        if (c.moveToFirst()) {
            do {
                // MyListItemのコンストラクタ呼び出し(myListItemのオブジェクト生成)
                myListItem = new MyListItem(
                         c.getInt(c.getColumnIndex("id")),
                        c.getString(c.getColumnIndex("Co_Name")),
                        c.getString(c.getColumnIndex("User_Name")),
                        c.getString(c.getColumnIndex("Dept_Name")),
                        c.getString(c.getColumnIndex("Tel")),
                        c.getString(c.getColumnIndex("Mail")),
                        c.getString(c.getColumnIndex("Rmrks")));

                items.add(myListItem);          // 取得した要素をitemsに追加

            } while (c.moveToNext());
        }
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
            TextView textView10;
            TextView textView12;
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
                TextView company = (TextView) findViewById(R.id.textView2);
                TextView dept = (TextView) findViewById(R.id.textView4);
                TextView user = (TextView) findViewById(R.id.textView6);
                TextView tel = (TextView) findViewById(R.id.textView8);
                TextView mail = (TextView) findViewById(R.id.textView10);
                TextView rmrks = (TextView) findViewById(R.id.textView12);

                // holderにviewを持たせておく
                holder = new ViewHolder();
                holder.textView2 = company;
                holder.textView4 = dept;
                holder.textView6 = user;
                holder.textView8 = tel;
                holder.textView10 = mail;
                holder.textView12 = rmrks;
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
            holder.textView10.setText(myListItem.getMail());
            holder.textView12.setText(myListItem.getRmrks());


            return view;

        }
    }
}

