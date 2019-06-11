package com.alleyway.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alleyway.BaseActivity;
import com.alleyway.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    private void init(){
        initNavBar(true, "登录");
    }

    public void onCommitClick(View v){

    }
}
