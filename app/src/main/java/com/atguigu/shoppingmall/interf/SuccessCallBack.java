package com.atguigu.shoppingmall.interf;

import com.liulishuo.filedownloader.BaseDownloadTask;

/**
 * Created by HAOJI on 2020/4/5.
 */

public interface SuccessCallBack {

    void onCompleted(BaseDownloadTask task);

    void onError(BaseDownloadTask task, Throwable e);

    void onProgress(BaseDownloadTask task, int soFarBytes, int totalBytes);

    void onNowProgress(long soFarBytes, long totalBytes);

}
