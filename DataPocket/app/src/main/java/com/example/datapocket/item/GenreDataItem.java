package com.example.datapocket.item;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * GenreDataItemClass
 * ジャンル画面のアダプター用型
 */
public class GenreDataItem {

    private int mPrimary;    // primaryKey
	private String mTitle;   // GenreTitle
	private String mMsg;     // GenreMessage
	private String mImage;   // GenreImagePath
    private String mListPrimary;// ListPrimary
	  
	public GenreDataItem(int primary, String title, String msg, String listprimary){
      this.mPrimary = primary;
	  this.mTitle = title;
	  this.mMsg = msg;
      this.mListPrimary = listprimary;
//      this.mImage = image;
	}
    public int getPrimary() {return mPrimary; }

	public String getTitle(){
	  return mTitle;
	}
	  
	public String getMsg(){
	  return mMsg;
	}

    public String getImage() { return mImage; }

    public String getmListPrimary() { return mListPrimary; }

	public String toString(){
	  return "key_" + mListPrimary; }
}



