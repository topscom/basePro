package com.example.xiaojin20135.basemodule.guidepage;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lixiaojin on 2018-09-28 9:30.
 * 功能描述：
 */

public class GuidePageAdapter extends PagerAdapter {
    private List<View> viewList;

    public GuidePageAdapter(List<View> viewList){
        this.viewList = viewList;
    }

    /**
     * @author lixiaojin
     * @createon 2018-09-28 9:33
     * @Describe 返回界面的个数
     */
    @Override
    public int getCount () {
        if(viewList != null){
            return viewList.size ();
        }
        return 0;
    }

    /**
     * @author lixiaojin
     * @createon 2018-09-28 9:31
     * @Describe 判断对象是都生成界面
     */
    @Override
    public boolean isViewFromObject (@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * @author lixiaojin
     * @createon 2018-09-28 9:32
     * @Describe 初始化position位置的界面
     */
    @NonNull
    @Override
    public Object instantiateItem (@NonNull ViewGroup container, int position) {
        container.addView (viewList.get (position));
        return viewList.get (position);
    }

    @Override
    public void destroyItem (@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView (viewList.get (position));
    }
}
