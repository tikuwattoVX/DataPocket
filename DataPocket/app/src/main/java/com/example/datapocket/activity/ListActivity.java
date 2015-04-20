package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.datapocket.item.GenreDataItem;
import com.example.datapocket.item.ListDataItem;
import com.example.datapocket.utility.Const;
import com.example.datapocket.utility.Key;

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

        findViews();
        setAdapters();

        SharedPreferences pref = getSharedPreferences(Key.SYSTEM_VALUE, MODE_PRIVATE);
        // 初回起動かどうかの判定処理
//      if (pref.getBoolean("LIST_START_FIRST", true)) {
//          Log.v(TAG, "true通りました。");
        // サンプルデータベースを表示する処理
        setBackground(R.drawable.background_pocket);
        dataList.add(new ListDataItem(
                "マグロとアボカドのタルタル",
                "４人前"));
        adapter.notifyDataSetChanged();
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Key.LIST_START_FIRST, false);
        editor.commit();
        // TODO:サンプル保存処理を記述
//      } else {
//          Log.v(TAG, "false通りました。");
        // TODO:背景の設定処理
//        int listBackground = SQLiteから背景データを取得する処理
//        if(!listBackground==null) {
//        setBackground(listBackground);
//        }
        // TODO:タイトル、説明、画像セット処理
//      }
	}

    protected void findViews() {
        mListView = (ListView) findViewById(R.id.listView2);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(getApplicationContext(), Const.DETAIL_ACTIVITY);
                startActivity(intent);

            }


        });
    }

    protected void setAdapters() {
        adapter = new ListAdapter();
        mListView.setAdapter(adapter);
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
         public View getView(int position, View convertView, ViewGroup parent) {

                 TextView textView1;
                 TextView textView2;
                 View v = convertView;

                 if(v==null){
                     LayoutInflater inflater =
                             (LayoutInflater)
                                     getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                     v = inflater.inflate(R.layout.row_list, null);
                 }
                 ListDataItem data = (ListDataItem)getItem(position);
                 if(data != null){
                     textView1 = (TextView) v.findViewById(R.id.listTitle);
                     textView2 = (TextView) v.findViewById(R.id.listMessage);

                     textView1.setText(data.getTitle());
                     textView2.setText(data.getMsg());
                 }
                 return v;
             }
     }

}
