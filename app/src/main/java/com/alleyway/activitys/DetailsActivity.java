package com.alleyway.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alleyway.BaseActivity;
import com.alleyway.R;
import com.alleyway.adapter.ImageAdapter;
import com.alleyway.pojo.Work;
import com.alleyway.utils.JsonUtils;
import com.alleyway.utils.SendHttpRequestUtils;
import com.bumptech.glide.Glide;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends BaseActivity {

    private static final String URL = "work/getWorkId";

    private TextView workTitleText;
    private TextView timeDateText;
    private TextView userNameText;
    private ImageView headImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        int work_id = intent.getIntExtra("work_id", 102);

        System.out.println("work_id = " + work_id);

        try {
            init(String.valueOf(work_id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(String id) throws Exception {
        // 设置页面标题
        initNavBar(true, "详情");
        // 获取数据
        Work work = getInitData(id);

        System.out.println(work.toString());

        // 标题
        workTitleText = (TextView) findViewById(R.id.details_work_title);
        workTitleText.setText(work.getWorkText());
        // 作品创建时间
        timeDateText = (TextView) findViewById(R.id.details_work_time_data);
        timeDateText.setText(work.getWorkTime());
        // 用户昵称
        userNameText = (TextView) findViewById(R.id.details_user_name);
        userNameText.setText(work.getUserName());
        // 用户头像
        headImg = (ImageView) findViewById(R.id.details_user_head_img);
        Glide.with(DetailsActivity.this).load("http://www.cyhfwq.top/designForum2/images/head/" + work.getUserHead()).into(headImg);


        // 配置Adapter
        ImageAdapter imageAdapter = new ImageAdapter(DetailsActivity.this, R.layout.details_image_adapter, work.getWorkImageList(), work.getPath());

        ListView listView = (ListView) findViewById(R.id.details_list_view);
        listView.setAdapter(imageAdapter);

    }


    private Work getInitData(String id) throws Exception {
        // 获取json数据
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("work_id", id);
        String jsonStr = SendHttpRequestUtils.sendHttpRequest(URL, paraMap, true);
        System.out.println(jsonStr);
        // 将json数据封装成work对象
        Work work = (Work)JsonUtils.jsonToClass(jsonStr, Work.class);
        return work;
    }
}
