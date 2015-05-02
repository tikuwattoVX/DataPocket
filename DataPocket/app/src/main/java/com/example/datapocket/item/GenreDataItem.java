package com.example.datapocket.item;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * GenreDataItemClass
 * ジャンル画面のアダプター用型
 */
public class GenreDataItem {

    int primary;    // primaryKey
	String title;   // GenreTitle
	String msg;     // GenreMessage
	String image;   // GenreImagePath
	  
	public GenreDataItem(int primary, String title, String msg){
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



