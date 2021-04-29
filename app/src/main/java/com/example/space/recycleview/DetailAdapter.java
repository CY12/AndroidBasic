package com.example.space.recycleview;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.space.R;

import java.util.List;

public class DetailAdapter extends BaseQuickAdapter<Detail, BaseViewHolder> {
    public DetailAdapter(int layoutResId, List<Detail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Detail detail) {
        baseViewHolder.setText(R.id.tv_officeLevelWage,detail.getData().getOfficeLevelWage()+"");
        baseViewHolder.setText(R.id.tv_rankSalary,detail.getData().getRankSalary()+"");
        baseViewHolder.setText(R.id.tv_other,detail.getData().getOther()+"");

    }
}
