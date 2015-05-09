package com.example.datapocket.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by masakisakamoto on 2015/05/09.
 */
public class BaseBackgroundFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.activity_background, null);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
