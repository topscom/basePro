package com.example.xiaojin20135.basemodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.xiaojin20135.basemodule.R;

import java.util.List;
import java.util.Map;

/**
 * Created by lixiaojin on 2018-07-17 18:02.
 * 功能描述：
 * Spinner 基础适配器类
 */

public class BaseSpinnerAdapter extends BaseAdapter{
    List<Map> datas ;
    Context mContext;

    public BaseSpinnerAdapter (Context context, List<Map> objects) {
        this.datas=objects;
        this.mContext=context;
    }

    @Override
    public int getCount () {
        return datas.size();
    }

    @Override
    public Object getItem (int position) {
        return datas.get (position);
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(R.layout.base_spinner,null);
        if(convertView != null){
            TextView spinner_gzyy_TV = (TextView)convertView.findViewById(R.id.name);
            spinner_gzyy_TV.setText(datas.get(position).get("name").toString());
        }
        return convertView;
    }
}
