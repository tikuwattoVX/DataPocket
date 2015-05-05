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
    public static final String GENRE_PRIMARY = "genrePrimary";

    // ListActivity
    public static final String LIST_START_FIRST = "listStartFirst";
    public static final String LIST_PRIMARY = "listPrimary";

    // AddGenreActivity
    public static final String GENRE_IMAGE = "genreImage";

    public static final String LIST_TITLE = "listTitle";
    public static final String LIST_DESCRIPTION = "listDescription";
    public static final String LIST_IMAGE = "listImage";


    //テーブル名
    public static final String TABLE_NAME = "DataPocket";
    //プライマリキー
    public static final String GenreID = "gID";
    public static final String Columns_DPID = "DPID";

    //ジャンル画面
    public static final String Columns_G1 = "gTitle";
    public static final String Columns_G2 = "gPicture";
    public static final String Columns_G3 = "gDescription";
    public static final String Columns_G4 = "gListKey";//リストテーブルへのキー
    //リスト & 詳細画面
    public static final String ListID = "dListKey";
    public static final String Columns_D1 = "dHurigana";
    public static final String Columns_D2 = "dTittle";
    public static final String Columns_D3 = "dPicture";
    public static final String Columns_D4 = "dDescription";
}
