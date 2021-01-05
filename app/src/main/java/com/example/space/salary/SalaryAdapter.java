package com.example.space.salary;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.space.R;

import java.util.List;

public class SalaryAdapter extends BaseQuickAdapter<Salary.DataBean.ListBean, BaseViewHolder> {
    private Context context;
    private int year;
    public SalaryAdapter(Context context,int  year,int layoutResId, List<Salary.DataBean.ListBean> data) {
        super(layoutResId, data);
        this.context = context;
        this.year = year;

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Salary.DataBean.ListBean listBean) {
        baseViewHolder.setText(R.id.tv_month,listBean.getMonth()+"月");
        baseViewHolder.setText(R.id.tv_salary,"$"+listBean.getValue());
        baseViewHolder.getView(R.id.tv_salary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test","click");
                SalaryActivity salaryActivity = (SalaryActivity) context;
                salaryActivity.getDetail(getDate(listBean.getMonth()),listBean.getValue());
            }
        });

    }
    private String getDate(int month){
        String date = year+"";
        if (month<10){
            date = date +"0"+month;
        }else {
            date = date + month;
        }
        Log.d("Test","getDetail "+date);
        return date;
    }
}
