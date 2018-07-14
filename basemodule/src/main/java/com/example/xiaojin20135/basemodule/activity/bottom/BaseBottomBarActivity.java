package com.example.xiaojin20135.basemodule.activity.bottom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaojin
 * @create 2018-07-14
 * @Describe 底部TAB导航基础类
 */
public class BaseBottomBarActivity extends ToolBarActivity
    implements BottomNavigationBar.OnTabSelectedListener{

    private FragmentManager fragmentManager;
    private BottomNavigationBar bottomNavigationBar;
    private final List<Fragment> fragmentList = new ArrayList<> ();
    //底部导航栏风格，
    private int mode = BottomNavigationBar.MODE_DEFAULT;
    //底部导航栏背景样式
    private int backgroundStyle = BottomNavigationBar.BACKGROUND_STYLE_DEFAULT;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        hideBarIcon();
    }

    @Override
    protected int getLayoutId () {
        return 0;
    }

    @Override
    protected void initView () {
        fragmentManager = this.getSupportFragmentManager ();
        bottomNavigationBar = (BottomNavigationBar)findViewById (R.id.bottom_navigation_bar);

    }

    @Override
    protected void initEvents () {
        bottomNavigationBar.setTabSelectedListener (this);
    }

    @Override
    protected void loadData () {

    }

    @Override
    public void onTabSelected (int position) {
        showFragment(position);
    }

    @Override
    public void onTabUnselected (int position) {

    }
    
    @Override
    public void onTabReselected (int position) {

    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 14:57
     * @Describe 加Fragment以及底部图标和文字
     */
    public void addItem(Fragment fragment,int imageId,String title,int activeColor){
        fragmentList.add (fragment);
        bottomNavigationBar.addItem (new BottomNavigationItem (imageId,title).setActiveColorResource (activeColor));
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 14:58
     * @Describe 加Fragment以及底部图标和文字
     */
    public void addItem(Fragment fragment,int imageId,int title,int activeColor){
        fragmentList.add (fragment);
        bottomNavigationBar.addItem (new BottomNavigationItem (imageId,title).setActiveColorResource (activeColor));
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 15:00
     * @Describe 设置BottomNavigationBar风格样式
     */
    protected void setNavBarStyle(int mode,int backgroundStyle){
        this.mode = mode;
        this.backgroundStyle = backgroundStyle;
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 15:02
     * @Describe 初始化容器，添加Fragment
     */
    public void initialise(int containerViewId){
        bottomNavigationBar.setMode (mode);
        bottomNavigationBar.setBackgroundStyle (backgroundStyle);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
        for(Fragment fragment : fragmentList){
            fragmentTransaction.add (containerViewId,fragment);
        }
        fragmentTransaction.commit ();
        showFragment(0);
        bottomNavigationBar.initialise ();
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 15:05
     * @Describe 显示Fragment
     */
    public void showFragment(int position){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction ();
        for(int i=0;i<fragmentList.size ();i++){
            if(i != position){
                fragmentTransaction.hide (fragmentList.get (i));
            }else{
                fragmentTransaction.show (fragmentList.get (i));
            }
        }
        fragmentTransaction.commit();
    }


}
