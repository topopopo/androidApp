package com.example.android.myapplication;

import android.util.Log;

/**
 * SelectSheetListViewに必要なデータを取得するクラス
 * MyListItem
 */
public class MyListItem {
    protected int id;           // ID
    protected String Co_Name;   // 会社名
    protected String User_Name;    // 名前
    protected String Dept_Name;    // 部署名
    protected String Tel;     // 電話番号
    protected String Mail;     // メール
    protected String Rmrks;     // 備考


    public MyListItem(int id, String Co_Name, String User_Name, String Dept_Name, String Tel,String Mail,String Rmrks) {
        this.id = id;
        this.Co_Name = Co_Name;
        this.User_Name = User_Name;
        this.Dept_Name = Dept_Name;
        this.Tel = Tel;
        this.Mail = Mail;
        this.Rmrks = Rmrks;
    }

    /**
     * IDを取得
     * getId()
     *
     * @return id int ID
     */
    public int getId() {
        Log.d("取得したID：", String.valueOf(id));
        return id;
    }

    public String getCo_Name() {
        return Co_Name;
    }


    public String getUser_Name() {
        return User_Name;
    }


    public String getDept_Name() {
        return Dept_Name;
    }

    public String getTel() {
        return Tel;
    }

    public String getMail() {
        return Mail;
    }

    public String getRmrks() {
        return Rmrks;
    }
}