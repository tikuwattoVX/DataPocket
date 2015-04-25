package com.example.datapocket.activity;

import android.app.Activity;
import android.os.Bundle;
/**
 * リストからの編集画面
 * @author masakisakamoto
 *
 */
public class CustomAllActivity extends BaseBackgroundActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_all);

        findViews();

        // TODO:背景の設定処理
//      int genreAddBackground = SQLiteから背景データを取得する処理
//      if(!genreAddBackground==null) {
//          setBackground(genreAddBackground);
        setBackground(R.drawable.background_pocket);
//      }
	}

    private void findViews() {
        // TODO:取得処理
    }

}
