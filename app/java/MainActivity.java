package com.example.masakisakamoto.sqlitetest;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ListView listview;
    static List<Item> dataList = new ArrayList<Item>();
    static ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findViewById
        listview = (ListView)findViewById(R.id.listViewTop);

        addItem();

        setAdapters();
    }

    private void addItem() {
        for(int i=0; i < 3; i++) {
            dataList.add(
                    new Item(
                            "title" + i,
                            "sakamoto",
                            "masaki"
                    )
            );
        }
    }

    private void setAdapters() {
        adapter = new ItemAdapter();
        listview.setAdapter(adapter);
    }

    // customadapter
    private class ItemAdapter extends BaseAdapter {

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

            TextView text1;
            TextView text2;
            TextView text3;
            View v = convertView;

            if(v==null){
                LayoutInflater inflater =
                        (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.row, null);
            }
            Item item = (Item)getItem(position);
            if(item != null) {
                text1 = (TextView) v.findViewById(R.id.textView1);
                text2 = (TextView) v.findViewById(R.id.textView2);
                text3 = (TextView) v.findViewById(R.id.textView3);

                text1.setText(item.string1);
                text2.setText(item.string2);
                text3.setText(item.string3);
            }
            return v;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
