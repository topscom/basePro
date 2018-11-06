package com.example.xiaojin20135.basemodule.menuitem.fragment;



import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.menuitem.adapter.MenuItemAdapter;
import com.example.xiaojin20135.basemodule.menuitem.help.MyItemDecoration;
import com.example.xiaojin20135.basemodule.menuitem.listener.IMemuItemClick;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuItemFragment extends Fragment implements IMemuItemClick{
    private RecyclerView menu_item_RV;
    private MenuItemAdapter menuItemAdapter;
    private List<Map<String,Integer>> datas;
    private BaseActivity baseActivity;
    public static MenuItemFragment menuItemFragment;
    public IMemuItemClick iMemuItemClick;
    public int spanCount = 4; //每列菜单个数

    public MenuItemFragment () {
        // Required empty public constructor
    }

    public static MenuItemFragment getInstance(BaseActivity baseActivity,List<Map<String,Integer>> datas,IMemuItemClick iMemuItemClick){
        menuItemFragment = new MenuItemFragment ();
        menuItemFragment.setBaseActivity (baseActivity);
        menuItemFragment.setiMemuItemClick (iMemuItemClick);
        menuItemFragment.setDatas (datas);
        return menuItemFragment;
    }

    /**
     * @author lixiaojin
     * @createon 2018-08-25 11:06
     * @Describe 刷新菜单信息，包括名称以及数字角标等
     */
    public void updataInfo(){
        menuItemAdapter.notifyDataSetChanged ();
    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_menu_item, container, false);
        initView (view);
        initMenu ();
//        datas =
        return view;
    }

    public void initView(View view){
        menu_item_RV = (RecyclerView)view.findViewById (R.id.menu_item_RV);
    }

    public void initMenu(){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager (spanCount,StaggeredGridLayoutManager.VERTICAL);
//        staggeredGridLayoutManager.setGapStrategy (StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        menu_item_RV.setLayoutManager (staggeredGridLayoutManager);
        menu_item_RV.addItemDecoration (new MyItemDecoration ());
        menuItemAdapter = new MenuItemAdapter (baseActivity,this,menu_item_RV,datas);
        menu_item_RV.setAdapter (menuItemAdapter);
    }

    public void setDatas (List<Map<String, Integer>> datas) {
        this.datas = datas;
    }

    public void setBaseActivity (BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public void setiMemuItemClick (IMemuItemClick iMemuItemClick) {
        this.iMemuItemClick = iMemuItemClick;
    }

    public void setSpanCount (int spanCount) {
        this.spanCount = spanCount;
    }

    @Override
    public void itemClick (int index) {
        iMemuItemClick.itemClick (index);
    }
}
