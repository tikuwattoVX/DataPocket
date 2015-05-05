package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.datapocket.utility.Const;

import java.io.InputStream;

/**
 * リストからの編集画面
 * @author masakisakamoto
 *
 */
public class CustomAllActivity extends BaseBackgroundActivity {

    private ImageView mGenreImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_all);

        getActionBar().setTitle("データベース編集");

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
        mGenreImage = (ImageView)findViewById(R.id.genreImage);
        mGenreImage.setOnClickListener(setGenreImageListener);
    }

    /**
     * GenreTop画面の背景設定用Listener
     */
    View.OnClickListener setGenreImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                    Log.v("TAG", "イメージクリックしました。");
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_PICK);
                    startActivityForResult(intent, Const.REQUEST_GALLERY);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Const.REQUEST_GALLERY) {
            Log.v("TAG", "キャッチしました。1");
            if (resultCode == RESULT_OK) {
                Log.v("TAG", "キャッチしました。2");
                putImage(data);
            }
            // 画像が選択されていない場合は何もしない。
        }
    }

    /**
     * putImageメソッド
     * 追加画像の表示を選択したものに切り替えるmメソッド
     * @param intent
     */
    public void putImage(Intent intent) {
        try {
            InputStream stream = getContentResolver().openInputStream(intent.getData());
            Bitmap bmp = BitmapFactory.decodeStream(stream);
            stream.close();

            mGenreImage.setImageBitmap(bmp);
        } catch (Exception e) {
        }
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
//                String title = mTitleAdd.getText().toString();
//                String Description = mDescriptionAdd.getText().toString();
                // 入力判定処理
//                if(! title.isEmpty() || ! Description.isEmpty()) {
                // TODO:梱包処理
//                    Intent data = new Intent();
//                    Bundle bundle = new Bundle();
//                    bundle.putString(Key.GENRE_TITLE,title);
//                    bundle.putString(Key.GENRE_DESCRIPTION,Description);
//                    data.putExtras(bundle);

//                    setResult(RESULT_OK, data);
                finish();
//                }
//                else {
                // TODO:警告ダイアログ
//                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
