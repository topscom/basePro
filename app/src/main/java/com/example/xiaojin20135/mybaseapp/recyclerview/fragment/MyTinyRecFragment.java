package com.example.xiaojin20135.mybaseapp.recyclerview.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.fragment.recycle.TinyRecycleFragment;
import com.example.xiaojin20135.mybaseapp.R;
import com.example.xiaojin20135.mybaseapp.bean.recyclerview.ListItemInfo;

import java.util.ArrayList;
import java.util.List;

import static com.example.xiaojin20135.basemodule.activity.BaseActivity.showToast;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTinyRecFragment extends TinyRecycleFragment<ListItemInfo> {
    public static MyTinyRecFragment tinyRecycleFragment;

    public MyTinyRecFragment () {
        // Required empty public constructor
    }

    public static MyTinyRecFragment getInstance(BaseActivity baseActivity){
        tinyRecycleFragment = new MyTinyRecFragment ();
        tinyRecycleFragment.setBaseActivity (baseActivity);
        return tinyRecycleFragment;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView (inflater,container,savedInstanceState);
        View view = inflater.inflate (R.layout.fragment_my_tiny_rec, container, false);
        super.initView (view);
        super.initEvents (view);
        addData();
        return view;
    }

    @Override
    protected void initItemLayout () {
        setLayoutResId(R.layout.baseitem);
        setListType(LINEAR_LAYOUT_MANAGER, true);

    }

    @Override
    protected void MyHolder (BaseViewHolder baseViewHolder, ListItemInfo listItemInfo) {
        baseViewHolder.setText (R.id.tv_title,listItemInfo.getTitle ());
        baseViewHolder.setText (R.id.tv_des,listItemInfo.getSubTitle ());

    }

    @Override
    protected void loadFirstData () {
        loadDataSuccess();
    }

    @Override
    protected void itemClick (int position) {
        showToast ( getActivity (),rvAdapter.getItem (position).getTitle ());
        setEmpty();
    }

    @Override
    protected void loadMoreData () {
        loadDataSuccess();
    }
    private void addData () {
        List<ListItemInfo> listItemInfos = new ArrayList<> ();
        String[] titles = new String[]{"title1","title2","title3","title4","title5","title1","title2","title3","title4","title5","title1","title2","title3","title4","title5"};
        String[] titlesDes = new String[]{"titlesDes1","titlesDes2","titlesDes3","titlesDes","titlesDes5","titlesDes1","titlesDes2","titlesDes3","titlesDes","titlesDes5","titlesDes1","titlesDes2","titlesDes3","titlesDes","titlesDes5"};
        for (int i = 0; i < 10; i++) {
            listItemInfos.add(new ListItemInfo(titles[i], titlesDes[i]));
        }
        showList (listItemInfos);
    }
}
