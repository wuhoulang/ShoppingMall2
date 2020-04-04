package com.atguigu.shoppingmall.open.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by HAOJI on 2019/11/1.
 */

public class ClassifyPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> list;
    private List<String> list1;


    public ClassifyPagerAdapter(Context context, List<Fragment> list, FragmentManager fm, List<String> list1) {
        super(fm);
        this.context=context;
        this.list=list;
        this.list1=list1;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       return list1.get(position);
    }
}
