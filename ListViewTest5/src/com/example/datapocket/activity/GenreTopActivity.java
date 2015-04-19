package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.datapocket.item.GenreDataItem;

/**
 * Created by masakisakamoto on 2015/04/05.
 * ジャンル画面
 */
public class GenreTopActivity extends Activity 
    implements OnClickListener {

  static final String TAG = "ListViewTest";
  static final int REQUEST_CODE = 1001;
  
  ListView listView;
  Button addButton;
  static List<GenreDataItem> dataList = new ArrayList<GenreDataItem>();
//  static ArrayAdapter<GenreDataItem> adapter;
  static GenreAdapter adapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_top);
        findViews();
        setListeners();
        setAdapters();
        // 初回起動時はサンプルデータベースを表示する処理
        // prefの値がtrueの場合通る
//        if() {
        	// 表示処理
//        }
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
	  		startActivityForResult(intent, REQUEST_CODE);
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
    case REQUEST_CODE:
      if (resultCode == RESULT_OK) {
    	Bundle bundle = data.getExtras();
        /**
         * 1.bundle.getString("key.StringData")
         * bundle.getInt("key.intData")などを使ってデータを回収する処理
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
	  /* adapter = new ArrayAdapter<GenreDataItem>(
			  this,
			  android.R.layout.simple_list_item_1,
			  dataList); */
	  adapter = new GenreAdapter();
	  listView.setAdapter(adapter);
  }
  
  protected void findViews(){
    listView = (ListView)findViewById(R.id.listView1);
    addButton = (Button)findViewById(R.id.button1);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent =  new Intent();
			intent.setClassName(getApplicationContext(), "com.example.datapocket.activity.ListActivity");
			startActivity(intent);
			
		}
    	
    	
	});
  }
  
  protected void setListeners(){
    addButton.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch(v.getId()){
    case R.id.button1:
      addItem();
      break;
    }
  }
    
  protected void addItem(){
	  dataList.add(new GenreDataItem(
		      "肉料理", 
		      "肉肉しい肉料理は、主に肉です。"));
	  adapter.notifyDataSetChanged();
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
