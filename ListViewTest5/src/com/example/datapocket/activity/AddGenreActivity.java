package com.example.datapocket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
/**
 * ジャンル追加画面
 * @author masakisakamoto
 * #編集保存機能
 */
public class AddGenreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_genre);
	}
	
	/**
	 * ActionBarMenu
	 * #編集保存ボタン
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.custom, menu);
		return true;
	}
	
	/**
	 * ActionBarClickイベント
	 * #編集保存ボタン
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.save:
			/**
			 * 1.編集入力判定処理
			 * 2.確認ダイアログ処理
			 */
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
