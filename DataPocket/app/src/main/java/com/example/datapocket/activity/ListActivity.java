package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ListActivity extends BaseBackgroundActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
	}
	
	 /**
	  * ActionBarMenu
	  * #リスト追加ボタン
	  * #編集ボタン
	  */
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.list, menu);
		return true;
	 }
	 
	 /**
	  * ActionBarClickイベント
	  * #リスト追加ボタン
	  * #編集ボタン
	  */
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch(item.getItemId()) {
		  case R.id.list_add:	// リスト追加ボタン
		  	  intent.setClassName(getApplicationContext(), "com.example.datapocket.activity.AddListActivity");
		  	  startActivity(intent);
		  	  return true;
		  case R.id.all_custom:	// 編集ボタン
			  intent.setClassName(getApplicationContext(), "com.example.datapocket.activity.CustomAllActivity");
			  startActivity(intent);
			  return true;
		  default:
			  return super.onOptionsItemSelected(item);
		}
		
	 }

}
