package com.atguigu.shoppingmall.open.widget;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.adapter.HomeRecycleAdapter;
import com.atguigu.shoppingmall.home.bean.ResultBean;
import com.atguigu.shoppingmall.home.fragment.HomeFragment;
import com.atguigu.shoppingmall.open.adapter.ClassifyPagerAdapter;
import com.atguigu.shoppingmall.user.activity.MessageCenterActivity;
import com.atguigu.shoppingmall.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

/**
 * Created by HAOJI on 2020/4/1.
 */

public class OpenFragment extends BaseFragment {

        @Override
        public View initView() {
            View view = View.inflate(mContext, R.layout.type_fragment, null);
            TabLayout tab_layout = view.findViewById(R.id.tab_layout);
            ViewPager vp= view.findViewById(R.id.vp_class);
            BookOneFragment bookOne = BookOneFragment.newInstance(mContext);
            BookTwoFragment booktwo = BookTwoFragment.newInstance(mContext);
            List<Fragment> list =new ArrayList<>();
            list.add(bookOne);
            list.add(booktwo);
            FragmentManager fm = getChildFragmentManager();
            List<String> list1 =new ArrayList<>();
            list1.add("今日开服");
            list1.add("即将开服");
            ClassifyPagerAdapter cp =new ClassifyPagerAdapter(mContext,list,fm,list1);
            vp.setAdapter(cp);
            tab_layout.setupWithViewPager(vp);
            return view;
        }

        @Override
        public void initData() {

        }


        private void initListener() {

        }


}
