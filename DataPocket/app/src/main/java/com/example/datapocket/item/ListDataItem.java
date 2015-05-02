package com.example.datapocket.item;

import android.widget.ImageView;

/**
 * ListDataItemClass
 * リスト画面のアダプター用型
 */
public class ListDataItem {

    int primary;    // primaryKey
    String title;   // ListTitle
    String msg;     // ListMessage
    String image;   // ListImagePath

    public ListDataItem(int primary, String title, String msg){
        this.primary = primary;
        this.title = title;
        this.msg = msg;
        this.image = image;
    }
    public String getTitle(){
        return title;
    }

    public String getMsg(){
        return msg;
    }

    public String getImage() { return image; }

    public String toString(){
        return "key_" + primary;
    }
}