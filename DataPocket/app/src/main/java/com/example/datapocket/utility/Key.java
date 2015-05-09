package com.example.datapocket.utility;

/**
 * Created by masakisakamoto on 2015/04/19.
 */
public class Key {

    // GenreTopActivity
    public static final String SYSTEM_VALUE = "systemValue";
    public static final String GENRE_START_FIRST = "genreStartFirst";
    public static final String GENRE_TITLE = "genreTitle";
    public static final String GENRE_DESCRIPTION = "genreDescription";

    // ListActivity
    public static final String LIST_TITLE = "listTitle";
    public static final String LIST_DESCRIPTION = "listDescription";
    public static final String LIST_IMAGE = "listImage";
    // ListActivity
    public static final String LIST_START_FIRST = "listStartFirst";

    // AddGenreActivity
    public static final String GENRE_IMAGE = "genreImage";

/**********************以下はデータベース(テーブル)定義に使用**********************/
    //ジャンル画面
    public static final String GenreTable = "GenreTable";   //テーブル名
    public static final String GenreID = "gID";             //プライマリキー
    public static final String Columns_G1 = "gTitle";       //タイトル
    public static final String Columns_G2 = "gPicture";     //画像表示用 -> 未使用
    public static final String Columns_G3 = "gDescription"; //内容
    public static final String Columns_G4 = "gListKey";     //リストテーブルへのキー(ジャンルとリスト&詳細へのキー)
    //ListKeyのカラムに入る値はジャンルテーブルとリスト&詳細テーブルを繋ぐキーになる

    //リスト & 詳細画面
    public static final String ListTable  = "ListTable";    //テーブル名
    public static final String ListID     = "dID";          //プライマリキー(主に削除に使用)
    public static final String Columns_D1 = "dListKey";     //リストテーブルのキー
    public static final String Columns_D2 = "dHurigana";    //ふりがな
    public static final String Columns_D3 = "dTittle";      //タイトル
    public static final String Columns_D4 = "dPicture";     //画像表示用 -> 未使用
    public static final String Columns_D5 = "dDescription"; //内容
/************************************************************************************/

}
