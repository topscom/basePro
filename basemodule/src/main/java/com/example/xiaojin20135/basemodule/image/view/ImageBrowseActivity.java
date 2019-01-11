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
import com.example.xiaojin20135.basemodule.image.adapter.ImageBrowseAdapter;

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
        imageBrowseAdapter = new ImageBrowseAdapter (this,imageList);
        imageBrowseViewPager.setAdapter (imageBrowseAdapter);
        imageBrowseViewPager.setCurrentItem (currentIndex);
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
