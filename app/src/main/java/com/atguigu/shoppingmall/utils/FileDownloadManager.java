package com.atguigu.shoppingmall.utils;

import android.os.Environment;
import android.util.Log;

import com.atguigu.shoppingmall.interf.ProgressCallBack;
import com.atguigu.shoppingmall.interf.SuccessCallBack;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadQueueSet;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAOJI on 2020/4/5.
 */

public class FileDownloadManager {

    public static FileDownloadManager instance = null;
    private int id;
    private BaseDownloadTask baseDownloadTask;

    private FileDownloadManager() {
    }// 避免类在外部被实例化

    public static FileDownloadManager getInstance() {
        if (null == instance) {
            instance = new FileDownloadManager();
        }
        return instance;
    }

    public void startDownLoadFileSingle(String downLoadUri, String destinationUri, SuccessCallBack callBack) {
        FileDownloader.getImpl().create(downLoadUri).setPath(destinationUri).setListener(fileDownload(callBack)).start();
    }

    public void startDownLoadFileSingle(String downLoadUri, String destinationUri, ProgressCallBack progressCallBack) {
        baseDownloadTask = FileDownloader.getImpl().create(downLoadUri).setPath(destinationUri);
        id = baseDownloadTask.getId();
        long soFar = FileDownloader.getImpl().getSoFar(id);
        long total = FileDownloader.getImpl().getTotal(id);
        progressCallBack.onNewProgress(soFar,total);
        Log.e("FileDownloadManager", "soFar:" + String.valueOf(soFar));
        Log.e("FileDownloadManager", "total:" + String.valueOf(total));
        Log.e("FileDownloadManager", "downLoadId:" + String.valueOf(id));
    }


    public void startSigle(String downLoadUri, String destinationUri, int tag, SuccessCallBack callBack){
        baseDownloadTask = FileDownloader.getImpl().create(downLoadUri).setPath(destinationUri).setListener(fileDownload(callBack)).setTag(tag);
        id = baseDownloadTask.getId();
        baseDownloadTask.start();
    }


    public void startListUrl() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/bb.apk";

        String dUrl = "http://acj5.pc6.com/pc6_soure/2020-4-1/a793f1e85f5768aWRDcQFWjON8sOp4.apk";
        List<String> URLS = new ArrayList<>();
        URLS.add(dUrl);

        final List<BaseDownloadTask> tasks = new ArrayList<>();
        for (int i = 0; i < URLS.size(); i++) {
            tasks.add(FileDownloader.getImpl().create(URLS.get(i)).setPath(path).setTag(i + 1));
        }
            FileDownloader.getImpl().start(downloadListener, true);

    }

    public void pauseDownload() {
        FileDownloader.getImpl().pause(id);
    }

    private FileDownloadListener fileDownload(final SuccessCallBack callBack) {
        return new FileDownloadListener() {
            @Override
            protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                //等待，已经进入下载队列
                Log.e("FileDownloadManager", "--------------pending------------");
            }

            @Override
            protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                if (callBack != null) {
                    callBack.onProgress(task, soFarBytes, totalBytes);
                }
            }

            @Override
            protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
            }

            @Override
            protected void completed(BaseDownloadTask task) {
                //完成
                if (callBack != null) {
                    callBack.onCompleted(task);
                }
            }

            @Override
            protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                Log.e("FileDownloadManager", "--------------paused------------");
            }

            @Override
            protected void error(BaseDownloadTask task, Throwable e) {
                if (callBack != null) {
                    callBack.onError(task, e);
                }
            }

            @Override
            protected void warn(BaseDownloadTask task) {
                //在下载队列中(正在等待/正在下载)已经存在相同下载连接与相同存储路径的任务
                Log.e("FileDownloadManager", "--------------warn------------");
            }
        };
    }

    final FileDownloadListener downloadListener = new FileDownloadListener() {
        @Override
        protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        }

        @Override
        protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
        }

        @Override
        protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
            Log.e("FileDownloadManager", "--------------soFarBytes------------"+soFarBytes);
        }

        @Override
        protected void blockComplete(BaseDownloadTask task) {
        }

        @Override
        protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
        }

        @Override
        protected void completed(BaseDownloadTask task) {
            Log.e("FileDownloadManager", "--------------completed------------");
        }

        @Override
        protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
        }

        @Override
        protected void error(BaseDownloadTask task, Throwable e) {
        }

        @Override
        protected void warn(BaseDownloadTask task) {
        }
    };

}
