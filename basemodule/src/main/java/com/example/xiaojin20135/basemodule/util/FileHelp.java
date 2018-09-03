package com.example.xiaojin20135.basemodule.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;

/**
 * Created by lixiaojin on 2018-09-03 11:51.
 * 功能描述：
 */

public enum FileHelp {
    FILE_HELP;
    /**
     * @author lixiaojin
     * @createon 2018-09-03 13:38
     * @Describe URI 转 绝对路径
     */
    public String getFilePath(final Context context, final Uri uri) {
        if (null == uri){
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if(scheme == null){
            data = uri.getPath();
        }else if(ContentResolver.SCHEME_FILE.equals(scheme)){
            data = uri.getPath();
        }else if(ContentResolver.SCHEME_CONTENT.equals(scheme)){
            Cursor cursor = context.getContentResolver().query(uri, new String[]
                {
                    MediaStore.Images.ImageColumns.DATA
                }, null, null, null );
            if(null != cursor){
                if(cursor.moveToFirst()){
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if(index > -1){
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * @author lixiaojin
     * @createon 2018-09-03 13:41
     * @Describe URI数组转换为String绝对路径
     */
    public ArrayList<String> getFilePathArray(final Context context, final ArrayList<Uri> uriArrayList){
        ArrayList<String> filePathList = new ArrayList<> ();
        if(uriArrayList != null && uriArrayList.size () > 0){
            for(int i=0;i<uriArrayList.size ();i++){
                filePathList.add (getFilePath (context,uriArrayList.get (i)));
            }
        }
        return filePathList;
    }

}
