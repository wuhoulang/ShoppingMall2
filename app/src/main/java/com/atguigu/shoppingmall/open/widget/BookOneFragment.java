package com.atguigu.shoppingmall.open.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.open.adapter.OpenOneRecycleAdapter;

/**
 * Created by HAOJI on 2019/8/22.
 */

@SuppressLint("ValidFragment")
public class BookOneFragment extends Fragment {

    private static Context context;

    public BookOneFragment() {
    }

    public static BookOneFragment newInstance(Context context1) {
        context = context1;
        BookOneFragment fragment = new BookOneFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.book_one,container,false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        RecyclerView ry = view.findViewById(R.id.ry_book);
        ry.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        OpenOneRecycleAdapter bo =new OpenOneRecycleAdapter(context);
        ry.setAdapter(bo);
    }
}
