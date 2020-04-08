package com.atguigu.shoppingmall.open.widget;

import android.app.Activity;
import android.app.DownloadManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.security.mobile.module.deviceinfo.AppInfo;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.interf.ProgressCallBack;
import com.atguigu.shoppingmall.interf.SuccessCallBack;
import com.atguigu.shoppingmall.open.adapter.ImageAdapter;
import com.atguigu.shoppingmall.utils.FileDownloadManager;
import com.atguigu.shoppingmall.utils.InstallApkUtils;
import com.hymane.expandtextview.ExpandTextView;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HAOJI on 2020/4/3.
 */

public class OpenDetailsActivity extends Activity implements View.OnClickListener {

    AppInfo appInfo;
    @Bind(R.id.detail_tv_decription)
    ExpandTextView mTvDecription;
    @Bind(R.id.detail_tv_updateLog)
    ExpandTextView mTvUpdateLog;
    @Bind(R.id.detail_rv_image)
    RecyclerView mRvImage;
    @Bind(R.id.detail_tv_download)
    TextView mTvDownload;
    @Bind(R.id.detail_tv_commentNum)
    TextView mTvCommentNum;
    @Bind(R.id.detail_tv_permission)
    TextView mTvPermission;
    @Bind(R.id.pb_download)
    ProgressBar pb_download;
    @Bind(R.id.rl_download)
    RelativeLayout rl_download;
    @Bind(R.id.tv_download)
    TextView tv_download;
    @Bind(R.id.detail_tv_version)
    TextView detail_tv_version;

    private String url;
    private String tempStr;
    private boolean isDownLoad = false;
    private int position;
    private String path;
    private String downPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initView();

        ImageAdapter imageAdapter = new ImageAdapter();
        mRvImage.setAdapter(imageAdapter);
        mRvImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void initView() {
        mTvDownload.setOnClickListener(this);
        mTvDecription.setOnClickListener(this);
        rl_download.setOnClickListener(this);
        detail_tv_version.setOnClickListener(this);

        url = getIntent().getStringExtra("url");
        position = getIntent().getIntExtra("pos", 0);
        tempStr = url.substring(url.lastIndexOf("/") + 1);

        path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" ;

        downPath =  path + tempStr;

        FileDownloadManager.getInstance().startDownLoadFileSingle(url, downPath, new ProgressCallBack() {
            @Override
            public void onNewProgress(long soFar, long total) {
                int ccc = (int) (soFar * 1.0f / total * 100);
                if (ccc==0){
                    tv_download.setText("下载");
                }else {
                    pb_download.setProgress(ccc);
                    tv_download.setText(String.valueOf(ccc) + "%");
                }

            }
        });
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/download/"+tempStr+".temp";
//        File file = new File(path);
//        if(file.exists()){
//            FileInputStream fis = null;
//            try {
//                fis = new FileInputStream(file);
//             int  size = fis.available();
//                Log.e("OpenDetailsActivity","size:"+size);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }

//         FileDownloadManager.getInstance().getSofar(url,tempStr,1);
        Log.e("OpenDetailsActivity", "url:" + url);
        Log.e("OpenDetailsActivity", "tempStr:" + tempStr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_download:
                if (isDownLoad) {
                    FileDownloadManager.getInstance().pauseDownload();
                    isDownLoad = false;
                    Log.e("OpenDetailsActivity", "isDownLoad:" + isDownLoad);
                    return;
                }
                isDownLoad = true;
                Log.e("OpenDetailsActivity", "isDownLoad:" + isDownLoad);

                FileDownloadManager.getInstance().startSigle(url, downPath, position, new SuccessCallBack() {
                    @Override
                    public void onCompleted(BaseDownloadTask task) {
                        pb_download.setProgress(100);
                        tv_download.setText("下载完成");
                        InstallApkUtils.installApk(OpenDetailsActivity.this,path,tempStr);
                        Log.e("OpenDetailsActivity", "------------onCompleted-------------");
                    }

                    @Override
                    public void onNowProgress(long soFarBytes, long totalBytes) {
                        int ccc = (int) (soFarBytes * 1.0f / totalBytes * 100);
                        if (ccc == 0) {
                            tv_download.setText("下载");
                        } else {
                            pb_download.setProgress(ccc);
                            tv_download.setText(String.valueOf(ccc) + "%");
                        }
                    }

                    @Override
                    public void onError(BaseDownloadTask task, Throwable e) {
                        Log.e("OpenDetailsActivity", "------------onError-------------" + e);
                    }

                    @Override
                    public void onProgress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        int bbb = (int) (soFarBytes * 1.0f / totalBytes * 100);
                        pb_download.setProgress(bbb);
                        tv_download.setText(String.valueOf(bbb) + "%");
                        Log.e("OpenDetailsActivity", "------------onProgress-------------" + ",soFarBytes:" + soFarBytes + ",totalBytes:" + totalBytes);
                        Log.e("OpenDetailsActivity", "------------onProgress-------------" + String.valueOf(bbb));
                    }
                });

//                 url = "http://acj5.pc6.com/pc6_soure/2020-4-1/a793f1e85f5768aWRDcQFWjON8sOp4.apk";
//                String url = "http://192.168.8.119:80/atguigu/save_folder/leitingzhengjiu_Tencent_2.0.1_20191029164007.apk";
//                http://192.168.8.119:80/atguigu/save_folder/leitingzhengjiu_Tencent_2.0.1_20191029164007.apk
//                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/"+tempStr;
//                Log.e("OpenDetailsActivity","------------path-------------:"+path);
//                sigleTask(path);
//                FileDownloadManager.getInstance().startListUrl();

                break;
            case R.id.detail_tv_version:

                break;
            default:
                break;
        }
    }

    private void sigleTask(String path) {
//        FileDownloadManager.getInstance().startDownLoadFileSingle(url, path,position, new SuccessCallBack() {
//            @Override
//            public void onCompleted(BaseDownloadTask task) {
//                pb_download.setProgress(100);
//                tv_download.setText("下载完成");
//                Log.e("OpenDetailsActivity","------------onCompleted-------------");
//            }
//
//            @Override
//            public void onNowProgress(long soFarBytes, long totalBytes) {
//                int ccc = (int) (soFarBytes * 1.0f / totalBytes * 100);
//                if (ccc==0){
//                    tv_download.setText("下载");
//                }else {
//                    pb_download.setProgress(ccc);
//                    tv_download.setText(String.valueOf(ccc)+"%");
//                }
//            }
//
//            @Override
//            public void onError(BaseDownloadTask task, Throwable e) {
//                Log.e("OpenDetailsActivity","------------onError-------------"+e);
//            }
//
//            @Override
//            public void onProgress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
//               int bbb = (int) (soFarBytes * 1.0f / totalBytes * 100);
//                pb_download.setProgress(bbb);
//                tv_download.setText(String.valueOf(bbb)+"%");
//                Log.e("OpenDetailsActivity","------------onProgress-------------"+",soFarBytes:"+soFarBytes+",totalBytes:"+totalBytes);
//                Log.e("OpenDetailsActivity","------------onProgress-------------"+String.valueOf(bbb));
//            }
//        });
    }
}
