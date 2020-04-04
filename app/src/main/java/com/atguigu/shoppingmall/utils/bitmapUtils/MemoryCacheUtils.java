package com.atguigu.shoppingmall.utils.bitmapUtils;//package zuo.biao.library.util.bitmapUtils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 三级缓存之内存缓存
 */

public class MemoryCacheUtils {

    private LruCache<String,Bitmap> mMemoryCache;


    public MemoryCacheUtils(){
        //得到手机最大允许的的1/8 ，即超过指定内存，则开始回收
        long maxMemory =Runtime.getRuntime().maxMemory()/8;
        // 需要传入允许的内存最大值，虚拟机默认内存16m,真机不一定相同
        mMemoryCache=new LruCache<String,Bitmap>((int) maxMemory){
            //计算每个条目的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getByteCount();
                return byteCount;
            }
        };
    }

//    从内存读图片
    public Bitmap getBitmapFromMemory(String url){
        if(url==null||"".equals(url)){
            return null;
        }
        Bitmap bitmap =mMemoryCache.get(url);
        return bitmap;
    }
//      往内存写图片
    public void setBitmapToMemory(String url,Bitmap bitmap){
        mMemoryCache.put(url,bitmap);
    }

}
