package com.example.listviewtest4;

import android.view.View;

public class Data {
	
	private String mTitle;
	private String mDescription;
	private View mImage;
	
	// コンストラクタ（String, String）
	public Data(String title, String description) {
		this.mTitle = title;
		this.mDescription = description;
		this.mImage = null;
	}
	// コンストラクタ（String, String, View）
	public Data(String title, String description, View image) {
		this.mTitle = title;
		this.mDescription = description;
		this.mImage = image;
	}
	/**
	 * タイトル取得メソッド
	 * @return タイトル
	 */
	public String getTitle(){
		return this.mTitle;
	}
	/**
	 * 説明取得メソッド
	 * @return 説明
	 */
	public String getDescription(){
		return this.mDescription;
	}
	/**
	 * 画像取得メソッド
	 * @return 画像
	 */
	public View getImage(){
		return this.mImage;
	}
}
