package com.example.xiaojin20135.basemodule.image.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xiaojin20135.basemodule.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lixiaojin on 2018-09-01 15:27.
 * 功能描述：
 */

public class MyPhotoAdapter extends RecyclerView.Adapter<MyPhotoAdapter.PhotoViewHolder>{
    private static final String TAG = "MyPhotoAdapter";
    private ArrayList<String> photoPaths = new ArrayList<String>();
    private LayoutInflater inflater;
    private Context mContext;
    public final static int TYPE_ADD = 1;//增加图片图标
    public final static int TYPE_PHOTO = 2; //选中的图片
    //允许上传的图片限制为1张
    public final static int MAX = 15;

    public MyPhotoAdapter(Context mContext, ArrayList<String> photoPaths) {
        this.photoPaths = photoPaths;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        Log.d(TAG,"viewType="+viewType);
        switch (viewType) {
            case TYPE_ADD:
                itemView = inflater.inflate(R.layout.item_image_add, parent, false);
                break;
            case TYPE_PHOTO:
                itemView = inflater.inflate(R.layout.item_photo_picker, parent, false);
                break;
        }
        return new PhotoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
        Log.d("PhotoAdapter","in onBindViewHolder.position="+position);
        if (getItemViewType(position) == TYPE_PHOTO) {
            String filePath = photoPaths.get(position);
            Glide.with(mContext)
                .load(filePath)
                .thumbnail(0.1f)
                .into(holder.ivPhoto);
        }
    }

    /**
     * @author lixiaojin
     * @createon 2018-09-04 14:42
     * @Describe 替换全部
     */
    public void addAll(ArrayList<String> arrayList){
        photoPaths = arrayList;
        this.notifyDataSetChanged ();
    }


    @Override
    public int getItemCount () {
        return photoPaths.size()+1;
    }

    public void clearData(){
        photoPaths.clear ();
        this.notifyDataSetChanged ();
    }

    /**
     * 判断图片的类型，是增加图片你的图标还是选择的将要显示的图片
     * @param position
     */
    @Override
    public int getItemViewType(int position) {
        return (position == photoPaths.size()) ? TYPE_ADD : TYPE_PHOTO;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto  = (ImageView) itemView.findViewById(R.id.iv_photo);
        }
    }
}
