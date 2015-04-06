package com.example.sample.datapocket.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.example.sample.datapocket.R;
import com.example.sample.datapocket.adapter.GenreAdapter;
import com.example.sample.datapocket.item.DataItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masakisakamoto on 2015/04/05.
 */
public class GenreTopActivity extends ActionBarActivity {

    ListView listViewGenre;

    static List<DataItem> dataList = new ArrayList<DataItem>();
    static GenreAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_top);

        listViewGenre = (ListView)findViewById(R.id.listViewGenre);

        adapter = new GenreAdapter();
        listViewGenre.setAdapter(adapter);
    }
}
