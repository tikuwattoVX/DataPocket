package com.example.datapocket.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by masakisakamoto on 2015/05/06.
 */
public class CustomAllFragmentActivity extends FragmentActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_all);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(
                new CustomAllPagerAdapter(getSupportFragmentManager())
        );
    }

    public class CustomAllPagerAdapter extends FragmentStatePagerAdapter {

        public CustomAllPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            switch(i) {
                case 0:
                    return new CustomGenreFragment();
                case 1:
                    return new CustomListFragment();
                default:
                    return new CustomDetailFragment();
            }
        }


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch(position) {
                case 0:
                    return "page" + position;
                case 1:
                    return "page" + position;
                default:
                    return "page" + position;
            }
        }
    }
}
