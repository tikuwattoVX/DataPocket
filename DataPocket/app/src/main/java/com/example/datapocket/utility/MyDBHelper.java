package com.example.datapocket.utility;

import com.example.datapocket.item.GenreDataItem;
import com.example.datapocket.item.ListDataItem;
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
* 拡張済みデータベースヘルパークラス
*   基本的にはインスタンス化->APIコールで処理を実行
*   select 1API
*   insert 1API
*   delete 2API
*/
public class MyDBHelper extends SQLiteOpenHelper{
    private boolean FirstStart_flg = false;         //初回起動判定フラグ、最初はfalse(OFF)で初期化
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
     *
     ****************************************************************************/
    public List<GenreDataItem> selectGenre(){
        List<GenreDataItem> GenreList = new ArrayList<GenreDataItem>();
        SQLiteDatabase db;
        db = getReadableDatabase();
        //クエリ作成
        StringBuilder buf = new StringBuilder();
        buf.append("SELECT ");
        buf.append(Key.GenreID);
        buf.append(" ,"+Key.Columns_G1);
        //buf.append("  ,"+Key.Columns_G2); 画像の仕様未確定
        buf.append(" ,"+Key.Columns_G3);
        buf.append(" ,"+Key.Columns_G4);
        buf.append(" FROM "+Key.GenreTable);

        Log.v("check query:",buf.toString());
        try{
            Cursor cursor = db.rawQuery(buf.toString(), null);
            while (cursor.moveToNext()){
                GenreList.add(new GenreDataItem(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                ));
            }
            Log.v("check :","select文通過");
        } finally{
            db.close();
        }
        return GenreList ;
    }

    /****************************************************************************
     *   メソッド名     select文 リスト表示メソッド
     *   パラメータ     String LISTKEY
     *   機能説明       ジャンル画面用テーブルデータ
     *   戻り値         リスト型 ジャンル画面用データ
     *
     ****************************************************************************/
    public List<ListDataItem> selectList(String LISTKEY){
        List<ListDataItem> ListList = new ArrayList<ListDataItem>();
        SQLiteDatabase db;
        db = getReadableDatabase();
        //クエリ作成
        StringBuilder buf = new StringBuilder();
        buf.append("SELECT ");
        buf.append(Key.ListID);
        buf.append(" ,"+Key.Columns_D2);
        buf.append(" ,"+Key.Columns_D3);
        //buf.append("  ,"+Key.Columns_D4); 画像の仕様未確定
        buf.append(" FROM "+Key.ListTable);
        buf.append(" WHERE "+Key.Columns_D1+" = ");
        buf.append("\'"+LISTKEY+"\'");

        Log.v("check query:",buf.toString());
        try{
            Cursor cursor = db.rawQuery(buf.toString(), null);
            while (cursor.moveToNext()){
                ListList.add(new ListDataItem(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                ));
            }
            Log.v("check :","select文通過");
        } finally{
            db.close();
        }
        return ListList ;
    }

    /****************************************************************************
     *   メソッド名    insert文 ジャンル画面追加
     *   パラメータ    String タイトル
     *                 String 内容
     *   機能説明     引数の値をジャンル画面テーブルに追加
     *   戻り値       なし
     ****************************************************************************/
    public void insertGenre(String setColmns_1, /*String setColmns_2,*/ String setColmns_3){
        SQLiteDatabase db;
        db = getWritableDatabase();
        //insert
        StringBuilder buf = new StringBuilder();
        buf.append("INSERT INTO ");
        buf.append(Key.GenreTable);
        buf.append("(");
        buf.append(Key.Columns_G1+","+Key.Columns_G3);
        buf.append(")");
        buf.append(" VALUES(");
        buf.append("\'"+setColmns_1+"\',\'"+setColmns_3+"\'");
        buf.append(")");

        Log.v("check query:",buf.toString());
        try {
            db.execSQL(buf.toString());
            Log.v("check:","insertジャンル画面通過");
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        } finally {
            db.close();
        }
    }

    /****************************************************************************
     *   メソッド名    delete文 1レコード削除(ジャンル画面)
     *   パラメータ    int primarykey
     *   機能説明     引数の値(プライマリキー)に対応する
     *                ジャンル画面テーブルのレコード削除
     *   戻り値       なし
     ****************************************************************************/
    public void deleteGenre(int Primarykey) {
        SQLiteDatabase db;
        db = getWritableDatabase();
        //delete
        StringBuilder buf = new StringBuilder();
        buf.append("DELETE FROM ");
        buf.append(Key.GenreTable);
        buf.append(" WHERE ");
        buf.append(Key.GenreID+"=\'"+Primarykey+"\'");

        Log.v("check query:",buf.toString());
        try {
            db.execSQL(buf.toString());
            Log.v("check:","delete通過");
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        } finally {
            db.close();
        }
    }

