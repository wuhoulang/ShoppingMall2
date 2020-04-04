package com.atguigu.shoppingmall.open.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.shoppingmall.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<String> mSrcList;
    private Context mContext;

    private boolean isNotLoadBitmap = false;

//    public ImageAdapter(List<String> mSrcList,boolean isNotLoadBitmap) {
//        this.mSrcList = mSrcList;
//        this.isNotLoadBitmap=isNotLoadBitmap;
//    }


    public ImageAdapter() {

    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new ImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
//        String src = mSrcList.get(position);
        if (!isNotLoadBitmap) {
            Glide.with(mContext).load(R.drawable.xiao_splash_v_1).asBitmap().placeholder(R.drawable.xiao_splash_v_0).into(holder.imageView);
        } else {
            Glide.with(mContext).load(R.drawable.xiao_splash_v_1).into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_iv);
        }
    }

    public void setNotLoadBitmap(boolean isNotLoadBitmap) {
        this.isNotLoadBitmap = isNotLoadBitmap;
        notifyDataSetChanged();
    }
}
