package com.example.android.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tada on 2018/04/15.
 */

public class ItemListAdapter extends ArrayAdapter<MyListItem> {

    private static class ViewHolder {
        TextView mtextView2;
        TextView mtextView4;
        TextView mtextView6;
        TextView mtextView8;
        TextView mtextView10;
        TextView mtextView12;

    public ViewHolder(View v) {
        super();
        this.mtextView2 = (TextView) v.findViewById(R.id.textView2);
        this.mtextView4 = (TextView) v.findViewById(R.id.textView4);
        this.mtextView6 = (TextView) v.findViewById(R.id.textView6);
        this.mtextView8 = (TextView) v.findViewById(R.id.textView8);
        this.mtextView10 = (TextView) v.findViewById(R.id.textView10);
        this.mtextView12 = (TextView) v.findViewById(R.id.textView12);
    }
}
        private LayoutInflater layoutInflater;

        public ItemListAdapter(Context context, int resource, List<MyListItem> items) {
            super(context, resource, items);

            this.layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            // Viewがリサイクル出来ない場合
            if (convertView == null) {
                // Viewにレイアウトの情報を登録する
                convertView = layoutInflater.inflate(R.layout.item_detail_row, null);
                // Viewがリサイクル出来る場合はnewする必要がないので、holderはここでnewする
                holder = new ViewHolder(convertView);
                // リサイクルするときのためにタグ付けしておく
                convertView.setTag(holder);
            } else { // Viewがリサイクル出来る場合
                // タグ付けしておいた情報を取得する
                holder = (ViewHolder) convertView.getTag();
            }

            MyListItem ItemPosition = getItem(position);
            // 取得した各データを各TextViewにセット
            holder.mtextView2.setText(ItemPosition.getCo_Name());
            holder.mtextView4.setText(ItemPosition.getUser_Name());
            holder.mtextView6.setText(ItemPosition.getDept_Name());
            holder.mtextView8.setText(ItemPosition.getTel());
            holder.mtextView10.setText(ItemPosition.getMail());
            holder.mtextView12.setText(ItemPosition.getRmrks());


            return convertView; // Viewを返す
        }
}
