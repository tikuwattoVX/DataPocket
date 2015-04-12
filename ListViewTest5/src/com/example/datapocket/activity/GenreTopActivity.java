package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
//			ListView lv = (ListView)parent;
			Intent intent =  new Intent();
			intent.setClassName("com.example.datapocket.activity", "com.example.datapocket.activity.ListActivity");
			startActivity(intent);
//			String str = lv.getItemAtPosition(position).toString();
//			Toast.makeText(getApplicationContext(), str + "clicked", Toast.LENGTH_SHORT).show();
			
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
