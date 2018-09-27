package com.example.xiaojin20135.basemodule.wheelview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.Button;

import com.example.xiaojin20135.basemodule.R;
import com.wx.wheelview.widget.WheelView;

import java.util.Calendar;

/**
 * Created by lixiaojin on 2018-09-19 9:14.
 * 功能描述：
 * 滚轮日期选择器
 */

public class DatePickWheelDialog extends Dialog{
    private Button btn_left, btn_right;
    private WheelView year, month, day;
    private String date;//初始化显示的日期，默认为当日

    public DatePickWheelDialog(Context context,String date){
        this(context, R.style.AlertDialogStyle, date);
    }

    public DatePickWheelDialog(Context context, int theme, String date){
        super(context, theme);
        this.date = date;
        init();
    }

    private void init() {
        this.setCanceledOnTouchOutside(true);
        this.setCancelable(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_wheel_date_picker);

        initViews();
//        initValues();
    }

    private void initViews()
    {
        /*btn_left = (Button) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(clickListener);
        btn_right = (Button) findViewById(R.id.btn_right);
        btn_right.setOnClickListener(clickListener);

        String icurYear, icurMonth, icurDay;
        Calendar c = Calendar.getInstance();
        int curYear = c.get(Calendar.YEAR);
        if (date == null || date.length() < 10)
        {// 格式不正确
            int curMonth = c.get(Calendar.MONTH);
            int curDay = c.get(Calendar.DAY_OF_MONTH);
            icurYear = String.valueOf(curYear);
            icurMonth = String.valueOf(curMonth + 1);
            icurDay = String.valueOf(curDay);
        }
        else
        {// 年月日
            icurYear = date.substring(0, 4);
            icurMonth = date.substring(5, 7);
            icurDay = date.substring(8, 10);
        }

        year = (WheelView) findViewById(R.id.year);
        year.setAdapter(new NumericWheelAdapter(curYear - 2, curYear));
        // year.setLabel("年");
        year.setCurrentItem(Integer.parseInt(icurYear) - Integer.parseInt(year.getAdapter().getItem(0)));

        month = (WheelView) findViewById(R.id.month);
        month.setAdapter(new NumericWheelAdapter(1, 12));// "%02d"
        // month.setLabel("月");
        month.setCyclic(true);
        month.setCurrentItem(Integer.parseInt(icurMonth) - 1);

        int daysOfMounth = getDaysOfMounth();
        day = (WheelView) findViewById(R.id.day);
        day.setAdapter(new NumericWheelAdapter(1, daysOfMounth));
        // day.setLabel("日");
        day.setCyclic(true);
        day.setCurrentItem(Integer.parseInt(icurDay) - 1);

        OnWheelScrollListener scrollListener = new OnWheelScrollListener()
        {
            public void onScrollingStarted(WheelView wheel)
            {
            }

            public void onScrollingFinished(WheelView wheel)
            {
                int daysOfMounth = getDaysOfMounth();
                day.setAdapter(new NumericWheelAdapter(1, daysOfMounth));
            }
        };

        year.addScrollingListener(scrollListener);
        month.addScrollingListener(scrollListener);
        // day.addScrollingListener(scrollListener);*/

    }

}
