package com.example.xiaojin20135.basemodule.mpchart.fragment;



import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.mpchart.bean.BarChartBaseBean;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

/**
 * @author lixiaojin
 * @createon 2018-07-18 11:21
 * @Describe 饼状图基础视图
 */
public class PieChartFrag extends Fragment {
    private static PieChart mPieChart;   //饼状图
    private ArrayList<BarChartBaseBean> barChartBaseBeans = new ArrayList<> ();
    private boolean showLegend = false;

    public PieChartFrag pieChartFrag = null;

    public PieChartFrag () {
    }

    /**
     * @author lixiaojin
     * @createon 2018-07-20 14:05
     * @Describe 获取Fragment实例
     */
    public PieChartFrag getInstance(){
        if(pieChartFrag == null){
            pieChartFrag = new PieChartFrag ();
        }
        return pieChartFrag;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_pie_chart, container, false);
        initView (view);
        return view;
    }
    private void initView(View view){
        mPieChart= (PieChart)view.findViewById (R.id.pie_chart);
        initChart();
        initData();
    }
    private void initChart(){
        // 设置 pieChart 图表基本属性
        mPieChart.setUsePercentValues(false);            //使用百分比显示
        mPieChart.getDescription().setEnabled(false);    //设置pieChart图表的描述
//        mPieChart.setBackgroundColor(Color.YELLOW);      //设置pieChart图表背景色
        mPieChart.setExtraOffsets(20, 0, 20, 0);        //设置pieChart图表上下左右的偏移，类似于外边距
        mPieChart.setDragDecelerationFrictionCoef(0.95f);//设置pieChart图表转动阻力摩擦系数[0,1]
//        mPieChart.setRotationAngle(90);                   //设置pieChart图表起始角度
        mPieChart.setRotationEnabled(true);              //设置pieChart图表是否可以手动旋转
        mPieChart.setHighlightPerTapEnabled(true);       //设置piecahrt图表点击Item高亮是否可用
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);// 设置pieChart图表展示动画效果
        // 设置 pieChart 图表Item文本属性
        mPieChart.setDrawEntryLabels(false);              //设置pieChart是否只显示饼图上百分比不显示文字（true：下面属性才有效果）
        mPieChart.setEntryLabelColor(Color.WHITE);       //设置pieChart图表文本字体颜色
//        mPieChart.setEntryLabelTypeface(mTfRegular);     //设置pieChart图表文本字体样式
        mPieChart.setEntryLabelTextSize(5f);            //设置pieChart图表文本字体大小
        // 设置 pieChart 内部圆环属性
        mPieChart.setDrawHoleEnabled(true);              //是否显示PieChart内部圆环(true:下面属性才有意义)
        mPieChart.setHoleRadius(35f);                    //设置PieChart内部圆的半径(这里设置28.0f)
        mPieChart.setTransparentCircleRadius(38f);       //设置PieChart内部透明圆的半径(这里设置31.0f)
        mPieChart.setTransparentCircleColor(Color.BLACK);//设置PieChart内部透明圆与内部圆间距(31f-28f)填充颜色
        mPieChart.setTransparentCircleAlpha(50);         //设置PieChart内部透明圆与内部圆间距(31f-28f)透明度[0~255]数值越小越透明
        mPieChart.setHoleColor(Color.WHITE);             //设置PieChart内部圆的颜色
        mPieChart.setDrawCenterText(false);               //是否绘制PieChart内部中心文本（true：下面属性才有意义）
//        mPieChart.setCenterTextTypeface(mTfLight);       //设置PieChart内部圆文字的字体样式
        mPieChart.setCenterText("Test");                 //设置PieChart内部圆文字的内容
        mPieChart.setCenterTextSize(10f);                //设置PieChart内部圆文字的大小
        mPieChart.setCenterTextColor(Color.RED);         //设置PieChart内部圆文字的颜色
        setLegend();

    }

    private void setLegend(){
        // 获取pieCahrt图列
        Legend l = mPieChart.getLegend();
        if(showLegend){
            l.setEnabled(true);                    //是否启用图列（true：下面属性才有意义）
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setForm(Legend.LegendForm.CIRCLE); //设置图例的形状
            l.setFormSize(10f);                    //设置图例的大小
            l.setFormToTextSpace(5f);            //设置每个图例实体中标签和形状之间的间距
            l.setDrawInside(false);
            l.setWordWrapEnabled(true);           //设置图列换行(注意使用影响性能,仅适用legend位于图表下面)
            l.setXEntrySpace(0f);                //设置图例实体之间延X轴的间距（setOrientation = HORIZONTAL有效）
            l.setYEntrySpace(0f);                 //设置图例实体之间延Y轴的间距（setOrientation = VERTICAL 有效）
            l.setYOffset(0f);                     //设置比例块Y轴偏移量
            l.setTextSize(15f);                   //设置图例标签文本的大小
            l.setTextColor(Color.parseColor("#000000"));//设置图例标签文本的颜色
        }else{
            l.setEnabled(false);
        }
    }

    public void initData(){
        ArrayList<Integer> colors = new ArrayList<Integer>();
        ArrayList<PieEntry> pieEntries = new ArrayList<PieEntry> ();
        for(int i=0;i<barChartBaseBeans.size ();i++){
            BarChartBaseBean barChartBaseBean = barChartBaseBeans.get (i);
            if(barChartBaseBean.getColor () != 0){
                colors.add (barChartBaseBean.getColor ());
            }
            pieEntries.add (new PieEntry (barChartBaseBean.getCount (),barChartBaseBean.getName ()));
        }

        //饼状图数据集
        PieDataSet pieDataSet = new PieDataSet(pieEntries,"");
        pieDataSet.setSliceSpace (2f); //设置饼状item之间的间隙
        pieDataSet.setSelectionShift (10f);//设置饼状item被选中时变化的的距离
        pieDataSet.setColors(colors); //为DataSet中的数据匹配上颜色集（饼图item的颜色）
        //饼图外画线指示
        pieDataSet.setValueLinePart1OffsetPercentage (41.0f);
        pieDataSet.setValueLinePart1Length (0.1f);
        pieDataSet.setValueLinePart2Length (0.5f);
        pieDataSet.setYValuePosition (PieDataSet.ValuePosition.OUTSIDE_SLICE);
        ///最终数据
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(true); //设置是否显示数据实体（百分比，true：以下属性才有意义）
        pieData.setValueTextColor (Color.BLACK);//设置所有DataSet内数据实体的文字颜色
        pieData.setValueTextSize (17f);//设置所有DataSet内数据实体的文字字体大小
        pieData.setValueFormatter(new PercentFormatter ()); //设置所有DataSet内数据实体的文字字体格式
        mPieChart.setData(pieData);
        mPieChart.highlightValues (null);
        mPieChart.invalidate ();
    }

    public ArrayList<BarChartBaseBean> getBarChartBaseBeans () {
        return barChartBaseBeans;
    }

    public void setBarChartBaseBeans (ArrayList<BarChartBaseBean> barChartBaseBeans) {
        this.barChartBaseBeans = barChartBaseBeans;
    }

    public static PieChart getmPieChart () {
        return mPieChart;
    }

    public static void setmPieChart (PieChart mPieChart) {
        PieChartFrag.mPieChart = mPieChart;
    }

    public boolean isShowLegend () {
        return showLegend;
    }

    public void setShowLegend (boolean showLegend) {
        this.showLegend = showLegend;
    }
}
