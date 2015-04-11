package com.example.listviewtest4;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	static List<Data> dataList = new ArrayList<Data>();	// adapter用ArrayList

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// inflaterの生成
		View v = getLayoutInflater().inflate(R.layout.button_test, null);
		// button_testレイアウトをセットする処理
		addContentView(v, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		// button1クリック処理セット
		v.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// テスト用クリック処理
				switch (v.getId()) {
				case R.id.button1:
					break;
				}
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	/**
	 * データアダプター
	 * @author masakisakamoto
	 * Top画面のListView用アダプター
	 */
	public class DataAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// itemの数を返す
			return dataList.size();
		}

		@Override
		public Object getItem(int position) {
			// 指定された位置のitemを返す
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			// itemIdを返す
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO 自動生成されたメソッド・スタブ
			TextView textview1;
			TextView textview2;
			View image;
			
			View v = convertView;
			
			if(v==null) {
				LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = inflater.inflate(R.layout.row_top, null);
			}
			Data data = (Data)getItem(position);
			if(data != null) {
				textview1 = (TextView)v.findViewById(R.id.DataTitle);
				textview2 = (TextView)v.findViewById(R.id.DataDescription);
				image = (View)v.findViewById(R.id.DataImage);
				
				textview1.setText(data.getTitle());
				textview2.setText(data.getDescription());
				image.setBackground(data.getImage().getBackground());
			}
			return v;
		}
		
	}
}
