package com.example.datapocket.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.datapocket.utility.Const;
import com.example.datapocket.utility.Key;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * リスト追加画面
 * @author masakisakamoto
 * #編集保存機能
 */
public class AddListActivity extends BaseBackgroundActivity {

    static final String TAG = "AddListActivity";

    private EditText mTitleAdd;
    private EditText mDescriptionAdd;
    private ImageView mImageAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        findViews();

        // TODO:背景の設定処理
//      int genreAddBackground = SQLiteから背景データを取得する処理
//      if(!genreAddBackground==null) {
//          setBackground(genreAddBackground);
        setBackground(R.drawable.background_pocket);
//      }
    }

    public void findViews() {
        mTitleAdd = (EditText) findViewById(R.id.listTitleAdd);
        mDescriptionAdd = (EditText) findViewById(R.id.listDataDescriptionAdd);
        mImageAdd = (ImageView) findViewById(R.id.listImageAdd);
        mImageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.listImageAdd:
                        Log.v(TAG, "イメージクリックしました。");
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_PICK);
                        startActivityForResult(intent, Const.REQUEST_GALLERY);
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Const.REQUEST_GALLERY) {
            Log.v(TAG, "キャッチしました。1");
            if (resultCode == RESULT_OK) {
                Log.v(TAG, "キャッチしました。2");
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

            mImageAdd.setImageBitmap(bmp);
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
                String title = mTitleAdd.getText().toString();
                String description = mDescriptionAdd.getText().toString();
                // 入力判定処理
                if(! title.isEmpty() || ! description.isEmpty()) {
                    // TODO:梱包処理
                    Intent data = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(Key.LIST_TITLE,title);
                    bundle.putString(Key.LIST_DESCRIPTION,description);
                    if(mImageAdd.getDrawable() != null) {   // 編集した画像がある場合
                        Bitmap image = ((BitmapDrawable) mImageAdd.getDrawable()).getBitmap();
                        // TODO 画像ファイル名のフォーマットをカレンダーから取得した値にする。
//                    String timeStamp = new SimpleDateFormat(
//                            "yyyy_MM_dd_hh_mm_ss").format(Calendar.getInstance());
                        String imageName = "saka.png";
                        File imageFile = new File(getFilesDir(), imageName);
                        FileOutputStream out;
                        try {
                            out = new FileOutputStream(imageFile);
                            image.compress(Bitmap.CompressFormat.PNG, 0, out);
                            ///画像をアプリの内部領域に保存
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        bundle.putString(Key.LIST_IMAGE,
                                imageFile.getAbsolutePath());
//                    Log.v(TAG, "画像あるよ");
//                    Bitmap image = ((BitmapDrawable) mImageAdd.getDrawable()).getBitmap();
//                    bundle.putParcelable(Key.GENRE_IMAGE, image);
                    }
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
