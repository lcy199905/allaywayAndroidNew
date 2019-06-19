package com.alleyway.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alleyway.R;
import com.bumptech.glide.Glide;

public class MeActivity extends Fragment{

    private View view;
    String headUrl = "http://www.cyhfwq.top/designForum2/images/head/";
    String workUrl = "http://www.cyhfwq.top/designForum2/images/work/";

    public MeActivity(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_me, container, false);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedPreferences settings = getActivity().getSharedPreferences("UserInfo", 0);
        String userId = settings.getString("userId", "");
        String userPhone = settings.getString("userPhone", "");
        String userHead = settings.getString("userHead", "");
        String userName = settings.getString("userName", "");

        if ("".equals(userId)){
            /**
             * 点击头像登录
             */
            View touxiang = view.findViewById(R.id.me_touxiang);
            touxiang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            /**
             * 设置头像
             */
            ImageView touxiang = view.findViewById(R.id.me_touxiang);
            Glide.with(getActivity()).load(headUrl +userHead).into(touxiang);
            /**
             * 设置昵称
             */
            TextView me_user_Name = view.findViewById(R.id.me_user_Name);
            me_user_Name.setText(userName);
        }




        /**
         * 我的作品
         */
        View me_item_01 = view.findViewById(R.id.me_item_01);
        me_item_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(userId)){
                    Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(),MyWorksActivity.class);
                    startActivity(intent);
                }
            }
        });

        /**
         * 我的收藏
         */
        View me_item_02 = view.findViewById(R.id.me_item_02);
        me_item_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(userId)){
                    Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), MyCollectActivity.class);
                    startActivity(intent);
                }
            }
        });

        /**
         * 我的点赞
         */
        View me_item_03 = view.findViewById(R.id.me_item_03);
        me_item_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(userId)){
                    Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), MyLikesActivity.class);
                    startActivity(intent);
                }
            }
        });

        /**
         * 我的设置
         */
        /*View me_item_04 = view.findViewById(R.id.me_item_04);
        me_item_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "我的设置", Toast.LENGTH_SHORT).show();
            }
        });*/

        /**
         * 退出账号
         */
        View me_item_05 = view.findViewById(R.id.me_item_05);
        me_item_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"".equals(userId)){
                    /**
                     * 设置头像
                     */
                    ImageView touxiang = view.findViewById(R.id.me_touxiang);
                    touxiang.setImageResource(R.drawable.head);
                    /**
                     * 设置昵称
                     */
                    TextView me_user_Name = view.findViewById(R.id.me_user_Name);
                    me_user_Name.setText("请点击头像登录");
                    /**
                     * 点击头像登录
                     */
                    View tou2 = view.findViewById(R.id.me_touxiang);
                    tou2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent  = new Intent(getActivity(),LoginActivity.class);
                            startActivity(intent);
                        }
                    });

                    /**
                     * 清空账号信息
                     */
                    SharedPreferences.Editor editor = settings.edit();
                    editor.clear();
                    editor.commit();

                    Toast.makeText(getActivity(), "已退出账号", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
