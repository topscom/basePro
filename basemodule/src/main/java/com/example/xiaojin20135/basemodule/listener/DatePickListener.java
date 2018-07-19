package com.example.xiaojin20135.basemodule.listener;

import android.view.View;

/**
 * Created by lixiaojin on 2018-07-19 18:36.
 * 功能描述：
 */

public class DatePickListener implements View.OnClickListener {
    private String dateValue = "";
    private String timeValue = "00:00";


    @Override
    public void onClick (View v) {

    }

    public String getDateValue () {
        return dateValue;
    }

    public void setDateValue (String dateValue) {
        this.dateValue = dateValue;
    }

    public String getTimeValue () {
        return timeValue;
    }

    public void setTimeValue (String timeValue) {
        this.timeValue = timeValue;
    }
}
