package com.example.datapocket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * ジャンル追加画面
 * @author masakisakamoto
 * #編集保存機能
 */
public class AddGenreActivity extends Activity {

	static final String TAG = "AddGenreActivity";
    private EditText mTitleAdd;
	private EditText mDescriptionAdd;
	private ImageView imageAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_genre);
		
		findViews();
	}
	
	public void findViews() {
		mTitleAdd = (EditText)findViewById(R.id.genreTitleAdd);
		mDescriptionAdd = (EditText)findViewById(R.id.genreDataDescriptionAdd);
		imageAdd = (ImageView)findViewById(R.id.genreImageAdd);
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
			String title = mTitleAdd.getText().toString();
			String Description = mDescriptionAdd.getText().toString();
			// 入力判定処理
			if(! title.isEmpty() || ! Description.isEmpty()) {
				Log.v(TAG,"空じゃないです");
				// 梱包処理
			}
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
