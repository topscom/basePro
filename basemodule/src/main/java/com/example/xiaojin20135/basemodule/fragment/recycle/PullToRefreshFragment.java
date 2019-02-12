package com.example.xiaojin20135.basemodule.fragment.recycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.recycle.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.List;

public abstract class PullToRefreshFragment<T> extends BaseRecycleFragment<T> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isShowProgressDialog=false;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        swipeRefreshLayout .setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark);
        rvAdapter.setLoadMoreView(setLoadMoreView());
        rvAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMoreData();
            }
        });

    }
    public LoadMoreView  setLoadMoreView(){
        return new CustomLoadMoreView();
    }
    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:27
     * @Describe 设置加载更多
     */
    protected void setLoadMoreEnable(){
        //添加滚动监听
        // swipeMenuRecyclerView.addOnScrollListener (onScrollListener);
    }
    protected  void loadFirstData(){
        swipeRefreshLayout.setRefreshing(true);
        rvAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
    };

    /**
     * @author lixiaojin
     * @createon 2018-08-11 17:06
     * @Describe  数据展示
     */
    protected void showList(List<T> dataList){
        swipeRefreshLayout.setRefreshing(false);
        rvAdapter.setEnableLoadMore(true);
        if(dataList != null && dataList.size () > 0){
            if(page == 1){
                rvAdapter.setNewData (new ArrayList<T>());
            }
            rvAdapter.addData (dataList);

            if(dataList.size ()<rows){
                canLoadMore=false;
                rvAdapter.loadMoreEnd(page==1);
                // showToast (this,R.string.no_more);
            }else{
                rvAdapter.loadMoreComplete();
            }
        }else{
            if(page == 1){
                setEmpty ();
            }else{
                canLoadMore=false;
                // showToast (this,R.string.no_more);
                rvAdapter.loadMoreEnd(false);
            }
        }
    }


}
