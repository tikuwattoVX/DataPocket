package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.datapocket.utility.Const;

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
		  	  intent.setClassName(getApplicationContext(), Const.ADD_LIST_ACTIVITY);
		  	  startActivity(intent);
		  	  return true;
		  case R.id.all_custom:	// 編集ボタン
			  intent.setClassName(getApplicationContext(), Const.CUSTOM_ALL_ACTIVITY);
			  startActivity(intent);
			  return true;
		  default:
			  return super.onOptionsItemSelected(item);
		}
		
	 }

}
