package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datapocket.utility.Const;
import com.example.datapocket.utility.Key;

/**
 * Created by masakisakamoto on 2015/04/20.
 */
public class DetailActivity extends BaseBackgroundActivity {

    public static final String TAG = "DetailActivity";

    private String mLid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActionBar().setTitle("詳細データ");

        Intent intent = getIntent();
        if(intent != null) {
            mLid = intent.getStringExtra(Key.LIST_PRIMARY);
        }
        Log.v(TAG, mLid + "取得しました。");
        String[] key = mLid.split("_", -1);
        int primary = Integer.parseInt(key[1]);
        Log.v(TAG, primary + "です");

        findViews();

        // TODO:背景の設定処理
//      int genreAddBackground = SQLiteから背景データを取得する処理
//      if(!genreAddBackground==null) {
//          setBackground(genreAddBackground);
        setBackground(R.drawable.background_pocket);
    }
      // TODO ListActivityからのIntentを作成するためのメソッド パラメータがまだ不確定
//    public static Intent createIntent(Context context, String dpid) {
//        return new Intent(context, DetailActivity.class)
//                .putExtra(Key.Columns_DPID,dpid);
//    }

    public static Intent createIntent(Context context, String primary) {
        return new Intent(context, DetailActivity.class)
                .putExtra(Key.LIST_PRIMARY, primary);
    }

    private void findViews() {
        TextView detailTitle = (TextView) findViewById(R.id.detail_title);
        TextView detailHurigana = (TextView) findViewById(R.id.detail_hurigana);
        TextView detailDescription = (TextView) findViewById(R.id.detail_description);
        ImageView detailImage = (ImageView) findViewById(R.id.detail_image);
    }

    /**
     * ActionBarMenu
     * #詳細画面からの編集ボタン
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    /**
     * ActionBarClickイベント
     * #詳細画面からの編集ボタン
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.list_custom:
                Intent intent = new Intent();
                intent.setClassName(getApplicationContext(), Const.CUSTOM_LIST_ACTIVITY);
                startActivityForResult(intent, Const.REQUEST_CODE);
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
