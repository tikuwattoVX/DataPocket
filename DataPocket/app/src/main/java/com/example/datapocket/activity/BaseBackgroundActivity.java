package com.example.datapocket.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.FrameLayout;

/**
 * Created by masakisakamoto on 2015/04/19.
 * BaseBackgroundActivity class
 * Activityの背景を設定するclass、Activityに継承させて使用する。
 *
 * メソッド
 * #setContentView(int layoutResID)
 * #setBackground(int drawableID)
 */
public class BaseBackgroundActivity extends Activity {

    private FrameLayout mFrameLayout;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_background);

        mFrameLayout =(FrameLayout)findViewById(R.id.backgroundBase);
        getLayoutInflater().inflate(layoutResID, mFrameLayout);
    }

    /**
     * 背景変更用メソッド
     * @param drawableID 例：R.drawable.background
     */
    public void setBackground(int drawableID) {
        mFrameLayout = (FrameLayout) findViewById(R.id.backgroundBase);
        mFrameLayout.setBackgroundResource(drawableID);
    }
}
