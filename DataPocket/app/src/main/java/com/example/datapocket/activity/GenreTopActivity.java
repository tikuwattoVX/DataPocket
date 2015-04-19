package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

  static final String TAG = "ListViewTest";

  private ListView mListView;

  static List<GenreDataItem> dataList = new ArrayList<GenreDataItem>();
  static GenreAdapter adapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_genre_top);

      findViews();
      setAdapters();

      SharedPreferences pref = getSharedPreferences("SYSTEM_VALUE", MODE_PRIVATE);
      // 初回起動かどうかの判定処理
//      if (pref.getBoolean("START_FIRST", true)) {
//          Log.v(TAG, "true通りました。");
          // サンプルデータベースを表示する処理
          setBackground(R.drawable.background_pocket);
          dataList.add(new GenreDataItem(
                  "魚料理",
                  "サーモン料理は、主に魚です。"));
          adapter.notifyDataSetChanged();
          SharedPreferences.Editor editor = pref.edit();
          editor.putBoolean("START_FIRST", false);
          editor.commit();
          // TODO:サンプル保存処理を記述
//      } else {
//          Log.v(TAG, "false通りました。");
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
	  		intent.setClassName(getApplicationContext(), "com.example.datapocket.activity.AddGenreActivity");
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
        String keyTitle = bundle.getString("GENRE_TITLE");
        String keyDescription = bundle.getString("GENRE_DESCRIPTION");
        /**
         * TODO:bundle.getInt("key.intData")などを使ってデータを回収する処理
         * 2.画面に描画する処理
         * 3.SQLiteでデータを保存する処理
         */
            
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
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            Intent intent = new Intent();
            intent.setClassName(getApplicationContext(), "com.example.datapocket.activity.ListActivity");
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
