package com.example.xiaojin20135.basemodule.activity.tablay;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.adapter.ViewPagerAdapter;

import java.util.ArrayList;

public abstract class BaseTapActivity extends ToolBarActivity {
    private TabLayout base_tablayout;
    private ViewPager base_viewpager;
    private String[] titlesArr;
    private ArrayList<Fragment> fragments;
    private ViewPagerAdapter viewPagerAdapter;
    private boolean autoRefresh = true; //viewpager是否自动刷新
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }


    @Override
    protected void initView () {
        //初始化TabLayout和ViewPager
        base_tablayout = (TabLayout)findViewById (R.id.base_tablayout);
        base_viewpager = (ViewPager)findViewById (R.id.base_viewpager);
        //页面适配器
        viewPagerAdapter = new ViewPagerAdapter (getSupportFragmentManager ());
    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {

    }

    /**
     * @author lixiaojin
     * @createon 2018-08-23 10:45
     * @Describe 传入数据，并展示
     */
    protected String showTap(String[] titlesArr,ArrayList<Fragment> fragments){
        if(titlesArr != null && fragments != null && titlesArr.length == fragments.size ()){
            this.titlesArr = titlesArr;
            this.fragments = fragments;
            viewPagerAdapter.setTitlesArr (titlesArr);
            viewPagerAdapter.addFragments(fragments);
            base_viewpager.addOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener (base_tablayout));
            base_viewpager.setAdapter (viewPagerAdapter);
            base_tablayout.setupWithViewPager (base_viewpager,autoRefresh);
            return "";
        }else{
            return "标题数组和页面数组不能为空并且必须相等！";
        }
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-23 11:25
     * @Describe 设置TabLayout格式，默认为滚动模式，适应于多个同时显示，如果数量较少，使用固定模式
     */
    public void setTabMode(int mode){
        base_tablayout.setTabMode (mode);
    }


}
