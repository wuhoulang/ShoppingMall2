package com.atguigu.shoppingmall.open.widget;

import android.app.Activity;
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
import android.widget.TextView;

import com.alipay.security.mobile.module.deviceinfo.AppInfo;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.interf.SuccessCallBack;
import com.atguigu.shoppingmall.open.adapter.ImageAdapter;
import com.atguigu.shoppingmall.utils.FileDownloadManager;
import com.hymane.expandtextview.ExpandTextView;
import com.liulishuo.filedownloader.BaseDownloadTask;

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
//        String url = getIntent().getStringExtra("url");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_tv_decription:

                String url = "http://acj5.pc6.com/pc6_soure/2020-4-1/a793f1e85f5768aWRDcQFWjON8sOp4.apk";

                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Download/ttt.apk";
                Log.e("OpenDetailsActivity","------------path-------------:"+path);
                FileDownloadManager.getInstance().startDownLoadFileSingle(url, path, new SuccessCallBack() {
                    @Override
                    public void onCompleted(BaseDownloadTask task) {
                        pb_download.setProgress(100);
                        Log.e("OpenDetailsActivity","------------onCompleted-------------");
                    }

                    @Override
                    public void onError(BaseDownloadTask task, Throwable e) {
                        Log.e("OpenDetailsActivity","------------onError-------------"+e);
                    }

                    @Override
                    public void onProgress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                       int bbb = (int) (soFarBytes * 1.0f / totalBytes * 100);
                        pb_download.setProgress(bbb);
                        Log.e("OpenDetailsActivity","------------onProgress-------------"+",soFarBytes:"+soFarBytes+",totalBytes:"+totalBytes);
                        Log.e("OpenDetailsActivity","------------onProgress-------------"+String.valueOf(bbb));
                    }
                });
                break;
            default:
                break;
        }
    }
}
