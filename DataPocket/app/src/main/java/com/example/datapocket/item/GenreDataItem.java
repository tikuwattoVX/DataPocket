package com.example.datapocket.item;

import android.widget.ImageView;

/**
 * GenreDataItemClass
 * ジャンル画面のアダプター用型
 */
public class GenreDataItem {
	
	String title;
	String msg;
	ImageView image;
	  
	public GenreDataItem(String title, String msg){
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



