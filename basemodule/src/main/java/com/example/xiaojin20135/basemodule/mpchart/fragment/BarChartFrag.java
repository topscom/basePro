package com.example.xiaojin20135.basemodule.mpchart.fragment;



import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.mpchart.MyValueFormatter;
import com.example.xiaojin20135.basemodule.mpchart.bean.BarChartBaseBean;
import com.example.xiaojin20135.basemodule.mpchart.CommonAxisValueFormatter;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

/**
 * @author lixiaojin
 * @createon 2018-07-18 9:15
 * @Describe 柱状图基础视图
 */
public class BarChartFrag extends Fragment {
    private BarChart chart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;
    private ArrayList<BarChartBaseBean> barChartBaseBeans = new ArrayList<> ();

    //图表上展示的文字大小
    private float valueFontSize = 12f;
    //横坐标文字颜色
    private int xAxixFontColor = Color.BLACK;
    //横、纵坐标文字大小
    private float xAxixFontSize = 10f;
    //柱状图颜色
    private int chartColr = Color.BLUE;
    //是否显示图例
    private boolean showLegend = true;
    //图片文字说明
    private String legendText = "";
    //网格线颜色
    private int gridColor = Color.LTGRAY;
    //轴线的轴的颜色
    private int aXisColor = Color.LTGRAY;
    //label
    private String label = "";


    public BarChartFrag () {
        // Required empty public constructor
    }


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_bar_chart, container, false);
        initView(view);
        initData ();
        return view;
    }

    private void initView(View view){
        chart = (BarChart) view.findViewById(R.id.chart);
        leftAxis = chart.getAxisLeft();
        rightAxis = chart.getAxisRight();
        xAxis = chart.getXAxis();
    }

    public void initData(){
        //背景颜色
        chart.setBackgroundColor (Color.WHITE);
        //网格
        chart.setDrawGridBackground (false);
        //显示边界
        chart.setDrawBorders (false);
        //设置动画效果
        chart.animateY (1000, Easing.EasingOption.Linear);
        chart.animateX (1000, Easing.EasingOption.Linear);
        //设置描述信息
        Description description = new Description ();
        description.setText ("");
        chart.setDescription (description);

        //设置是否启动触摸响应
        chart.setTouchEnabled (false);
        //设置是否可以拖拽
        chart.setDragEnabled (false);
        //设置是否可以缩放
        chart.setScaleEnabled (false);

        //折线图例、标签 设置
        Legend legend = chart.getLegend ();
        if(showLegend){
            legend.setForm (Legend.LegendForm.CIRCLE);
            legend.setTextSize (12f);
            //显示位置
            legend.setVerticalAlignment (Legend.LegendVerticalAlignment.BOTTOM);
            legend.setHorizontalAlignment (Legend.LegendHorizontalAlignment.RIGHT);
            legend.setOrientation (Legend.LegendOrientation.HORIZONTAL);
            legend.setDrawInside (false);
        }else{
            legend.setEnabled (false);
        }

        //X、Y轴位置
        //X轴显示在位置底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(xAxixFontSize);
        xAxis.setTextColor(xAxixFontColor);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setGridColor (gridColor);
        xAxis.setAxisLineColor (aXisColor);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextSize (xAxixFontSize);
        leftAxis.setDrawGridLines (true);
        leftAxis.setGridColor (gridColor);
        leftAxis.setAxisLineColor (aXisColor);
        //隐藏右刻度
        rightAxis.setDrawAxisLine (false);
        rightAxis.setDrawGridLines (false);
//        rightAxis.setAxisMinimum (0f);
        rightAxis.setDrawLabels (false);
        rightAxis.setGridColor (gridColor);
        rightAxis.setAxisLineColor (aXisColor);

        //名称 横向
        ArrayList<String> names = new ArrayList<> ();

        //大小、高低 纵向
        ArrayList<BarEntry> sizes = new ArrayList<> ();

        //颜色
        ArrayList<Integer> colors = new ArrayList<> ();
        //填充数据
        for(int i=0;i<barChartBaseBeans.size ();i++){
            BarChartBaseBean barChartBaseBean = barChartBaseBeans.get (i);
            names.add (barChartBaseBean.getName ());
            if(barChartBaseBean.getColor () != 0){
                colors.add (barChartBaseBean.getColor ());
            }
            sizes.add (new BarEntry ((int)(i+1),(int)barChartBaseBean.getCount ()));
        }
        //横向刻度名称
        xAxis.setValueFormatter(new CommonAxisValueFormatter (names));
        //柱状图数据
        BarDataSet barDataSet = new BarDataSet (sizes,label);
        //顶部文字颜色
        barDataSet.setValueTextSize (valueFontSize);
        //自定义顶部数字显示格式，主要是为了去掉小数点
        barDataSet.setValueFormatter (new MyValueFormatter ());
        //颜色
        if(colors.size () > 0){
            barDataSet.setColors (colors);
        }else{
            barDataSet.setColor (chartColr);
        }

        BarData barData = new BarData (barDataSet);
        chart.setData(barData);
        chart.setFitBars(true); //使x轴完全适合所有条形
        chart.invalidate(); // refresh
    }

    public void setBarChartBaseBeans (ArrayList<BarChartBaseBean> barChartBaseBeans) {
        this.barChartBaseBeans = barChartBaseBeans;
    }

    public float getValueFontSize () {
        return valueFontSize;
    }

    public void setValueFontSize (float valueFontSize) {
        this.valueFontSize = valueFontSize;
    }

    public int getxAxixFontColor () {
        return xAxixFontColor;
    }

    public void setxAxixFontColor (int xAxixFontColor) {
        this.xAxixFontColor = xAxixFontColor;
    }

    public float getxAxixFontSize () {
        return xAxixFontSize;
    }

    public void setxAxixFontSize (float xAxixFontSize) {
        this.xAxixFontSize = xAxixFontSize;
    }

    public int getChartColr () {
        return chartColr;
    }

    public void setChartColr (int chartColr) {
        this.chartColr = chartColr;
    }

    public boolean isShowLegend () {
        return showLegend;
    }

    public void setShowLegend (boolean showLegend) {
        this.showLegend = showLegend;
    }

    public String getLegendText () {
        return legendText;
    }

    public void setLegendText (String legendText) {
        this.legendText = legendText;
    }

    public BarChart getChart () {
        return chart;
    }

    public void setChart (BarChart chart) {
        this.chart = chart;
    }

    public YAxis getLeftAxis () {
        return leftAxis;
    }

    public void setLeftAxis (YAxis leftAxis) {
        this.leftAxis = leftAxis;
    }

    public YAxis getRightAxis () {
        return rightAxis;
    }

    public void setRightAxis (YAxis rightAxis) {
        this.rightAxis = rightAxis;
    }

    public XAxis getxAxis () {
        return xAxis;
    }

    public void setxAxis (XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public int getGridColor () {
        return gridColor;
    }

    public void setGridColor (int gridColor) {
        this.gridColor = gridColor;
    }

    public int getaXisColor () {
        return aXisColor;
    }

    public void setaXisColor (int aXisColor) {
        this.aXisColor = aXisColor;
    }

    public String getLabel () {
        return label;
    }

    public void setLabel (String label) {
        this.label = label;
    }
}
