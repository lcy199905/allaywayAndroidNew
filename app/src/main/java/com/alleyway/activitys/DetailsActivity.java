package com.alleyway.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alleyway.BaseActivity;
import com.alleyway.R;
import com.alleyway.pojo.Work;
import com.alleyway.utils.JsonUtils;
import com.alleyway.utils.SendHttpRequestUtils;

import java.util.Map;

public class DetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String url = "work/getWorkList?work_type=2&page_num=1&page_size=&show_page_num=&label_type=&return_type=1";
        String jsonData = null;
        try {
            jsonData = SendHttpRequestUtils.sendHttpRequest(url, true);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("jsonData:" + jsonData);

    }
}
