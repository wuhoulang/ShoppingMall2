package com.atguigu.shoppingmall.utils.bitmapUtils;//package zuo.biao.library.util.bitmapUtils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.atguigu.shoppingmall.R;

/**
 * 三级缓存：如果每次都网络请求获取图片，会消耗更多的流量，会浪费更多的时间
 * 首先，优先内存中的图片，次优先本地的图片，最后网络请求图片。
 * 读取内存中的图片，否则读取本地的图片，否则读取网络请求图片并缓存到本地和内存。
 */

public class MyBitmapUtils {

    private NetCacheUtils mNetCacheUtils;
    private LocalCacheUtils mLocalCacheUtils;
    private MemoryCacheUtils mMemoryCacheUtils;

    public MyBitmapUtils(){
        mMemoryCacheUtils=new MemoryCacheUtils();
        mLocalCacheUtils =new LocalCacheUtils();
        mNetCacheUtils=new NetCacheUtils(mLocalCacheUtils,mMemoryCacheUtils);
    }


    public Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        bitmap = mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap != null) {
            return bitmap;
        }
        bitmap = mLocalCacheUtils.getBitmapFromLocal(url);
        if (bitmap != null) {
            mMemoryCacheUtils.setBitmapToMemory(url, bitmap);
            return bitmap;
        }

        return bitmap;
    }

    public void disPlay(ImageView ivPic,String url){
//        ivPic.setImageResource(R.drawable.btn_blue_round_big_pressed);
        Bitmap bitmap;
//        内存缓存
        bitmap=mMemoryCacheUtils.getBitmapFromMemory(url);
        if (bitmap!=null){
            ivPic.setImageBitmap(bitmap);
            return;
        }

//      本地缓存
        bitmap =mLocalCacheUtils.getBitmapFromLocal(url);
        if (bitmap!=null){
            ivPic.setImageBitmap(bitmap);
            mMemoryCacheUtils.setBitmapToMemory(url,bitmap);
            return;
        }
//        网络缓存
        mNetCacheUtils.getBitmapFromNet(ivPic,url);
    }
}
