package com.alleyway;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {

    private ImageView mIvBack;
    private TextView mTvTitle;

    /**
     *   简化寻找id findViewById
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T fd(@IdRes int id){
        return findViewById(id);
    }

    /**
     *  初始化NavigationBar
     * @param isShowBack
     * @param title
     */
    protected void initNavBar(boolean isShowBack, String title){

        mIvBack = fd(R.id.iv_back);
        mTvTitle = fd(R.id.tv_title);

        mIvBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        mTvTitle.setText(title);

        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击后退按钮，执行后退的操作
                onBackPressed();
            }
        });

    }

}
