package com.example.xiaojin20135.basemodule.wheelview.widget.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaojin20135.basemodule.R;

import java.util.ArrayList;

/**
 * Created by lixiaojin on 2018-09-29 8:53.
 * 功能描述：
 */

public class CalendarTextAdapter extends AbstractWheelTextAdapter {
    ArrayList<String> list;

    public CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
        super(context, R.layout.wheel_item, NO_RESOURCE, currentItem, maxsize, minsize);
        this.list = list;
        setItemTextResource(R.id.item_value_TV);
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        View view = super.getItem(index, cachedView, parent);
        return view;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public CharSequence getItemText(int index) {
        return list.get(index) + "";
    }
}
