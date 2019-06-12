package com.alleyway.utils;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import okhttp3.*;

public class SendHttpRequestUtils {

    private static final String URL = "http://www.cyhfwq.top/designForum/";

    public static String sendHttpRequest(String url, boolean isGet) throws Exception {
        // 创建Gson实例
        Gson gson = new Gson();
        // 用于接受SendHttpRequestCallable返回来的数据
        FutureTask<String> futureTask = null;

        Request request = null;
        // 判断请求是否为get
        if (isGet){
            // 构建get请求
            request = new Request.Builder().get().url(URL + url).build();
        }else {
            // 构建post请求
            RequestBody requestBody = generateRequestBody(url);

            if (requestBody == null) return null;

            request = new Request.Builder().url(URL + url).post(requestBody).build();
        }
        // 创建一个新的线程来执行http请求
        SendHttpRequestCallable shrc =  new SendHttpRequestCallable(request);

        futureTask = new FutureTask<>(shrc);
        new Thread(futureTask).start();

        String jsonData = futureTask.get();
        return jsonData;
    }

    /**
     * 生成post请求所需的FormBody
     * @param url
     * @return
     */
    private static RequestBody generateRequestBody(String url){
        String[] split = url.split("\\?");
        if (split == null || split.length < 2){
            return null;
        }
        String paramStr = split[1];

        paramStr.replaceAll("=", ":");
        paramStr.replaceAll("\\&", ";");

        System.out.println("{" + paramStr + "}");

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), "{" + paramStr + "}");

        return requestBody;
    }
}

/**
 * 可以返回数据的Thread
 */
class SendHttpRequestCallable implements Callable<String> {

    private Request request;

    public SendHttpRequestCallable(Request request) {
        this.request = request;
    }

    @Override
    public String call() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        return data;
    }
}
