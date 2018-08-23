package com.example.xiaojin20135.basemodule.mpchart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * Created by lixiaojin on 2018-07-18 15:55.
 * 功能描述：
 */

public class MyValueFormatter implements IValueFormatter {
    @Override
    public String getFormattedValue (float value, Entry entry, int dataSetIndex, ViewPortHandler
        viewPortHandler) {
        return (int)value+"";
    }
}
