package com.example.datapocket.item;

import android.widget.ImageView;

/**
 * ListDataItemClass
 * リスト画面のアダプター用型
 */
public class ListDataItem {

    String title;
    String msg;
    ImageView image;

    public ListDataItem(String title, String msg){
        this.title = title;
        this.msg = msg;
    }

    public String getTitle(){
        return title;
    }

    public String getMsg(){
        return msg;
    }

    public String toString(){
        return title + ":" + msg;
    }
}
