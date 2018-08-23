package com.example.xiaojin20135.basemodule.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaojin20135 on 2017-11-20.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "ViewPagerAdapter";
    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titlesArr;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public int getCount() {
        Log.d(TAG,"fragmentList.size() = " + fragmentList.size());
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    public void addFragments(ArrayList<Fragment> fragments){
        fragmentList = fragments;
    }

    public void setTitlesArr (String[] titlesArr) {
        this.titlesArr = titlesArr;
    }

    @Override
    public CharSequence getPageTitle (int position) {
        return titlesArr[position];
    }
}

