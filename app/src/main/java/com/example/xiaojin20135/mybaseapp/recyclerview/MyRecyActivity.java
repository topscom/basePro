package com.example.xiaojin20135.mybaseapp.recyclerview;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.xiaojin20135.basemodule.activity.recycle.BaseRecyclerActivity;
import com.example.xiaojin20135.mybaseapp.MainActivity;
import com.example.xiaojin20135.mybaseapp.R;
import com.example.xiaojin20135.mybaseapp.bean.recyclerview.ListItemInfo;

import java.util.ArrayList;
import java.util.List;

public class MyRecyActivity extends BaseRecyclerActivity<ListItemInfo> {
    /**
     * @author lixiaojin
     * @createon 2018-07-15 10:09
     * @Describe
     *
     *  列表展示基础类
     */
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        addData();
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_my_recy;
    }

    @Override
    protected void initEvents () {
        super.initEvents ();
    }

    @Override
    protected void loadData () {
        Intent intent = getIntent ();
        Log.d (TAG,intent.getStringExtra ("hh"));
    }

    @Override
    protected void initItemLayout () {
        setLayoutResId(R.layout.baseitem);
        setListType(LINEAR_LAYOUT_MANAGER, true, true,true);
    }

    @Override
    protected void MyHolder (BaseViewHolder baseViewHolder, ListItemInfo listItemInfo) {
        baseViewHolder.setText (R.id.tv_title,listItemInfo.getTitle ());
        baseViewHolder.setText (R.id.tv_des,listItemInfo.getSubTitle ());
    }

    @Override
    protected void loadFirstData () {
        Log.d (TAG,"加载第一页数据");
        loadDataSuccess();
    }

    @Override
    protected void itemClick (int position) {
       showToast ( MyRecyActivity.this,rvAdapter.getItem (position).getTitle ());
        setEmpty();
    }

    @Override
    protected void loadMoreData () {
        Log.d (TAG,"加载下一页数据");
        loadDataSuccess();
    }

    private void addData () {
        List<ListItemInfo> listItemInfos = new ArrayList<> ();
        String[] titles = new String[]{"title1","title2","title3","title4","title5","title1","title2","title3","title4","title5","title1","title2","title3","title4","title5"};
        String[] titlesDes = new String[]{"titlesDes1","titlesDes2","titlesDes3","titlesDes","titlesDes5","titlesDes1","titlesDes2","titlesDes3","titlesDes","titlesDes5","titlesDes1","titlesDes2","titlesDes3","titlesDes","titlesDes5"};
        for (int i = 0; i < titles.length; i++) {
            listItemInfos.add(new ListItemInfo(titles[i], titlesDes[i]));
        }
        showList (listItemInfos);
    }
}
