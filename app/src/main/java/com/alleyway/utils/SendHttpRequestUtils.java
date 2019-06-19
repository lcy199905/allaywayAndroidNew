package com.alleyway.utils;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import okhttp3.*;

public class SendHttpRequestUtils {

    private static final String URL = "http://www.cyhfwq.top/designForum2/";
//    private static final String URL = "http://192.168.1.143/design/";

    /**
     * 发送Http请求
     * @param url 请求的url地址（不需要带参）
     * @param paramMap 封装url参数的map
     * @param isGet true：get请求 false：post请求
     * @return 获取到的json数据
     * @throws Exception
     */
    public static String sendHttpRequest(String url, Map<String,String> paramMap, boolean isGet) throws Exception {
        StringBuilder sb = new StringBuilder(url).append("?");
        Set<String> keySet = paramMap.keySet();
        for (String key : keySet) {
            String value = paramMap.get(key);
            sb.append(key).append("=").append(value).append("&");
        }
        String s = sb.toString();
        s = s.substring(0, s.length() - 1);
        String jsonData = sendHttpRequest(s, isGet);
        return jsonData;
    }

    /**
     * 发送Http请求
     * @param url 请求的url地址
     * @param isGet true：get请求 false：post请求
     * @return 获取到的json数据
     * @throws Exception
     */
    public static String sendHttpRequest(String url, boolean isGet) throws Exception {

        System.out.println(url);
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

            if (requestBody == null) return "";

            url = new String(URL + url).split("\\?")[0];
            System.out.println(url);

            request = new Request.Builder().url(url).post(requestBody).build();
        }
        // 创建一个新的线程来执行http请求
        SendHttpRequestCallable shrc =  new SendHttpRequestCallable(request);

        futureTask = new FutureTask<>(shrc);
        new Thread(futureTask).start();

        String jsonData = futureTask.get();

        System.out.println("jsonData = " + jsonData);
        return jsonData;
    }

    /**
     * 生成post请求所需的FormBody
     * @param url 需要解析的url
     * @return url参数解析并封装好的RequestBody对象用于post请求
     */
    private static RequestBody generateRequestBody(String url){
        String[] split = url.split("\\?");
        if (split == null || split.length < 2){
            return null;
        }
        String paramStr = split[1];

        paramStr = paramStr.replaceAll("\\=", ":");
        paramStr = paramStr.replaceAll("\\&", ";");
        paramStr = paramStr.replaceAll(" ","");
        paramStr = paramStr.replaceAll("\n","");
        paramStr = paramStr.replaceAll("\r","");
        System.out.println("进来了~~~~~~~~~~~~~~~~~~~~~");

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
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        String data = response.body().string();
        return data;
    }
}
