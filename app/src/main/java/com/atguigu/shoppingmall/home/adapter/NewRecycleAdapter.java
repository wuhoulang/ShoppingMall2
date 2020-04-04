package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.ResultBean;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by HAOJI on 2020/4/1.
 */

public class NewRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ResultBean.SeckillInfoBean data;
//    private final List<ResultBean.SeckillInfoBean.ListBean> list;

    public NewRecycleAdapter(Context mContext) {
        this.mContext = mContext;
//        this.data = data;
//        list = data.getList();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewRecycleAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_new_game, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewRecycleAdapter.MyViewHolder myViewHolder = (NewRecycleAdapter.MyViewHolder) holder;
        myViewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_new_game;

        public MyViewHolder(View itemView) {
            super(itemView);
//            ivFigure = (ImageView) itemView.findViewById(R.id.iv_figure);
//            tvCoverPrice = (TextView) itemView.findViewById(R.id.tv_cover_price);
//            tvOriginPrice = (TextView) itemView.findViewById(R.id.tv_origin_price);
            rl_new_game = (RelativeLayout) itemView.findViewById(R.id.rl_new_game);
        }

        public void setData(final int position) {
//            ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);
//            tvCoverPrice.setText("￥" + listBean.getCover_price());
//            tvOriginPrice.setText("￥" + listBean.getOrigin_price());
//            Glide.with(mContext)
//                    .load(Constants.BASE_URl_IMAGE +listBean.getFigure())
//                    .into(ivFigure);
            rl_new_game.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNewRecyclerView != null) {
                        onNewRecyclerView.onClick(position);
                    }
                }
            });
        }
    }

    public interface OnNewRecyclerView {
        void onClick(int position);
    }

    public void setOnNewRecyclerView(NewRecycleAdapter.OnNewRecyclerView onNewRecyclerView) {
        this.onNewRecyclerView = onNewRecyclerView;
    }

    private NewRecycleAdapter.OnNewRecyclerView onNewRecyclerView;

}
