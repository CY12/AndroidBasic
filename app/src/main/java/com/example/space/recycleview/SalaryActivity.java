package com.example.space.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.space.R;
import com.example.space.http.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaryActivity extends AppCompatActivity {
    private LinearLayout title;
    private RecyclerView rvYear;
    private List<Salary.DataBean> dataBeans = new ArrayList<>();

    private List<Salary.DataBean.ListBean> listBeans = new ArrayList<>();
    private YearAdapter yearAdapter;

    private Salary salary;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);
        title = (LinearLayout) findViewById(R.id.title);
        rvYear = (RecyclerView) findViewById(R.id.rv_year);
//        for (int i = 0 ;i<3;i++){
//            Salary.DataBean.ListBean listBean = new Salary.DataBean.ListBean();
//            listBean.setMonth(i);
//            listBean.setValue(i*1000);
//            listBeans.add(listBean);
//        }
//        for (int i= 2018 ;i <2021;i++){
//            Salary.DataBean dataBean = new Salary.DataBean();
//            dataBean.setYear(i);
//            dataBean.setList(listBeans);
//            dataBeans.add(dataBean);
//        }



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        yearAdapter = new YearAdapter(SalaryActivity.this,R.layout.item_year,dataBeans);
        rvYear.setLayoutManager(linearLayoutManager);
        rvYear.setAdapter(yearAdapter);
        getSalary();
//        getDetail();
        getParty();

    }

    public void getDetail(String date,int total){

        Intent intent = new Intent(SalaryActivity.this,DetailActivity.class);
        intent.putExtra("total",total);
        intent.putExtra("date",date);
        startActivity(intent);

    }

    private void getParty(){
        Params params = new Params();
        params.setLoginName("210283199703127211");
        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();
        String json = gb.create().toJson(params);
        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        HttpUtils.getRequest().getParty(requestBody).enqueue(new Callback<Party>() {
            @Override
            public void onResponse(Call<Party> call, Response<Party> response) {

            }

            @Override
            public void onFailure(Call<Party> call, Throwable t) {

            }
        });
    }

    private void getSalary(){
        Gson gson = new Gson();
//        Salary salary = gson.fromJson(json,Salary.class);
//        dataBeans.addAll(salary.getData());
        Salary salary = new Salary();
        List<Salary.DataBean> dataBean = new ArrayList<>();
        for (int i = 2000;i<2012;i++){
            Salary.DataBean dataBean1 =new Salary.DataBean();
            List<Salary.DataBean.ListBean> listBean = new ArrayList<>();
            for (int j = 0;j<12;j++){
                Salary.DataBean.ListBean listBean1 = new Salary.DataBean.ListBean();
                listBean1.setMonth(j);
                listBean1.setValue(j+i);
                listBean.add(listBean1);
            }
            dataBean1.setYear(i);
            dataBean1.setList(listBean);
            dataBean.add(dataBean1);
        }
        salary.setData(dataBean);
        dataBeans.addAll(salary.getData());
        yearAdapter.notifyDataSetChanged();
//        Params params = new Params();
//        params.setLoginName("210283199703127211");
//        GsonBuilder gb = new GsonBuilder();
//        gb.disableHtmlEscaping();
//        String json = gb.create().toJson(params);
//        final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
//
//        HttpUtils.getRequest().getSalary(requestBody).enqueue(new Callback<Salary>() {
//            @Override
//            public void onResponse(Call<Salary> call, Response<Salary> response) {
//                if (response != null && response.code() == 200 && response != null){
//                    dataBeans.addAll(response.body().getData());
//                    yearAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Salary> call, Throwable t) {
//                Toast.makeText(SalaryActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
//            }
//        });


    }

}