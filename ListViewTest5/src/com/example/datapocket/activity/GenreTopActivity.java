package com.example.datapocket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masakisakamoto on 2015/04/05.
 * ジャンル画面
 */
public class GenreTopActivity extends Activity 
    implements OnClickListener {

  static final String TAG = "ListViewTest";
  
  ListView listView;
  Button addButton;
  static List<String> dataList = new ArrayList<String>();
  static ArrayAdapter<String> adapter;
  
  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_top);
        findViews();
        setListeners();
        setAdapters();
    }
  
  protected void setAdapters() {
	  adapter = new ArrayAdapter<String>(
			  this,
			  android.R.layout.simple_list_item_1,
			  dataList);
	  listView.setAdapter(adapter);
  }
  
  protected void findViews(){
    listView = (ListView)findViewById(R.id.listView1);
    addButton = (Button)findViewById(R.id.button1);
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
	  adapter.add("Hello!");
  }

}
