package com.example.datapocket.activity;

import com.example.datapocket.utility.Const;
import com.example.datapocket.utility.Key;

import android.app.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
public class AddGenreActivity extends BaseBackgroundActivity {

	static final String TAG = "AddGenreActivity";

    private EditText mTitleAdd;
	private EditText mDescriptionAdd;
	private ImageView mImageAdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_genre);

		findViews();

     // TODO:背景の設定処理
//      int genreAddBackground = SQLiteから背景データを取得する処理
//      if(!genreAddBackground==null) {
//          setBackground(genreAddBackground);
        setBackground(R.drawable.background_pocket);
//      }
	}
	
	public void findViews() {
		mTitleAdd = (EditText)findViewById(R.id.genreTitleAdd);
		mDescriptionAdd = (EditText)findViewById(R.id.genreDataDescriptionAdd);
//		imageAdd = (ImageView)findViewById(R.id.genreImageAdd);
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
				// TODO:梱包処理
                Intent data = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(Key.GENRE_TITLE,title);
                bundle.putString(Key.GENRE_DESCRIPTION,Description);
                data.putExtras(bundle);

                setResult(RESULT_OK, data);
                finish();
			}
            else {
                /* 未入力の場合、警告ダイアログを表示する処理 */
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("入力エラー");
                alertDialogBuilder.setMessage("未入力です。");
                alertDialogBuilder.setPositiveButton("確認",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }

                        });
                alertDialogBuilder.setCancelable(true);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
