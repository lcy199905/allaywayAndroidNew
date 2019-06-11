package com.alleyway.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.alleyway.BaseActivity;
import com.alleyway.R;
import com.alleyway.utils.UserUtil;
import com.alleyway.views.InputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.input_username)
    InputView inputUsername;
    @BindView(R.id.input_password)
    InputView inputPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

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
        if(!UserUtil.validateLogin(this, username, password)){
            return;
        }
        //  发送请求出去，判断数据库是否有这个用户和密码是否正确

        // 返回正确
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);


    }
}
