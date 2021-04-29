package com.example.space.http;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 更换 request 里的内容 要返回一个新的 request
 */
public class BaseURLInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 获取request
        Request request = chain.request();
        // 从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        // 获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        if (false) {// 替换的条件
            if (oldHttpUrl.encodedPath().contains("-")) {
                String path = oldHttpUrl.encodedPath().replace("-", "/");
                HttpUrl newFullUrl = oldHttpUrl
                        .newBuilder()
                        .scheme(oldHttpUrl.scheme()) //协议 http or https
                        .host(oldHttpUrl.host())//ip 域名 20.1.120.222
                        .encodedPath(path)//路径  /wave
                        .port(oldHttpUrl.port())//端口号 9090
                        .build();
                return chain.proceed(builder.url(newFullUrl).build());

            }
        }

        // 从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers("url_name");
        if (headerValues != null && headerValues.size() > 0) {
            // 如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("url_name");
            // 匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            HttpUrl newBaseUrl = null;
            if ("authCenter".equals(headerValue)) {
                newBaseUrl = HttpUrl.parse("Config.baseAuthCenterUrl");
            } else if ("userCenter".equals(headerValue)) {
                newBaseUrl = HttpUrl.parse("Config.baseUserCenterUrl");
            } else {
                newBaseUrl = oldHttpUrl;
            }
            // 重建新的HttpUrl，修改需要修改的url部分
            HttpUrl newFullUrl = oldHttpUrl
                    .newBuilder()
                    // 更换网络协议
                    .scheme(newBaseUrl.scheme())
                    // 更换主机名
                    .host(newBaseUrl.host())
                    // 更换端口
                    .port(newBaseUrl.port())
                    .build();
            Log.e("Test", "HttpUrl" + newFullUrl.toString());
            // 重建这个request，通过builder.url(newFullUrl).build()；
            // 然后返回一个response至此结束修改
            return chain.proceed(builder.url(newFullUrl).build());
        }

        return chain.proceed(request);
    }
}