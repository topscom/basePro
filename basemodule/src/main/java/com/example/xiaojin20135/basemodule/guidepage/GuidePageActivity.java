package com.example.xiaojin20135.basemodule.guidepage;

import android.app.Activity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.image.adapter.ImageBrowseAdapter;
import com.example.xiaojin20135.basemodule.image.adapter.MyPhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GuidePageActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ViewPager guide_page_VP;
    private ArrayList<Integer> imageIdList= new ArrayList ();//图片资源数组
    private List<View> viewList = new ArrayList<> ();//图片资源的集合
    private ViewGroup viewGroup;//放置原点
    //实例化原点View
    private ImageView point_IV;
    private ImageView[] pointArray;
    //最后一页的按钮
    private TextView guide_start_TV;
    private LinearLayout.LayoutParams layoutParams;
    //跳过
    private TextView jump_TV;
    //结束后跳转到的Activity
    private String activityName = "";
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_guide_page;
    }

    @Override
    protected void initView () {
        guide_page_VP = (ViewPager)findViewById (R.id.guide_page_VP);
        guide_start_TV = (TextView) findViewById (R.id.guide_start_TV);
        viewGroup = (ViewGroup)findViewById (R.id.guide_point_LL);
        jump_TV = (TextView) findViewById (R.id.jump_TV);
        //设置边框
        //加载ViewPager
        initViewPager();
        //加载底部圆点
        initPoint();
    }

    @Override
    protected void initEvents () {
        guide_start_TV.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                done ();
            }
        });
        jump_TV.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                done();
            }
        });
    }

    @Override
    protected void loadData () {
        imageIdList = getIntent ().getIntegerArrayListExtra ("imagesList");
        activityName = getIntent ().getStringExtra ("activityName");
    }

    private void initViewPager(){
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams (LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //循环创建View并加入到集合中
        int len = imageIdList.size ();
        for(int i=0;i<len;i++){
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView (this);
            imageView.setLayoutParams (params);
            imageView.setBackgroundResource (imageIdList.get (i));
            //将ImageView添加到集合中
            viewList.add (imageView);
        }
        GuidePageAdapter guidePageAdapter = new GuidePageAdapter (viewList);
        guide_page_VP.setAdapter (guidePageAdapter);
        guide_page_VP.addOnPageChangeListener (this);

    }

    private void initPoint(){
        //根据图片数量实例化数组
        pointArray = new ImageView[viewList.size ()];
        int size = viewList.size ();
        for(int i=0;i<size;i++){
            point_IV = new ImageView (this);
            layoutParams = new LinearLayout.LayoutParams (30,30);
            //第一个页面需要设置为选中状态，这里采用两张不同的图片
            if(i == 0){
                point_IV.setBackgroundResource (R.drawable.ic_select);
            }else{
                layoutParams.leftMargin = 5;
                point_IV.setBackgroundResource (R.drawable.ic_unselect);
            }
            point_IV.setLayoutParams (layoutParams);
            point_IV.setPadding (30,0,30,0);
            pointArray[i] = point_IV;
            //将数组中的ImageView添加到ViewGroup
            viewGroup.addView (pointArray[i]);
        }
    }


    @Override
    public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected (int position) {
        int length = imageIdList.size ();
        Log.d (TAG,"onPageSelected length = "  + length);
        for(int i=0;i<length;i++){
            pointArray[position].setBackgroundResource (R.drawable.ic_select);
            if(position != i){
                pointArray[i].setBackgroundResource (R.drawable.ic_unselect);
            }
        }
        //判断是否是最后一页，若是，则显示按钮
        if(position == length - 1){
            guide_start_TV.setVisibility (View.VISIBLE);
        }else{
            guide_start_TV.setVisibility (View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged (int state) {
        Log.d (TAG,"");
    }


    private void done(){
        this.finish ();
        if(activityName != null && !TextUtils.isEmpty (activityName)){
            try {
                canGo (Class.forName (activityName));
            } catch (ClassNotFoundException e) {
                e.printStackTrace ();
            }
        }
    }
}
