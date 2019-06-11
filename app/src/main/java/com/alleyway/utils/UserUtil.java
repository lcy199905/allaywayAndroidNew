package com.alleyway.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.alleyway.activitys.LoginActivity;
import com.blankj.utilcode.util.RegexUtils;

public class UserUtil {

    /**
     * 验证登录用户输入合法性
     */
    public static boolean validateLogin(Context context, String username, String password){

        // 验证用户输入是否合法


        if(!RegexUtils.isUsername(username)){
            Toast.makeText(context, "用户名长度必须是 6-20 位", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(context, "请输入密码", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;

    }

    /**
     * 退出登录
     */
    public static void logout(Context context){
        Intent intent =  new Intent(context, LoginActivity.class);
        // 添加Intent标识符，清理task栈，并且新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
