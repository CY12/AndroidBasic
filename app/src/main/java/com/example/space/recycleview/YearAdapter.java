package com.example.space.recycleview;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.space.R;

import java.util.List;

public class YearAdapter extends BaseQuickAdapter<Salary.DataBean, BaseViewHolder> {
    private Context context;




    public YearAdapter(Context context,int layoutResId, List<Salary.DataBean> data) {
        super(layoutResId, data);
        this.context = context;

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Salary.DataBean dataBean) {
        SalaryAdapter salaryAdapter = new SalaryAdapter(context,dataBean.getYear(),R.layout.item_salary,dataBean.getList());

        baseViewHolder.setText(R.id.tv_year,dataBean.getYear()+"");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        RecyclerView recyclerview = baseViewHolder.getView(R.id.rv_salary);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(salaryAdapter);

    }
    private void getDate(){

    }
}
