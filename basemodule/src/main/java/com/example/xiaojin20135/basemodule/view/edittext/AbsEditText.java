package com.example.xiaojin20135.basemodule.view.edittext;

import android.content.Context;
import android.text.InputFilter;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by lixiaojin on 2018-09-07 16:14.
 * 功能描述：
 */

public abstract class AbsEditText extends EditText {
    public AbsEditText(Context context) {
        this(context,null,0);
    }

    public AbsEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AbsEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMaxLength();
        addInputFilter();
    }

    /**
     * 初始化配置
     */
    protected void setMaxLength(){
        //setOnKeyListener((OnKeyListener) DigitsKeyListener.getInstance(""));
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(getMaxLength())});
    }

    protected void addInputFilter(){
        setKeyListener(new NumberKeyListener () {
            @Override
            protected char[] getAcceptedChars() {
                return getInputFilterAcceptedChars();
            }

            @Override
            public int getInputType() {
                return getEditTextInputType();
            }
        });
    }

    /**
     * 输入内容最大长度
     * @return
     */
    public abstract int getMaxLength();

    /**
     * 输入内容过滤
     * @return
     */
    public abstract char[] getInputFilterAcceptedChars();

    /**
     * 输入内容检查是否ok
     * @return
     */
    public abstract boolean checkInputValue();

    public abstract int getEditTextInputType();


}

