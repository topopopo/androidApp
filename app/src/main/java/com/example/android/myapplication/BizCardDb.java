package com.example.android.myapplication;

/**
 * Created by TK on 2018/03/04.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BizCardDb extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String DB_FILENAME= "BizCard.db";

    public BizCardDb (Context context){
        super(context,DB_FILENAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("BizCardDb", "onCreate");

        // ここでDBの作成、初期データの投入を行う
        db.execSQL("CREATE TABLE BizCard (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Co_Name TEXT," +
                "User_name TEXT," +
                "Position TEXT," +
                "Tel TEXT," +
                "Mail TEXT," +
                "Rmks TEXT)");

        db.execSQL("INSERT INTO BizCard(Co_name) VALUES('kosuge co');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // アップグレード時に呼ばれる
    }
}