package com.alleyway.utils;

import com.alleyway.pojo.Paging;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * json工具类
 */
public class JsonUtils {

    /**
     * 解析json中只有一个类
     * @param json 要解析的json字符串
     * @param classz 要解析成的类型class
     * @return
     */
    public static Object jsonToClass(String json, Class classz){

        Integer code = getCode(json);

        if (code != 0){
            return null;
        }

        String s = "";
        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonObject = new JSONObject(jsonObject.getString("content"));
            JSONArray jsonArray = jsonObject.getJSONArray("content");
            s = jsonArray.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Object object = new Gson().fromJson(s, classz);

        return object;
    }

    /**
     * 解析分页的json数据
     * @param json 要解析的json字符串
     * @param classz 数据类型的class
     * @return
     */
    public static Paging jsonToMap(String json, Class classz){

        Integer code = getCode(json);

        if (code != 0){
            return null;
        }

        String s = "";
        JSONArray jsonArray = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            jsonObject = new JSONObject(jsonObject.getString("content"));
            jsonObject = new JSONObject(jsonObject.getString("content"));
            s = jsonObject.toString();

            jsonArray = jsonObject.getJSONArray("list");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Paging paging = gson.fromJson(s, Paging.class);

        List list = new ArrayList(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(1);
                Object object = gson.fromJson(jsonObject.toString(), classz);
                list.add(object);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        paging.setList(list);

        return paging;
    }

    /**
     * 获取json中的code
     * @param json 要解析的json字符串
     * @return
     */
    public static Integer getCode(String json){
        Integer code = -1;
        try {
            JSONObject jsonObject = new JSONObject(json);
            code = jsonObject.getInt("code");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return code;
    }

}
