package com.alleyway;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /* 获取到子页面的3个Activity */
    private Fragment mHomeFragment;
    private Fragment mFileloadFragment;
    private Fragment mMeFragment;

    private int fragmentId = R.id.nav_home;

    /* 底部导航栏的单击事件 */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // 获取到 点击的item的ItemId
            int itemId = item.getItemId();
            // 判断点击的是哪个id
            return setFragment(itemId);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //根据传入的Bundle对象判断Activity是正常启动还是销毁重建
        if(savedInstanceState == null){
            //设置第一个Fragment默认选中
            setFragment(fragmentId);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //通过onSaveInstanceState方法保存当前显示的fragment
        outState.putInt("fragment_id",fragmentId);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        //通过FragmentManager获取保存在FragmentTransaction中的Fragment实例
        mHomeFragment = mFragmentManager
                .findFragmentByTag("Home_fragment");
        mFileloadFragment = mFragmentManager
                .findFragmentByTag("Fileload_fragment");
        mMeFragment = mFragmentManager
                .findFragmentByTag("me_fragment");
        //恢复销毁前显示的Fragment
        setFragment(savedInstanceState.getInt("fragment_id"));
    }

    /**
     *  隐藏不需要显示的Activity
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction){
        if(mHomeFragment != null){
            //隐藏Fragment
            transaction.hide(mHomeFragment);
        }
        if(mFileloadFragment != null){
            transaction.hide(mFileloadFragment);

        }
        if(mMeFragment != null){
            transaction.hide(mMeFragment);

        }
    }

    /**
     *  开启了 Fragment管理器，从传递过来的itemid判断需要显示哪个页面
     * @param itemId
     * @return
     */
    private boolean setFragment(int itemId){
        //获取Fragment管理器
        FragmentManager mFragmentManager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction mTransaction = mFragmentManager.beginTransaction();
        //隐藏所有Fragment
        hideFragments(mTransaction);
        switch (itemId) {
            case R.id.nav_home:
                //显示对应Fragment
                if(mHomeFragment == null){
                    mHomeFragment = new HomeActivity();
                    mTransaction.add(R.id.frameLa, mHomeFragment,
                            "Home_fragment");
                }else {
                    mTransaction.show(mHomeFragment);
                }
                //提交事务
                mTransaction.commit();
                return true;

            case R.id.nav_file_upload:
                //显示对应Fragment
                if(mFileloadFragment == null){
                    mFileloadFragment = new FileUploadActivity();
                    mTransaction.add(R.id.frameLa, mFileloadFragment,
                            "Home_fragment");
                }else {
                    mTransaction.show(mFileloadFragment);
                }
                //提交事务
                mTransaction.commit();
                return true;

            case R.id.nav_wode:
                //显示对应Fragment
                if(mMeFragment == null){
                    mMeFragment = new MeActivity();
                    mTransaction.add(R.id.frameLa, mMeFragment,
                            "Home_fragment");
                }else {
                    mTransaction.show(mMeFragment);
                }
                //提交事务
                mTransaction.commit();
                return true;
        }
        return false;
    }
}
