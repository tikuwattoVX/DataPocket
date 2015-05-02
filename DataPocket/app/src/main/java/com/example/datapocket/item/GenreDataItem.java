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
	String image;
	  
	public GenreDataItem(String title, String msg){
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
	// TODO toStringでprimaryKeyを返すようにする
	public String toString(){
	  return title + ":" + msg;
	}
}



