package com.example.space.salary;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        // glide 显示图片时可以根据 view tag 村的position 与当前position 比较加载
        baseViewHolder.getView(R.id.tv_month).setTag(R.id.tv_month,baseViewHolder.getAdapterPosition());

        // 隐藏item
        /*RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) helper.getView(R.id.rl_party).getLayoutParams();
        if (Integer.parseInt(item.getValue()) == 0){
            param.height = 0;
            param.width = 0;
            helper.getView(R.id.rl_party).setVisibility(View.GONE);
        }else {
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT; // 根据具体需求场景设置
            param.width = LinearLayout.LayoutParams.MATCH_PARENT;
            helper.getView(R.id.rl_party).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_month,item.getMonth());
            helper.setText(R.id.tv_salary,"¥"+item.getValue());
        }
        helper.getView(R.id.rl_party).setLayoutParams(param);*/

        baseViewHolder.setText(R.id.tv_month,listBean.getMonth()+"月");
        baseViewHolder.setText(R.id.tv_salary,"$"+listBean.getValue());
        baseViewHolder.getView(R.id.tv_salary).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
