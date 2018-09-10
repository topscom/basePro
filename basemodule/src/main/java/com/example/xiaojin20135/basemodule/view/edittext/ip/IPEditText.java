package com.example.xiaojin20135.basemodule.view.edittext.ip;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;

import com.example.xiaojin20135.basemodule.view.edittext.AbsEditText;

/**
 * Created by lixiaojin on 2018-09-07 16:15.
 * 功能描述：
 */

public class IPEditText extends AbsEditText {

    public IPEditText(Context context) {
        this(context,null,0);
    }

    public IPEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IPEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getMaxLength() {
        return 3;
    }

    @Override
    public char[] getInputFilterAcceptedChars() {
        return new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }

    @Override
    public boolean checkInputValue() {
        String textValue = getText().toString().trim();
        if(textValue.isEmpty()){
            return false;
        }else{
            int number = Integer.valueOf(textValue);
            return number>=0&&number<=255;
        }
    }

    @Override
    public int getEditTextInputType() {
        return InputType.TYPE_CLASS_NUMBER;
    }
}
