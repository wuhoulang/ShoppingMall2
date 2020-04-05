package com.atguigu.shoppingmall.utils;

import com.atguigu.shoppingmall.interf.SuccessCallBack;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by HAOJI on 2020/4/5.
 */

public class FileDownloadManager {

    public static FileDownloadManager instance = null;

    private FileDownloadManager() {
    }// 避免类在外部被实例化

    public static FileDownloadManager getInstance() {
        if (null == instance) {
            instance = new FileDownloadManager();
        }
        return instance;
    }

    public void startDownLoadFileSingle(String downLoadUri, String destinationUri, SuccessCallBack callBack){
        FileDownloader.getImpl().create(downLoadUri).setPath(destinationUri).setListener(fileDownload(callBack)).start();
    }

   private FileDownloadListener fileDownload(final SuccessCallBack callBack){
        return new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                //等待，已经进入下载队列
            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                if (callBack != null){
                    callBack.onProgress(task,soFarBytes,totalBytes);
                }
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                //完成
                if(callBack != null){
                    callBack.onCompleted(task);
                }
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
                if (callBack != null){
                    callBack.onError(task,e);
                }
            }

            @Override
            protected void warn(BaseDownloadTask task) {
                //在下载队列中(正在等待/正在下载)已经存在相同下载连接与相同存储路径的任务
            }
        };
   }

}
