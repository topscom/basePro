package com.example.xiaojin20135.basemodule.fragment.recycle;



import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class TinyRecycleFragment<T> extends BaseFragment {

    protected RecyclerView base_rv_list;
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
    protected boolean isVertical = true;
    //gird布局与瀑布流布局默认单行数量
    protected int spanCount = 1;
    //子布局ID
    protected int layoutResId = -1;
    protected int lastVisibleItem;
    LinearLayoutManager linearLayoutManager = null;
    public int page = 1;
    public String sidx = "";
    protected String sord = "desc";
    protected int rows = 10;//每页显示的记录数

    protected BaseActivity baseActivity;



    public TinyRecycleFragment () {
        // Required empty public constructor
    }

    public void setBaseActivity (BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView (inflater,container,savedInstanceState);
        TextView textView = new TextView (getActivity ());
        textView.setText (R.string.hello_blank_fragment);
        return textView;
    }

    @Override
    protected void initView (View view) {
        initItemLayout ();
        base_rv_list = (RecyclerView)view.findViewById (R.id.base_rv_list);
        chooseListTye (listType,isVertical);
        if(layoutResId == -1){
            throw new RuntimeException ("layoutResId is null!");
        }
        rvAdapter = new RvAdapter (layoutResId,new ArrayList<T> ());
        base_rv_list.setAdapter (rvAdapter);
    }

    @Override
    protected void initEvents (View view) {
        //设置列表点击事件
        setItemCick ();
    }

    public void setEmpty(){
        rvAdapter.setNewData (null);
        View emptyView=baseActivity.getLayoutInflater().inflate(R.layout.empty_view, null);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rvAdapter.setEmptyView (emptyView);
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
    protected void setItemCick(){
        base_rv_list.addOnItemTouchListener (onItemTouchListener);
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-14 16:28
     * @Describe 设置布局类型
     */
    protected void setListType(int type,boolean isVertical){
        this.listType = type;
        this.isVertical = isVertical;
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
                linearLayoutManager = new LinearLayoutManager (getContext ());
                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                base_rv_list.setLayoutManager(linearLayoutManager);
                break;
            case GRID_LAYOUT_MANAGER:
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext (), spanCount);
                gridLayoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
                base_rv_list.setLayoutManager(gridLayoutManager);
                break;
            case STAGGERED_GRID_LAYOUT_MANAGER:
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager
                    (spanCount, isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL);
                base_rv_list.setLayoutManager(staggeredGridLayoutManager);
                break;
            default:
                linearLayoutManager = new LinearLayoutManager(getContext ());
                linearLayoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
                base_rv_list.setLayoutManager(linearLayoutManager);
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

    /**
     * @author lixiaojin
     * @createon 2018-07-16 15:15
     * @Describe 加载第一页数据
     */
    protected abstract void loadFirstData();

    /**
     * @author lixiaojin
     * @createon 2018-07-16 15:16
     * @Describe 加载数据成功
     */
    public void loadDataSuccess(){

    }

    /**
     * @author lixiaojin
     * @createon 2018-07-16 15:27
     * @Describe 列表点击事件
     */
    private RecyclerView.OnItemTouchListener onItemTouchListener = new OnItemClickListener () {
        @Override
        public void onSimpleItemClick (BaseQuickAdapter adapter, View view, int position) {
//            showToast (MyRecyActivity.this,rvAdapter.getItem (position).getTitle ());
            itemClick(position);
        }
    };
    /**
     * @author lixiaojin
     * @createon 2018-07-16 15:28
     * @Describe 列表点击事件
     */
    protected abstract void itemClick(int position);

    /**
     * @author lixiaojin
     * @createon 2018-07-16 15:39
     * @Describe 加载更多监听事件
     */
    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener () {
        @Override
        public void onScrolled (RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled (recyclerView, dx, dy);
            if(listType == LINEAR_LAYOUT_MANAGER){
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        }

        @Override
        public void onScrollStateChanged (RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged (recyclerView, newState);
            if(listType == LINEAR_LAYOUT_MANAGER){
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem +2 > linearLayoutManager.getItemCount()){
//                    swipeRefreshLayout.setRefreshing (true);
                    loadMoreData ();
                }
            }

        }
    };
    protected abstract void loadMoreData();

    /**
     * @author lixiaojin
     * @createon 2018-08-11 17:06
     * @Describe  数据展示
     */
    protected void showList(List<T> dataList){
        if(dataList != null && dataList.size () > 0){
            Log.d (TAG,"dataList.get(0) = " + dataList.get (0).toString ());
            rvAdapter.addData (dataList);
        }else{
            if(page == 1){
                setEmpty ();
            }else{
                baseActivity.showToast (baseActivity, R.string.no_more);
            }
        }
    }
    /**
     * @author lixiaojin
     * @createon 2018-08-18 15:02
     * @Describe 初始化查询参数
     */
    public void initParas(String sidx,String sord,int rows){
        initParas (sidx,sord);
        this.rows = rows;
    }

    public void initParas(String sidx,String sord){
        this.sidx = sidx;
        this.sord = sord;
    }
}
