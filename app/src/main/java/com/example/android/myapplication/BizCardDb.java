package com.example.android.myapplication;

/**
 * Created by TK on 2018/03/04.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class BizCardDb extends SQLiteOpenHelper {
    //データベースバージョン
    private static int DB_VERSION = 1;
    //データベース名
    private static String DB_FILENAME= "BizCard.db";

    private SQLiteDatabase db = null;
    private BizCardDb dbHelper = null;           // DBHelper
    protected Context context;                  // Context
    //IDカラム
    /*public static final String Id = "Id";
    public static final String Co_Name = "Co_Name";
    public static final String User_name = "User_name";
    public static final String Position = "Position";
    public static final String Tel  = "Tel";
    public static final String Mail = "Mail";
    public static final String Rmrks = "Rmrks";*/


    public BizCardDb (Context context){
        super(context,DB_FILENAME,null,DB_VERSION);
    }

    public BizCardDb openDB() {
        db = dbHelper.getWritableDatabase();        // DBの読み書き
        return this;
    }

    public void closeDB() {
        db.close();     // DBを閉じる
        db = null;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("BizCardDb", "onCreate");

        // ここでDBの作成、初期データの投入を行う
        db.execSQL("CREATE TABLE BizCard (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Co_Name TEXT ," +
                "User_Name TEXT ," +
                "Dept_Name TEXT," +
                "Tel TEXT," +
                "Mail TEXT," +
                "Rmrks TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップグレード時に呼ばれる
    }
}