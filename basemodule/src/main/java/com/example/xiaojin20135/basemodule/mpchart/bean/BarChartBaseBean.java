package com.example.xiaojin20135.basemodule.mpchart.bean;

/**
 * Created by lixiaojin on 2018-07-18 9:08.
 * 功能描述：
 * 柱状图数据基础类
 *
 */

public class BarChartBaseBean {
    private String name;
    private int count;
    private int color = 0;

    public BarChartBaseBean (String name, int count) {
        this.name = name;
        this.count = count;
    }

    public BarChartBaseBean (String name, int count, int color) {
        this.name = name;
        this.count = count;
        this.color = color;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public float getCount () {
        return count;
    }

    public void setCount (int count) {
        this.count = count;
    }

    public int getColor () {
        return color;
    }

    public void setColor (int color) {
        this.color = color;
    }
}
