package com.example.datapocket.item;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * GenreDataItemClass
 * ジャンル画面のアダプター用型
 */
public class GenreDataItem {

    // TODO #1 primaryKey保持変数を作成
	String title;
	String msg;
	Bitmap image;
	  
	public GenreDataItem(String title, String msg, Bitmap image){
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

    public Bitmap getImage() { return image; }
	// TODO toStringでprimaryKeyを返すようにする
	public String toString(){
	  return title + ":" + msg;
	}
}



