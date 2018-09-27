package com.example.xiaojin20135.basemodule.wheelview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xiaojin20135.basemodule.R;
import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CityPickerActivity extends ToolBarActivity {
    private WheelView wheel_province;
    private WheelView wheel_city;
    private WheelView wheel_area;
    private Button btn_city;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_city_picker;
    }

    @Override
    protected void initView () {
        wheel_province = findViewById(R.id.wheel_province);
        wheel_city = findViewById(R.id.wheel_city);
        wheel_area = findViewById(R.id.wheel_area);

        //定义WheelView的style，比如选中文字大小和其他文字大小（这里WheelView已经封装了）
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 20;
        style.textSize = 16;

        //在这里设置一个WheelView的Adapter作为数据源的适配器
        wheel_province.setWheelAdapter(new ArrayWheelAdapter (this));
        //为WheelView设置一个皮肤风格（这里在WheelView中已经封装了一个Holo）
        wheel_province.setSkin(WheelView.Skin.Holo);
        //这里将数据放入WheelView中
        wheel_province.setWheelData(createProvinceDatas());
        //设置WheelView的Style（上面已经定义）
        wheel_province.setStyle(style);

        wheel_city.setWheelAdapter(new ArrayWheelAdapter(this));
        wheel_city.setSkin(WheelView.Skin.Holo);
        //这里就很奇妙了，我详细说一下
        //看下面的几个创建数据的函数，从province到city再到area，其中的返回类型中分别为List<String>,HashMap<String, List<String>>, HashMap<String, List<String>>
        //其中第一种为String列表，也就是第一个省份的列表可以直接通过String列表得到。
        //HashMap是哈希表，他里面的值都是通过key-value进行对应，所以在这个情况中就是一个省（String key）对应着一个市（String value）的列表（同理得到第二个市与区的关系）
        //HashMap.get(key)方法是用来通过key的值来得到value的值
        //WheelView.getSelection()通过看就知道是一个获取位置的方法（大神在WheelView中封装好了）.
        //综上所述，其实这条东西，逆向来读就是，通过得到省的WheelView的位置来得到省的value值，而省的value值就是市的key值，所以说可以得到市的一整个value值。
        wheel_city.setWheelData(createCityDatas().get(createProvinceDatas().get(wheel_province.getSelection())));
        wheel_city.setStyle(style);

        //这里是把省的WheelView与市的WheelView连接起来（封装好的）（加入下一级的WheelView）
        wheel_province.join(wheel_city);
        //这里是把省的WheelView与市的WheelView的数据连接起来
        wheel_province.joinDatas(createCityDatas());

        wheel_area.setWheelAdapter(new ArrayWheelAdapter(this));
        wheel_area.setSkin(WheelView.Skin.Holo);
        //这个嘛，上面解释过了，但是又臭又长，简单说一下
        //其实就匹配了两次，通过得到省和市的位置来定位到他们两个的value，再通过value得到区的value值
        wheel_area.setWheelData(createAreaDatas().get(createCityDatas().get(createProvinceDatas().get(wheel_province.getSelection())).get(wheel_city.getSelection())));
        wheel_area.setStyle(style);

        wheel_city.join(wheel_area);
        wheel_city.joinDatas(createAreaDatas());

        btn_city = findViewById(R.id.city_btn);
    }

    @Override
    protected void initEvents () {
        btn_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String province = wheel_province.getSelectionItem().toString();
                String city = wheel_city.getSelectionItem().toString();
                String area = wheel_area.getSelectionItem().toString();
                Toast.makeText(CityPickerActivity.this, province + city + area, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void loadData () {

    }

    //这里是第一级，所以直接把他放入一个List中就可以了
    private List<String> createProvinceDatas() {
        String[] strings = {"黑龙江", "吉林", "辽宁"};
        //将字符数组转换为List形式
        return Arrays.asList(strings);
    }

    private HashMap<String, List<String>> createCityDatas() {
        //新建一个哈希表
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        String[] strings = {"黑龙江", "吉林", "辽宁"};
        String[] s1 = {"哈尔滨", "齐齐哈尔", "大庆"};
        String[] s2 = {"长春", "吉林"};
        String[] s3 = {"沈阳", "大连", "鞍山", "抚顺"};
        String[][] ss = {s1, s2, s3};
        for (int i = 0; i < strings.length; i++) {
            //在这里把key与value分别列出，然后通过HashMap.put进行配对然后写入哈希表。
            map.put(strings[i], Arrays.asList(ss[i]));
        }
        // 一个哈希表的输出检测（自学哈希表时测试一下用的，自己也可以试试）
//        Iterator iter = map.entrySet().iterator();
//        while(iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            Object key = entry.getKey();
//            Object value = entry.getValue();
//            Log.v("second",key + ":" + value);
//        }
        return map;
    }


    private HashMap<String, List<String>> createAreaDatas() {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        String[] strings = {"哈尔滨", "齐齐哈尔", "大庆", "长春", "吉林", "沈阳", "大连", "鞍山", "抚顺"};
        String[] s1 = {"道里区", "道外区", "南岗区", "香坊区"};
        String[] s2 = {"龙沙区", "建华区", "铁锋区"};
        String[] s3 = {"红岗区", "大同区"};
        String[] s11 = {"南关区", "朝阳区"};
        String[] s12 = {"龙潭区"};
        String[] s21 = {"和平区", "皇姑区", "大东区", "铁西区"};
        String[] s22 = {"中山区", "金州区"};
        String[] s23 = {"铁东区", "铁西区"};
        String[] s24 = {"新抚区", "望花区", "顺城区"};
        String[][] ss = {s1, s2, s3, s11, s12, s21, s22, s23, s24};
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], Arrays.asList(ss[i]));
        }
//        Iterator iter = map.entrySet().iterator();
//        while(iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            Object key = entry.getKey();
//            Object value = entry.getValue();
//            Log.v("first",key + ":" + value);
//        }
        return map;
    }
}
