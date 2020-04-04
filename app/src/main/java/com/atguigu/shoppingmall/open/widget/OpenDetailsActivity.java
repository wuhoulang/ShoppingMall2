package com.atguigu.shoppingmall.open.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.alipay.security.mobile.module.deviceinfo.AppInfo;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.open.adapter.ImageAdapter;
import com.hymane.expandtextview.ExpandTextView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HAOJI on 2020/4/3.
 */

public class OpenDetailsActivity extends Activity {

    AppInfo appInfo;
    @Bind(R.id.detail_iv_icon)
    ImageView mIvIcon;
    @Bind(R.id.detail_tv_name)
    TextView mTvName;
    @Bind(R.id.detail_tv_company)
    TextView mTvCompany;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ImageAdapter imageAdapter = new ImageAdapter();
        mRvImage.setAdapter(imageAdapter);
        mRvImage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}
