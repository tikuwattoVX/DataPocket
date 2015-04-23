package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.datapocket.utility.Const;

/**
 * Created by masakisakamoto on 2015/04/20.
 */
public class DetailActivity extends BaseBackgroundActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        findViews();
    }

    private void findViews() {

    }

    /**
     * ActionBarMenu
     * #詳細画面からの編集ボタン
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    /**
     * ActionBarClickイベント
     * #詳細画面からの編集ボタン
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.list_custom:
                Intent intent = new Intent();
                intent.setClassName(getApplicationContext(), Const.CUSTOM_LIST_ACTIVITY);
                startActivityForResult(intent, Const.REQUEST_CODE);
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