    /****************************************************************************
     *   メソッド名    delete文 テーブル削除
     *   パラメータ    void
     *   機能説明     ・2テーブルの削除(全レコードの削除)
     *                ・初回起動APIコール
     *   戻り値       なし
     ****************************************************************************/
    public void deleteALL(int primarykey) {
        SQLiteDatabase db;
        db = getWritableDatabase();
        //delete
        StringBuilder buf = new StringBuilder();
        buf.append("DELETE FROM ");
        buf.append(Key.GenreTable);

        Log.v("check query:",buf.toString());
        try {
            db.execSQL(buf.toString());
            Log.v("check:","delete通過");
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        } finally {
            //db.close();
        }

        //delete
        StringBuilder buf2 = new StringBuilder();
        buf2.append("DELETE FROM ");
        buf2.append(Key.ListTable);

        Log.v("check query:",buf2.toString());
        try {
            db.execSQL(buf2.toString());
            Log.v("check:","delete通過");
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        } finally {
            db.close();
        }

        //フラグON -> 直接初回起動確認APIをコールする処理に変更
        //FirstStart_flg = true; //初回起動フラグを立てる -> onCreate内部処理で実行の為コメントアウト
        isStartFirst();
    }


/****************************************************************************************************
 * 以下初回起動時API
 ****************************************************************************************************/


    /****************************************************************************
     *   メソッド名     初回テーブル作成メソッド
     *   機能説明       ・テーブルがない状態で実行した場合のみ呼び出されるメソッド
     *                  ・基本的には初回起動時のみ呼び出される
     *                  ・ジャンル画面テーブル と リスト&詳細画面テーブル を作成
     *   戻り値         なし
     ****************************************************************************/
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*****ジャンル画面用テーブル作成クエリ****/
        //クエリ作成
        StringBuilder buf1 = new StringBuilder();
        buf1.append("CREATE TABLE "+ Key.GenreTable);
        buf1.append("(");
        buf1.append(Key.GenreID+" integer primary key autoincrement");
        //ジャンル画面カラム
        buf1.append(" ,"+Key.Columns_G1+" text");
//      buf1.append(" ,"+Key.Columns_G2+" ");       //画像の仕様未確定
        buf1.append(" ,"+Key.Columns_G3+" text");
        buf1.append(" ,"+Key.Columns_G4+" text");   //Listキー "List"+Key.GenreID(プライマリキー)
        buf1.append(")");
        Log.v("check query:",buf1.toString());
        try {
            //クエリ実行
            db.execSQL(buf1.toString());
            Log.v("check:","初回テーブル作成_ジャンル画面テーブル通過");
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        }

        /*****リスト&詳細画面用テーブル作成クエリ****/
        //クエリ作成
        StringBuilder buf2 = new StringBuilder();
        buf2.append("CREATE TABLE "+ Key.ListTable);
        buf2.append("(");
        buf2.append(Key.ListID+" integer primary key autoincrement");
        //リスト&詳細画面カラム
        buf2.append(" ,"+Key.Columns_D1+" text");   //Listキー "List"+Key.GenreID(プライマリキー)
        buf2.append(" ,"+Key.Columns_D2+" text");
        buf2.append(" ,"+Key.Columns_D3+" text");
        //buf.append(" ,"+Key.Columns_D4+"");  //画像の仕様未確定
        buf2.append(" ,"+Key.Columns_D5+" text");
        buf2.append(")");
        Log.v("check query:",buf2.toString());
        try {
            //クエリ実行
            db.execSQL(buf2.toString());
            Log.v("check:","初回テーブル作成_ジャンル画面テーブル通過");
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        }

        FirstStart_flg = true; //初回起動フラグを立てる
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
        db = getWritableDatabase();  //テーブルの有無確認 無ければフラグがONになる
        if(FirstStart_flg){
            //ジャンル画面テーブル
            StringBuilder buf = new StringBuilder();
            buf.append("INSERT INTO ");
            buf.append(Key.GenreTable);
            buf.append("(");
            //プライマリキー Key.GenreID(integer primary key autoincrement)
            buf.append(Key.Columns_G1+","+Key.Columns_G3+","+Key.Columns_G4);
            buf.append(") ");
            buf.append("VALUES(");
            buf.append(" '魚料理', '魚料理についてのまとめ'");
            buf.append(",'LIST1' ");    //初回サンプルなので1で決めうち
            buf.append(")");
            Log.v("check query:",buf.toString());
            try {
                db.execSQL(buf.toString());
                Log.v("check:","[ジャンル]初回サンプル用insert通過");
            } catch (SQLException e) {
                Log.e("ERROR", e.toString());
            }

            //リスト&詳細画面テーブル
            StringBuilder buf2 = new StringBuilder();
            buf2.append("INSERT INTO ");
            buf2.append(Key.ListTable);
            buf2.append("(");
            //プライマリキー Key.ListID(integer primary key autoincrement)
            buf2.append(Key.Columns_D1+",");    //Listキー
            buf2.append(Key.Columns_D2+","+Key.Columns_D3+","+Key.Columns_D5);
            buf2.append(") ");
            buf2.append("VALUES(");
            buf2.append(" 'List1',");  //初回サンプルなので1で決めうち
            buf2.append("'まぐろ', '鮪','鮪とは、魚である。魚は体にいいのである。' ");
            buf2.append(")");
            Log.v("check query:",buf2.toString());
            try {
                db.execSQL(buf2.toString());
                Log.v("check:","[リスト&詳細]初回サンプル用insert通過");
            } catch (SQLException e) {
                Log.e("ERROR", e.toString());
            } finally {
                db.close();
            }
            FirstStart_flg = false; //初回サンプル作成後はフラグを折る
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}