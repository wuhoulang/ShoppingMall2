package com.atguigu.shoppingmall.open.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.open.adapter.ClassifyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HAOJI on 2019/12/13.
 */

public class MatingActivity extends FragmentActivity {

    private Context context = MatingActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_fragment);
        TabLayout tab_layout = findViewById(R.id.tab_layout);
        ViewPager vp= findViewById(R.id.vp_class);
        BookOneFragment bookOne = BookOneFragment.newInstance(context);
        BookTwoFragment booktwo = BookTwoFragment.newInstance(context);
        List<Fragment> list =new ArrayList<>();
        list.add(bookOne);
        list.add(booktwo);
        FragmentManager fm = getSupportFragmentManager();
        List<String> list1 =new ArrayList<>();
        list1.add("四级");
        list1.add("六级");
        ClassifyPagerAdapter cp =new ClassifyPagerAdapter(context,list,fm,list1);
        vp.setAdapter(cp);
        tab_layout.setupWithViewPager(vp);
    }
}
