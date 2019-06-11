package com.alleyway.activitys;

import android.os.Bundle;
import android.widget.Button;

import com.alleyway.BaseActivity;
import com.alleyway.R;
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

    }
}
