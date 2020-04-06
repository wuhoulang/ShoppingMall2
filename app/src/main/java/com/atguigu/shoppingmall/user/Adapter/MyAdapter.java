package com.atguigu.shoppingmall.user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;

import java.util.ArrayList;

/**
 * Created by HAOJI on 2020/4/6.
 */

public class MyAdapter extends BaseAdapter {


    private ArrayList<String> mList;
    private Context mContext;


    public MyAdapter(Context mContext) {
//        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 20;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        Holder holder = null;
//        if (convertView == null) {
//            holder = new Holder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
//            holder.mText = convertView.findViewById(R.id.ListView_Name);
//            convertView.setTag(holder);
//        } else {
//            holder = (Holder) convertView.getTag();
//        }
//        holder.mText.setText(mList.get(position));
        return convertView;
    }


//    class Holder {
//        private TextView mText;
//    }
}

