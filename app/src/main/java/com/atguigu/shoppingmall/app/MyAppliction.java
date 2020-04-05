package com.atguigu.shoppingmall.app;

import android.app.Application;
import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;

public class MyAppliction extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        //Hawk存储初始化
        // 不耗时，做一些简单初始化准备工作，不会启动下载进程
        FileDownloader.setupOnApplicationOnCreate(this)
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                ))
                .commit();
    }
    // 获取全局上下文
    public static Context getContext() {
        return mContext;
    }

}
