package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.datapocket.item.GenreDataItem;
import com.example.datapocket.utility.Const;
import com.example.datapocket.utility.Key;
import com.example.datapocket.utility.MyDBHelper;

/**
 * Created by masakisakamoto on 2015/04/05.
 * GenreTopActivityClass
 * ジャンル画面設定用クラス
 *
 * メソッド
 * #onCreate(Bundle savedInstanceState)
 * #onCreateOptionsMenu(Menu menu)
 * #onOptionsItemSelected(MenuItem item)
 * #onActivityResult(int requestCode, int resultCode, Intent data)
 * #setAdapters()
 * #findViews()
 */
public class GenreTopActivity extends BaseBackgroundActivity {

  static final String TAG = "GenreTopActivity";

  private ListView mListView;

  static List<GenreDataItem> dataList = new ArrayList<GenreDataItem>();
  static GenreAdapter adapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_genre_top);

      findViews();
      setAdapters();

//      MyDBHelper helper = new MyDBHelper(this);
//      final SQLiteDatabase db = helper.getWritableDatabase();

      // TODO # サンプルデータベースを表示する処理
      // 初回起動判定及び初回起動処理を行うメソッド

      // helper.isStartFirst();

      // SQLiteのGenreデータ取得処理
      // dataList = helper.selectGenre();
      // adapter.notifyDataSetChanged();

      /*** ここからテスト用のデータ　本番では消す ***/
      setBackground(R.drawable.background_pocket);
      dataList.add(new GenreDataItem(
      // TODO #3 サンプルをちゃんとしたものになおす
              "魚料理",
              "サーモン料理は、主に魚です。"));
      adapter.notifyDataSetChanged();
      /***************** ここまで ***************/

      // TODO #4 サンプル保存処理を記述
//      } else {
//          Log.v(TAG, "false通りました。");
      // TODO #5 SQLiteから背景データを読み取り描画する。データがなければデフォルトを設定する
//        int genreBackground = SQLiteから背景データを取得する処理
//        if(!genreBackground==null) {
//        setBackground(genreBackground);
//        }
      // TODO #6 SQLiteからタイトル、説明、画像を読み込み描画する
//      }
  }
  
  /**
   * ActionBarMenu
   * #ジャンル追加ボタン
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
	  MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
	  	return true;
  }

  /**
   * ActionBarClickイベント
   * #ジャンル追加ボタン
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
	  switch(item.getItemId()) {
	  	case R.id.genre_add:
	  		Intent intent = new Intent();
	  		intent.setClassName(getApplicationContext(), Const.ADD_GENRE_ACTIVITY);
	  		startActivityForResult(intent, Const.REQUEST_CODE);
		default:
			return super.onOptionsItemSelected(item);
	  }
	
  }

  /**
   * AddGenreActivityがfinishにより破棄された際に呼ばれるメソッド。
   * #編集データの回収と保存
   * #編集内容を画面に表示
   */
  @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	 
    switch (requestCode) {
    case Const.REQUEST_CODE:
      if (resultCode == RESULT_OK) {
    	Bundle bundle = data.getExtras();
        String keyTitle = bundle.getString(Key.GENRE_TITLE);
        String keyDescription = bundle.getString(Key.GENRE_DESCRIPTION);

        // TODO #1 取得したデータをSQLiteに保存する処理を記述する

        // TODO #2 描画処理を保存したSQLiteからの読み込みに変更する　
        dataList.add(new GenreDataItem(keyTitle, keyDescription));
        adapter.notifyDataSetChanged();
            
      }
      break;
 
    default:
      break;
    }
}

protected void setAdapters() {
	  adapter = new GenreAdapter();
	  mListView.setAdapter(adapter);
  }
  
  protected void findViews(){
    mListView = (ListView)findViewById(R.id.listView1);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // startActivity(createIntent(key.value, key.value, class))
            ListView listView = (ListView)parent;
            GenreDataItem genreDataItem =(GenreDataItem)listView.getItemAtPosition(position);
            Log.v(TAG, genreDataItem.toString());
            Intent intent = new Intent();
            intent.setClassName(getApplicationContext(), Const.LIST_ACTIVITY);
            startActivity(intent);

        }


    });
  }
  
  private class GenreAdapter extends BaseAdapter {

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(
		int position,
		View convertView,
		ViewGroup parent) {
        // TODO #7 画像も扱えるようにする
		
		TextView textView1;
		TextView textView2;
		View v = convertView;
		
		if(v==null){
	        LayoutInflater inflater = 
	          (LayoutInflater)
	            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        v = inflater.inflate(R.layout.row_genre, null);
	      }
	      GenreDataItem data = (GenreDataItem)getItem(position);
	      if(data != null){
	        textView1 = (TextView) v.findViewById(R.id.genreTitle);
	        textView2 = (TextView) v.findViewById(R.id.genreMessage);
	        
	        textView1.setText(data.getTitle());
	        textView2.setText(data.getMsg());
	      }
	      return v;
	}
	  
  }
}

// TODO #8 GenreTopのレイアウトを修正する。margin,paddingなど