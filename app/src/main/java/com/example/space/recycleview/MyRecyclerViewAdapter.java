package com.example.space.recycleview;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.space.R;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<UserBean> mDatas;

    private int onCreateViewHolderCount = 0;

    private int onBindViewHolderCount = 0;

    private OnMyRecyclerViewClickListener mClickListener;

    public static final int MY_ITEM_TYPE = 0;

    public MyRecyclerViewAdapter(Context mContext, List<UserBean> mDatas, OnMyRecyclerViewClickListener clickListener) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MY_ITEM_TYPE) {

            onCreateViewHolderCount++;
            Log.e("Test", "onCreateViewHolder()" + onCreateViewHolderCount);
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            int itemHeight = (int) (DisplayUtil.getScreenHeightByPix(mContext)/7f);
            itemView.setMinimumHeight(itemHeight);
            return new MyViewHolder(itemView, mContext, mClickListener);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindViewHolderCount++;

        Log.e("Test", "onBindViewHolder()" + onBindViewHolderCount+"  Position"+position);
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.bindData(mDatas.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return MY_ITEM_TYPE;
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAge;
        TextView tvSex;

        public MyViewHolder(@NonNull View itemView, Context context, final OnMyRecyclerViewClickListener onMyRecyclerViewClickListener) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvSex = itemView.findViewById(R.id.tv_sex);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onMyRecyclerViewClickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        onMyRecyclerViewClickListener.onUserClick(getAdapterPosition());
                    }
                }
            });
        }

        public void bindData(UserBean userBean) {
            tvName.setText(userBean.getUserName());
            tvAge.setText(userBean.getAge() + "");
            tvSex.setText(userBean.getSex());
        }

    }


    interface OnMyRecyclerViewClickListener {
        void onUserClick(int position);
    }

}
