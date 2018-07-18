package com.example.xiaojin20135.basemodule.mpchart;

import android.support.design.widget.TabLayout;
import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

/**
 * Created by lixiaojin on 2018-07-18 9:44.
 * 功能描述：
 */

public class CommonAxisValueFormatter implements IAxisValueFormatter{
    protected ArrayList<String> titles = new ArrayList<> ();

    public CommonAxisValueFormatter (ArrayList<String> titles) {
        this.titles = titles;
    }

    @Override
    public String getFormattedValue (float value, AxisBase axis) {
        int index = (int)value - 1;
        if(titles.size () > index && index >=0){
            return titles.get (index);
        }else{
            return "";
        }
    }
}
