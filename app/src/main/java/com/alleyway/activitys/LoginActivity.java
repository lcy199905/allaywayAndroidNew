package com.alleyway.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.alleyway.BaseActivity;
import com.alleyway.R;
import com.alleyway.pojo.BO.UserBO;
import com.alleyway.utils.SendHttpRequestUtils;
import com.alleyway.utils.UserUtil;
import com.alleyway.views.InputView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.input_username)
    InputView inputUsername;
    @BindView(R.id.input_password)
    InputView inputPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    private String resultJSON = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    /* 初始化 */
    private void init() {
        initNavBar(true, "登录");
    }


    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String username = inputUsername.getInputStr();
        String password = inputPassword.getInputStr();
        // 先验证格式是否正确
        if (!UserUtil.validateLogin(this, username, password)) {
            return;
        }

        try {
            /**发送请求出去，判断数据库是否有这个用户和密码是否正确**/
            //1、封装用户名和密码到Map
            Map<String, String> map = new HashMap<>();
            map.put("user_phone", username);
            map.put("user_password", password);
            //2、发送请求，获取到Json数据
            resultJSON = SendHttpRequestUtils.sendHttpRequest("VerifyController/login", map, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3、封装Json数据到POJO
        Gson gson = new Gson();
        UserBO userBO = gson.fromJson(resultJSON, UserBO.class);
        Log.e(TAG, "onViewClicked: " + userBO.toString() );
        //4、判断Json数据的状态码，判断有没有登录成功，成功转跳,用户信息存入到全局数据
        if(userBO.getCode() == 0){
            // User数据存入到全局保存
            SharedPreferences settings = getSharedPreferences("UserInfo", 0);
            SharedPreferences.Editor editor = settings.edit();
            // 用户id
            editor.putString("userId", String.valueOf(userBO.getContent().getId()));
            // 用户电话号
            editor.putString("userPhone", username);
            // 用户头像
            editor.putString("userHead", userBO.getContent().getUser_head());
            // 用户昵称
            editor.putString("userName", userBO.getContent().getUser_name());
            // 提交
            editor.commit();
            // 返回正确
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }



    }
}
