package com.example.xiaojin20135.basemodule.activity.recycle;

import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiaojin
 * @createon 2018-07-14 16:12
 * @Describe 列表展示基础类
 */
public abstract class BaseRecyclerActivity<T> extends ToolBarActivity {

    protected SwipeRefreshLayout swipeRefreshLayout;
    protected SwipeMenuRecyclerView swipeMenuRecyclerView;
    protected RvAdapter rvAdapter;
    //list布局
    protected static final int LINEAR_LAYOUT_MANAGER = 0;
    //grid 布局
    protected static final int GRID_LAYOUT_MANAGER = 1;
    //瀑布流布局
    protected static final int STAGGERED_GRID_LAYOUT_MANAGER = 2;
    //默认为0 单行布局
    private int listType = 0;
    //排列方式默认垂直
    private boolean isVertical = true;
    //gird布局与瀑布流布局默认单行数量
    private int spanCount = 1;
    //子布局ID
    private int layoutResId = -1;
    //是否可刷新，默认不可以
    private boolean canRefresh = true;
    //刷新监听
    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = null;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }


    @Override
    protected void initView () {
        initItemLayout ();
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById (R.id.base_swipe_refresh_lay);
        swipeMenuRecyclerView = (SwipeMenuRecyclerView)findViewById (R.id.base_rv_list);
        setRefreshEnable (canRefresh,onRefreshListener);
        chooseListTye (listType,isVertical);
        if(layoutResId == -1){
            throw new RuntimeException ("layoutResId is null!");
        }
        rvAdapter = new RvAdapter (layoutResId,new ArrayList<T> ());
        swipeMenuRecyclerView.setAdapter (rvAdapter);
    }


    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:35
     * @Describe 设置子布局id
     */
    public void setLayoutResId(@LayoutRes int layoutResId) {
        this.layoutResId = layoutResId;
    }
    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:23
     * @Describe 初始化子布局
     */
    protected abstract void initItemLayout();

    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:25
     * @Describe 设置下拉刷新
     * 下拉刷新功能是否可用，true：允许
     *                     false：禁止
     */
    protected void setRefreshEnable(boolean canRefresh, SwipeRefreshLayout.OnRefreshListener onRefreshListener){
        if(canRefresh){
            swipeRefreshLayout.setEnabled (true);
            swipeRefreshLayout.setOnRefreshListener (onRefreshListener);
        }else{
            swipeRefreshLayout.setEnabled (false);
        }
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:27
     * @Describe 设置加载更多
     */
    protected void setLoadMoreEnable(RecyclerView.OnScrollListener onScrollListener){
        //添加滚动监听
        swipeMenuRecyclerView.addOnScrollListener (onScrollListener);
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:28
     * @Describe 设置布局类型
     */
    protected void setListType(int type,boolean isVertical,boolean canRefresh,SwipeRefreshLayout.OnRefreshListener onRefreshListener){
        this.listType = type;
        this.isVertical = isVertical;
        this.canRefresh = canRefresh;
        this.onRefreshListener = onRefreshListener;
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:29
     * @Describe 设置grid样式和瀑布流横向或者纵向数量
     */
    protected void setSpanCount(int spanCount){
        if(spanCount > 0){
            this.spanCount = spanCount;
        }
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:31
     * @Describe 设置布局管理器
     */
    private void chooseListTye(int listType,boolean isVertical){
        switch (listType) {
            case LINEAR_LAYOUT_MANAGER:
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                swipeMenuRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case GRID_LAYOUT_MANAGER:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
                gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
                swipeMenuRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case STAGGERED_GRID_LAYOUT_MANAGER:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                    (spanCount, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
                swipeMenuRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            default:
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                layoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                swipeMenuRecyclerView.setLayoutManager(layoutManager);
                break;
        }
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:16
     * @Describe 适配器内部处理
     */
    protected abstract void MyHolder(BaseViewHolder baseViewHolder, T t);

    public class RvAdapter extends BaseQuickAdapter<T, BaseViewHolder> {

        public RvAdapter(int layoutResId, List<T> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, T t) {
            MyHolder(baseViewHolder, t);
        }
    }
}
