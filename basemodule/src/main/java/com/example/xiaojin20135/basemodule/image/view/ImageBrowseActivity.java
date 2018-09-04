package com.example.xiaojin20135.basemodule.image.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;

import java.util.ArrayList;

public class ImageBrowseActivity extends ToolBarActivity {
    private ArrayList<String> imageList = new ArrayList<> ();
    private ViewPager imageBrowseViewPager;
    private ImageBrowseAdapter imageBrowseAdapter;
    private int currentIndex = 0;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_image_browse;
    }

    @Override
    protected void initView () {
        imageBrowseViewPager = (ViewPager)findViewById (R.id.imageBrowseViewPager);
        imageBrowseAdapter = new ImageBrowseAdapter (this);
        imageBrowseViewPager.setAdapter (imageBrowseAdapter);
        imageBrowseViewPager.addOnPageChangeListener (new ViewPager.OnPageChangeListener () {
            @Override
            public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
                currentIndex = position;
                Log.d (TAG,"currentIndex = " + currentIndex);
            }

            @Override
            public void onPageSelected (int position) {

            }

            @Override
            public void onPageScrollStateChanged (int state) {

            }
        });

    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {
        Intent intent = getIntent ();
        if(intent != null){
            currentIndex = intent.getIntExtra("index", 0);
            imageList = (ArrayList<String>) intent.getStringArrayListExtra("imageList");
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.deletemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if(item.getItemId () == R.id.delete_item){
            if(imageList != null && imageList.size () > currentIndex){
                imageList.remove (currentIndex);
//                imageBrowseAdapter.
                imageBrowseAdapter.notifyDataSetChanged ();
            }
            if(imageList == null || imageList.size () == 0){
                back();
            }
        }
        return super.onOptionsItemSelected (item);
    }

    private class ImageBrowseAdapter extends PagerAdapter {
        Context context;
        public ImageBrowseAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount () {
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject (@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public View instantiateItem(ViewGroup container,int position){
            final PhotoView image = new PhotoView (context);
            // 开启图片缩放功能
            image.enable();
            // 设置缩放类型
            image.setScaleType (ImageView.ScaleType.CENTER_INSIDE);
            // 设置最大缩放倍数
            image.setMaxScale (2.5f);
            // 加载图片
            Glide.with(context)
                .load(imageList.get (position))
                .into(image);
            // 单击图片，返回
            image.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick (View v) {
                    image.disenable();
                    finish();
                }
            });
            container.addView(image);
            return image;
        }

        @Override
        public void destroyItem (@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView ((View) object);
        }

    }

    @Override
    public void initToolbar() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK == keyCode){
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void back(){
        Intent intent = new Intent ();
        intent.putStringArrayListExtra ("imageList",imageList);
        setResult (RESULT_OK,intent);
        this.finish ();

    }
}
