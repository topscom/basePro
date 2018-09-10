package com.example.xiaojin20135.basemodule.view.edittext.port;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import com.example.xiaojin20135.basemodule.view.edittext.ip.IPEditText;

/**
 * Created by lixiaojin on 2018-09-07 16:16.
 * 功能描述：
 */

public class PortView extends IPEditText {
    public PortView(Context context) {
        this(context,null,0);
    }

    public PortView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PortView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFocusableInTouchMode(true);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(10, 4, 4, 4);
    }

    @Override
    public int getMaxLength() {
        return 5;
    }

    @Override
    public boolean checkInputValue() {
        String textvalue = getText().toString().trim();
        if(textvalue.isEmpty()){
            return false;
        }else{
            int number = Integer.valueOf(textvalue);
            return number>0&&number<65536;
        }
    }

    public boolean checkDelayTimeValue() {
        String textvalue = getText().toString().trim();
        if(textvalue.isEmpty()){
            return false;
        }else{
            int number = Integer.valueOf(textvalue);
            return number>=0&&number<65536;
        }
    }

    public short getPort(){
        int number = Integer.valueOf(getText().toString().trim());
        return (short)number;
    }
}

