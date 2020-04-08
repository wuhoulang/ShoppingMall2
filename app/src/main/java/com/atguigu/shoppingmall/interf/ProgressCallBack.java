package com.atguigu.shoppingmall.interf;

import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by HAOJI on 2020/4/8.
 */

public interface ProgressCallBack {

    void onNewProgress(long soFar,long total);

}
