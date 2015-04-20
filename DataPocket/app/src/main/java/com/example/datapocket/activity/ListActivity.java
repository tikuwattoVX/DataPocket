package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.datapocket.item.GenreDataItem;
import com.example.datapocket.item.ListDataItem;
import com.example.datapocket.utility.Const;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends BaseBackgroundActivity {

    static final String TAG = "ListViewTest";

    private ListView mListView;

    static List<ListDataItem> dataList = new ArrayList<ListDataItem>();
    static ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
	}
	
	 /**
	  * ActionBarMenu
	  * #リスト追加ボタン
	  * #編集ボタン
	  */
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.list, menu);
		return true;
	 }
	 
	 /**
	  * ActionBarClickイベント
	  * #リスト追加ボタン
	  * #編集ボタン
	  */
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch(item.getItemId()) {
		  case R.id.list_add:	// リスト追加ボタン
		  	  intent.setClassName(getApplicationContext(), Const.ADD_LIST_ACTIVITY);
		  	  startActivity(intent);
		  	  return true;
		  case R.id.all_custom:	// 編集ボタン
			  intent.setClassName(getApplicationContext(), Const.CUSTOM_ALL_ACTIVITY);
			  startActivity(intent);
			  return true;
		  default:
			  return super.onOptionsItemSelected(item);
		}
		
	 }

    /**
     * ListAdapterClass
     * メソッド
     * #getCount()
     * #getItem(int position)
     * #getItemId(int position)
     * #getView(int position, View convertView, ViewGroup parent)
     */
     private class ListAdapter extends BaseAdapter {

         @Override
         public int getCount() {
             return 0;
         }

         @Override
         public Object getItem(int position) {
             return null;
         }

         @Override
         public long getItemId(int position) {
             return 0;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
             return null;
         }
     }

}
