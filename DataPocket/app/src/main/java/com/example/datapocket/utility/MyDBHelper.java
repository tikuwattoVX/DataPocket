package com.example.datapocket.utility;

import com.example.datapocket.item.GenreDataItem;
import com.example.datapocket.utility.Key;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

/*
*データベースヘルパークラス
*/
public class MyDBHelper extends SQLiteOpenHelper{
    private boolean FirstStart_flg = false;         //初回起動判定フラグ、最初はfalseで初期化
    private final static String DBName = "DPDB";    //データベース名
    private final static int DBVersion = 1;         //データベースバージョン

    //コンストラクタ
    public MyDBHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }

    /****************************************************************************
     *   メソッド名     select文 ジャンル表示メソッド
     *   パラメータ      なし
     *   機能説明       ジャンル画面用テーブルデータ
     *   戻り値         リスト型 ジャンル画面用データ
     ****************************************************************************/
    public List<GenreDataItem> selectGenre(){
        List<GenreDataItem> GenreList = new ArrayList<GenreDataItem>();
        SQLiteDatabase db;
        db = getReadableDatabase();
        //クエリ作成
        StringBuilder buf = new StringBuilder();
        buf.append(" SELECT");
        buf.append(" "+Key.Columns_G1);
        //buf.append("   ,"+Key.Columns_G2); 画像の仕様未確定
        buf.append(" ,"+Key.Columns_G3);
        buf.append(" FROM "+Key.TABLE_NAME);

        Log.v("check query:",buf.toString());
        try{
            Cursor cursor = db.rawQuery(buf.toString(), null);
            while (cursor.moveToNext()){
                GenreList.add(new GenreDataItem(
                        cursor.getString(0),
                        cursor.getString(1)
                ));
            }
            Log.v("check :","select文通過");
        }finally{
            db.close();
        }
        return GenreList ;
    }

    /****************************************************************************
     *   メソッド名    insert文 ジャンル画面追加
     *   パラメータ    String タイトル
     *                 String 内容
     *   機能説明     引数の値をテーブルに追加
     *   戻り値       なし
     ****************************************************************************/
    public void insertGenre(String setColmns_1, /*String setColmns_2,*/ String setColmns_3){
        SQLiteDatabase db;
        db = getWritableDatabase();
        //insert
        StringBuilder buf = new StringBuilder();
        buf.append("INSERT INTO ");
        buf.append(Key.TABLE_NAME);
        buf.append("(");
        buf.append(Key.Columns_G1+","+Key.Columns_G3);
        buf.append(")");
        buf.append(" VALUES");
        buf.append("(");
        buf.append("\'"+setColmns_1+"\',\'"+setColmns_3+"\'");
        buf.append(")");

        Log.v("check query:",buf.toString());
        try {
            db.execSQL(buf.toString());
            Log.v("check:","insert通過");
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        }
    }

    /****************************************************************************
     *   メソッド名     初回テーブル作成メソッド
     *   機能説明       テーブルがない状態で実行した場合のみ呼び出されるメソッド
     *                  基本的には初回起動時のみ呼び出される
     *   戻り値         なし
     ****************************************************************************/
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        //クエリ作成
        StringBuilder buf = new StringBuilder();
        buf.append("create table "+ Key.TABLE_NAME);
        buf.append("(");
        buf.append(Key.Columns_DPID+" integer primary key autoincrement");
        //ジャンル画面カラム
        buf.append(" ,"+Key.Columns_G1+" text");
//      buf.append(" ,"+Key.Columns_G2+" ");       //画像の仕様未確定
        buf.append(" ,"+Key.Columns_G3+" text");
        //詳細画面カラム
        buf.append(" ,"+Key.Columns_D1+" text");
        buf.append(" ,"+Key.Columns_D2+" text");
//      buf.append(" ,"+Key.Columns_D3+" ");        //画像の仕様未確定
        buf.append(" ,"+Key.Columns_D4+" text");
        buf.append(")");
        //クエリ実行
        db.execSQL(buf.toString());
        FirstStart_flg = true; //初回起動フラグを立てる
        Log.v("check","table作成通過");
    }

    /****************************************************************************
     *   メソッド名   初回起動判定メソッド
     *   機能説明     DBが作成されたことを確認するメソッド
     *                初回起動の場合、サンプルレコードを作成
     *   戻り値       true  初回起動判定
     *                false DB作成済み
     ****************************************************************************/
    public void isStartFirst(){

        SQLiteDatabase db;
        db = getWritableDatabase();

        if(FirstStart_flg){
            //初回起動の場合のみサンプルレコード作成処理

            //insert
            StringBuilder buf = new StringBuilder();
            buf.append("INSERT INTO ");
            buf.append(Key.TABLE_NAME);
            buf.append("(");
            //ジャンル画面(タイトル、説明)
            buf.append(Key.Columns_G1+","+Key.Columns_G3);
            buf.append(",");
            //詳細画面(タイトル、振り仮名、説明)
            buf.append(Key.Columns_D1+","+Key.Columns_D2+","+Key.Columns_D4);
            buf.append(")");
            buf.append(" VALUES");
            buf.append("(");
            //ジャンル画面用サンプルデータ
            buf.append("'魚料理', '魚料理とは、魚である'");
            buf.append(",");
            //詳細画面用サンプルデータ
            buf.append("'鰡','ぼら','鰡とは、魚の一種である。呼び方は「ぼら」'");
            buf.append(")");

            Log.v("check query:",buf.toString());
            try {
                db.execSQL(buf.toString());
                Log.v("check:","初回サンプル用insert通過");
            } catch (SQLException e) {
                Log.e("ERROR", e.toString());
            }



            FirstStart_flg = false; //作成後はフラグを折る
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }

}