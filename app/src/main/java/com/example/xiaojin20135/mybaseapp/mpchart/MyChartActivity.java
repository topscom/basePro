package com.example.xiaojin20135.mybaseapp.mpchart;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.mpchart.bean.BarChartBaseBean;
import com.example.xiaojin20135.basemodule.mpchart.fragment.BarChartFrag;
import com.example.xiaojin20135.basemodule.mpchart.fragment.PieChartFrag;
import com.example.xiaojin20135.mybaseapp.R;

import java.util.ArrayList;
import java.util.Random;

public class MyChartActivity extends ToolBarActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_my_chart;
    }

    @Override
    protected void initView () {

    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {

    }

    public void onClick (View view) {
        switch (view.getId ()){
            case R.id.barchart_btn:
                BarChartFrag barChartFrag = new BarChartFrag ();
                ArrayList<BarChartBaseBean> barChartBaseBeans = new ArrayList<> ();
                barChartBaseBeans.add (new BarChartBaseBean ("火警数",1, Color.RED));
                barChartBaseBeans.add (new BarChartBaseBean ("报警数",3,Color.GREEN));
                barChartBaseBeans.add (new BarChartBaseBean ("故障数",3,Color.LTGRAY));
                barChartBaseBeans.add (new BarChartBaseBean ("启动数",10,Color.YELLOW));
                barChartBaseBeans.add (new BarChartBaseBean ("反馈数",1,Color.GRAY));
                barChartBaseBeans.add (new BarChartBaseBean ("屏蔽数",3,Color.LTGRAY));
                barChartFrag.setBarChartBaseBeans (barChartBaseBeans);
                getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container,barChartFrag).commit ();
                break;
            case R.id.piechart_btn:
                PieChartFrag pieChartFrag = new PieChartFrag();
                ArrayList<BarChartBaseBean> barChartBaseBeans1 = new ArrayList<> ();
                barChartBaseBeans1.add (new BarChartBaseBean ("火警数",123 , Color.RED));
                barChartBaseBeans1.add (new BarChartBaseBean ("报警数",200 ,Color.GREEN));
                barChartBaseBeans1.add (new BarChartBaseBean ("故障数",345 ,Color.LTGRAY));
                barChartBaseBeans1.add (new BarChartBaseBean ("启动数",700 ,Color.YELLOW));
                barChartBaseBeans1.add (new BarChartBaseBean ("反馈数",400 ,Color.GRAY));
                barChartBaseBeans1.add (new BarChartBaseBean ("屏蔽数",500 ,Color.LTGRAY));
                pieChartFrag.setBarChartBaseBeans (barChartBaseBeans1);
                getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container,pieChartFrag).commit ();
                break;
        }
    }
}
