package com.atguigu.shoppingmall.utils.bitmapUtils;//package zuo.biao.library.util.bitmapUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by HAOJI on 2019/10/9.
 */

public class LocalCacheUtils {

    private static final String CACHE_PATH= Environment.getExternalStorageDirectory().getAbsolutePath()+
            "/WerbNews";

    public Bitmap getBitmapFromLocal(String url){
        String fileName=null;
        try {
//            fileName= MD5Util.ToMD5NOKey(url);
            fileName= url;
            File file =new File(CACHE_PATH,fileName);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBitmapToLocal(String url,Bitmap bitmap){
        try {
//            String fileName=MD5Util.ToMD5NOKey(url);
            String fileName=url;
            File file =new File(CACHE_PATH,fileName);
            File paarentFile =file.getParentFile();
            if(!paarentFile.exists()){
                paarentFile.mkdir();
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
