package com.example.android.myapplication;

/**
 * Created by TK on 2018/03/04.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BizCardDb {
    private static int DB_VERSION = 1;     //データベースバージョン
    private static String DB_FILENAME = "BizCard.db"; //データベース名
    private final static String DB_TABLE = " BizCard";       // DBのテーブル名


    private SQLiteDatabase db = null;
    private DBHelper dbHelper = null;           // DBHelper型のdbhelper
    protected Context context;                  // Context

    //カラム
    public final static String COL_ID = "Id";
    public final static String COL_CO_NAME = "Co_Name";
    public final static String COL_DEPT_NAME = "Dept_Name";
    public final static String COL_USER_NAME = "User_Name";
    public final static String COL_TEL = "Tel";
    public final static String COL_MAIL = "Mail";
    public final static String COL_RMRKS = "Rmrks";

    //コンストラクタ
    public BizCardDb(Context context) {
        this.context = context;
        dbHelper = new DBHelper(this.context);
    }

    public BizCardDb openDB() {
        db = dbHelper.getWritableDatabase();        // DBの読み書き
        return this;
    }

    public BizCardDb readDB() {
        db = dbHelper.getReadableDatabase();        // DBの読み込み
        return this;
    }

    public void closeDB() {
        db.close();     // DBを閉じる
        db = null;
    }
    /**
     * DBのレコードへ登録
     * saveDB()
     *
     * @param co 会社名
     * @param dept 部署
     * @param user  名前
     * @param tel   電話
     * @param mail  メールアドレス
     * @param rmrks  備考
     */
    public long saveDB(String co,String dept,String user,String tel,String mail,String rmrks) {

        long rt = 0;
        db.beginTransaction();          // トランザクション開始

        try {
            ContentValues values = new ContentValues();     // ContentValuesでデータを設定していく
            values.put(COL_CO_NAME, co);
            values.put(COL_DEPT_NAME, dept);
            values.put(COL_USER_NAME, user);
            values.put(COL_TEL, tel);
            values.put(COL_MAIL, mail);
            values.put(COL_RMRKS, rmrks);

            // insertメソッド データ登録
            // 第1引数：DBのテーブル名
            // 第2引数：更新する条件式
            // 第3引数：ContentValues
            rt = db.insert(DB_TABLE, null, values);      // レコードへ登録

            db.setTransactionSuccessful();      // トランザクションへコミット
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();                // トランザクションの終了
        }
        return rt;
    }

    /**
     * DBのデータを取得
     * getDB()
     *
     * @param columns String[] 取得するカラム名 nullの場合は全カラムを取得
     * @return DBのデータ
     */
    public Cursor getDB(String[] columns) {

        // queryメソッド DBのデータを取得
        // 第1引数：DBのテーブル名
        // 第2引数：取得するカラム名
        // 第3引数：選択条件(WHERE句)
        // 第4引数：第3引数のWHERE句において?を使用した場合に使用
        // 第5引数：集計条件(GROUP BY句)
        // 第6引数：選択条件(HAVING句)
        // 第7引数：ソート条件(ODERBY句)
        return db.query(DB_TABLE, columns, null, null, null, null, null);
    }

    /**
     * DBの検索したデータを取得
     * searchDB()
     *
     * @param columns String[] 取得するカラム名 nullの場合は全カラムを取得
     * @param column  String 選択条件に使うカラム名
     * @param name    String[]
     * @return DBの検索したデータ
     */
    public Cursor searchDB(String[] columns, String column, String[] name) {
        return db.query(DB_TABLE, columns, column + " like ?", name, null, null, null);
    }

    public Cursor selectDB(String[] pram) {
        String sqlStr ="select Id" +
                "               ,Co_Name" +
                "               ,User_Name" +
                "               ,Dept_Name" +
                "               ,Tel" +
                "               ,Mail" +
                "               ,Rmrks" +
                        "from '%DB_TABLE%' " +
                        "where Id" ;
        return db.rawQuery(sqlStr,pram);
    }

    /**
     * DBのレコードを全削除
     * allDelete()
     */
    public void allDelete() {

        db.beginTransaction();                      // トランザクション開始
        try {
            // deleteメソッド DBのレコードを削除
            // 第1引数：テーブル名
            // 第2引数：削除する条件式 nullの場合は全レコードを削除
            // 第3引数：第2引数で?を使用した場合に使用
            db.delete(DB_TABLE, null, null);        // DBのレコードを全削除
            db.setTransactionSuccessful();          // トランザクションへコミット
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();                    // トランザクションの終了
        }
    }

    /**
     * DBのレコードの単一削除
     * selectDelete()
     *
     * @param position String
     */
    public void selectDelete(String position) {

        db.beginTransaction();                      // トランザクション開始
        try {
            db.delete(DB_TABLE, COL_ID + "=?", new String[]{position});
            db.setTransactionSuccessful();          // トランザクションへコミット
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();                    // トランザクションの終了
        }
    }

    public static class DBHelper extends SQLiteOpenHelper {

        //コンストラクタ(DB作成)
        public DBHelper(Context context) {
            //第1引数：コンテキスト
            //第2引数：DB名
            //第3引数：factory nullでよい
            //第4引数：DBのバージョン
            super(context, DB_FILENAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

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
            // DBからテーブル削除
            db.execSQL("DROP TABLE IF EXISTS" +DB_TABLE);
            // テーブル生成
            onCreate(db);
        }
    }
}