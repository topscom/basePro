package com.example.xiaojin20135.mybaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.basemodule.menuitem.adapter.MenuItemAdapter;
import com.example.xiaojin20135.basemodule.menuitem.fragment.MenuItemFragment;
import com.example.xiaojin20135.basemodule.menuitem.listener.IMemuItemClick;
import com.example.xiaojin20135.mybaseapp.loaddata.fragment.CommonFragment;
import com.example.xiaojin20135.mybaseapp.recyclerview.fragment.MyRecyclerFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentActivity extends ToolBarActivity {
    //菜单相关变量
    MenuItemFragment menuItemFragment;
    List<Map<String,Integer>> datas;
    private int index = 0;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initView () {
        if(index == 0){
            CommonFragment commonFragment = new CommonFragment ();
            getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container,commonFragment).commit ();
        }else if(index == 1){
            MyRecyclerFragment myRecyclerFragment = MyRecyclerFragment.getInstance (this);
            getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container,myRecyclerFragment).commit ();
        }else if(index == 2){
            datas = new ArrayList<> ();
            for(int i=0;i<4;i++){
                Map map = new HashMap ();
                map.put (MenuItemAdapter.CODE,"");
                map.put (MenuItemAdapter.IMAGE,R.drawable.permission_ic_camera);
                map.put (MenuItemAdapter.TITLE,R.string.camera);
                map.put (MenuItemAdapter.COUNT,0);
                datas.add (map);
            }
            menuItemFragment = MenuItemFragment.getInstance (this, datas, myIMenuItemClick);
            menuItemFragment.setSpanCount (2); //每列图标个数
            getSupportFragmentManager ().beginTransaction ().replace (R.id.fragment_container,menuItemFragment).commit ();
        }
    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {
        index = getIntent ().getIntExtra ("index",0);
    }

    IMemuItemClick myIMenuItemClick = new IMemuItemClick(){
        @Override
        public void itemClick (int index) {
            Toast.makeText (getApplicationContext (),"您点击了 : " + index,Toast.LENGTH_SHORT).show ();
            int count = datas.get (index).get (MenuItemAdapter.COUNT) - 1;
            datas.get (index).put (MenuItemAdapter.COUNT,count);
            Log.d (TAG,"datas = " + datas);
            menuItemFragment.updataInfo ();
        }
    };
}
