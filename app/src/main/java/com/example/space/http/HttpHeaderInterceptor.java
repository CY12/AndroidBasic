package com.example.space.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HttpHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String accessToken ="Token";
//        String tokenType = "tokenType";
        Request request = chain.request().newBuilder()
//                .header("app_key", "appId")
                .header("Authorization",accessToken)
//                .header("Authorization", tokenType + " " + accessToken)
                .header("Content-Type", "application/json")
//                .addHeader("Connection", "close")
//                .addHeader("Accept-Encoding", "identity")
                .build();
        return chain.proceed(request);
    }
}
