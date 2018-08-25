package com.example.xiaojin20135.basemodule.menuitem.help;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lixiaojin on 2018-08-25 9:10.
 * 功能描述：
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void onDrawOver (Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver (c, parent, state);
    }

    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets (outRect, view, parent, state);
        outRect.left = 10;
        outRect.top = 10;
        outRect.top = 10;
    }
}
