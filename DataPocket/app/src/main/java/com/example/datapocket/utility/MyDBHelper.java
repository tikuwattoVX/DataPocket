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

/*  データベースヘルパークラス
* メソッド
*
*
*
* */
public class MyDBHelper extends SQLiteOpenHelper{

    private String string ="";
    private Context con;
    public MyDBHelper(Context context) {
		super(context, "testDB", null, 1);
        Log.v("TAG", "コンストラクタとおりました。");
        Toast.makeText(context, string, Toast.LENGTH_LONG).show();
		// TODO Auto-generated constructor stub
	}

    //テーブルがない状態でインスタンス化した場合のみ呼び出されるメソッド
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
        Log.v("TAG","onCreate通りました。");
        //クエリ作成
        String sql = "";
        sql += "create table "+ Key.TABLE_NAME +" (";
        sql += " "+Key.Columns_DPID+" integer primary key autoincrement";
        //ジャンル画面用カラム
        sql += ""+Key.Columns_G1+", text not null";
        //sql += ""+Key.Columns_G2+", "; 画像の仕様未確定
        sql += ""+Key.Columns_G3+", text not null";
        //詳細画面用カラム
        sql += ""+Key.Columns_D1+", text not null";
        sql += ""+Key.Columns_D2+", text not null";
        //sql += ""+Key.Columns_D3+", "; 画像の仕様未確定
        sql += ""+Key.Columns_D4+", text not null";
        sql += ")";
        //クエリ実行

        db.execSQL(sql);

    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

    //select文 ジャンル表示メソッド
    /*Parameter : なし
    */
    public List<GenreDataItem> selectGenre(){
        List<GenreDataItem> GenreList = new ArrayList<GenreDataItem>();
        //クエリ作成
        String sql = "";
        sql += "select ";
        sql += " ,"+Key.Columns_G1+"";
        //sql += " ,"+Key.Columns_G2+""; 画像の仕様未確定
        sql += " ,"+Key.Columns_G3+"";
        sql += "FROM "+Key.TABLE_NAME+"";

        SQLiteDatabase db = getReadableDatabase();

        try{
            Cursor cursor = db.rawQuery(sql.toString(), null);
            while (cursor.moveToNext()){
                GenreList.add(new GenreDataItem(
                        cursor.getString(0),
                        cursor.getString(1)
                        ));
            }
        }finally{
            db.close();
        }

        return GenreList ;
    }

    //insert文 ジャンル画面追加
    /*Parameter :   String タイトル
    *               String 内容
    */
    public void insertGenre(String setColmns_1, /*String setColmns_2,*/ String setColmns_3){
        SQLiteDatabase db = getReadableDatabase();
        //insert
        String sql = "insert into "
                + Key.TABLE_NAME + " ("+ Key.Columns_G1 +", "+ Key.Columns_G2 +", /*"+ Key.Columns_G3 +",*/ )"
                + " values( + setColumns_1 + ,/* + setColmns_2 + */ + setColmns_3 + );";
        try {
            Log.v("TAG", "保存しました。コングラッチュローション");
            db.execSQL(sql);
        } catch (SQLException e) {
            Log.e("ERROR", e.toString());
        }
    }
}

