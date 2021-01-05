package com.example.space.http;

import com.example.space.salary.Detail;
import com.example.space.salary.Party;
import com.example.space.salary.Salary;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Http {

    @POST("list")
    Call<Salary> getSalary(@Body RequestBody body);

    @POST("detail")
    Call<Detail> getDetail(@Body RequestBody body);

    @POST("party/list")
    Call<Party> getParty(@Body RequestBody body);
}
