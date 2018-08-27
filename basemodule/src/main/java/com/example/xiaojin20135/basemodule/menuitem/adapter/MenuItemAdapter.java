package com.example.xiaojin20135.basemodule.menuitem.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.BaseActivity;
import com.example.xiaojin20135.basemodule.menuitem.fragment.MenuItemFragment;

import java.util.List;
import java.util.Map;

/**
 * Created by lixiaojin on 2018-08-24 17:32.
 * 功能描述：
 * 菜单适配器
 */

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.MyViewHolder> implements View.OnClickListener{
    private static final String TAG = "MenuItemAdapter";
    public static final String IMAGE = "image";
    public static final String TITLE = "title";
    public static final String COUNT = "count";
    public static final String CODE = "code";
    BaseActivity baseActivity;
    private MenuItemFragment menuItemFragment;
    private RecyclerView recyclerView;
    private List<Map<String,Integer>> datas;

    public MenuItemAdapter (BaseActivity baseActivity, MenuItemFragment menuItemFragment,RecyclerView recyclerView, List<Map<String, Integer>> datas) {
        this.baseActivity = baseActivity;
        this.menuItemFragment = menuItemFragment;
        this.recyclerView = recyclerView;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from (baseActivity).inflate (R.layout.item_menu,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder (contentView);
        contentView.setOnClickListener (this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder (MyViewHolder holder, int position) {
        Map<String,Integer> map = datas.get (position);
        Log.d (TAG,"map = " + map.toString ());
        holder.item_image_IV.setImageResource (map.get (IMAGE));
        holder.item_title_TV.setText (baseActivity.getString (map.get (TITLE)));
        int count = map.get (COUNT);
        if(count > 0){
            holder.item_count_TV.setText (count+"");
            holder.item_count_TV.setVisibility (View.VISIBLE);
        }else{
            holder.item_count_TV.setVisibility (View.GONE);
        }
    }

    @Override
    public int getItemCount () {
        return datas==null?0:datas.size();
    }

    @Override
    public void onClick (View v) {
        menuItemFragment.itemClick (recyclerView.getChildAdapterPosition (v));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView item_image_IV;
        private TextView item_title_TV;
        private TextView item_count_TV;
        public MyViewHolder (View itemView) {
            super (itemView);
            item_image_IV = (ImageView)itemView.findViewById (R.id.item_image_IV);
            item_title_TV = (TextView)itemView.findViewById (R.id.item_title_TV);
            item_count_TV = (TextView)itemView.findViewById (R.id.item_count_TV);
        }
    }
}
