package com.alleyway.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alleyway.R;
import com.alleyway.pojo.MyCollect;
import com.alleyway.pojo.Paging;
import com.alleyway.pojo.Work;
import com.alleyway.utils.JsonUtils;
import com.alleyway.utils.SendHttpRequestUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyCollectActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private int page_num = 0;
    private ListView work_list_item;
    private Work myCollect;
    private List<Work> works = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        getSupportActionBar().hide();

        work_list_item=findViewById(R.id.my_collcet_work_list_item);
        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
        Set<String> production_collect = settings.getStringSet("production_collect", null);

        //获取json
        if (production_collect!=null){
            for (String s : production_collect) {
                try {
                    String res = SendHttpRequestUtils.sendHttpRequest("work/getWorkId?work_id="+s, true);
                    Log.e("ABC",res);
                    myCollect = (Work) JsonUtils.jsonToClass(res,Work.class);
                    Log.e("123",myCollect.toString());
                    works.add(myCollect);
                    Log.e("666",works.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // 准备BaseAdapter对象
        MyAdapter adapter = new MyAdapter();

        // 设置Adapter显示列表
        work_list_item.setAdapter(adapter);

        // 给列表设置点击监听
        work_list_item.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 获取当前点击的作品的id值
        int work_id=((Work)works.get(position)).getId();
//
//        SharedPreferences sp=getActivity().getSharedPreferences("work", Context.MODE_PRIVATE);
//        sp.edit().putString("work_id", String.valueOf(work_id));
//        sp.edit().commit();
        Intent intent = new Intent(MyCollectActivity.this,DetailsActivity.class);
        intent.putExtra("work_id",work_id);
        startActivity(intent);
    }

    //ListView的赋值
    class MyAdapter extends BaseAdapter {
        String headUrl = "http://www.cyhfwq.top/designForum2/images/head/";
        String workUrl = "http://www.cyhfwq.top/designForum2/images/work/";

        // 返回集合数据的数量
        public int getCount() {
            return works.size();
        }

        // 返回指定下标对应的数据对象
        public Object getItem(int i) {
            return works.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        /**
         * 返回指定下标所对应的item的View对象
         *
         * @param i         下标
         * @param view      可复用的缓存Item视图对象
         * @param viewGroup ListView对象
         * @return
         */
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                // 加载item的布局,得到View对象
                view = View.inflate(MyCollectActivity.this, R.layout.home_list, null);
            }
            // 根据下标参数设置对应的数据
            // 得到当前行的数据对象
            Work work = (Work) works.get(i);
            // 得到子View对象
            ImageView homeUserHeadd = view.findViewById(R.id.home_user_head); // 头像
            ImageView homeWorkImage = view.findViewById(R.id.home_work_image); // 头像
            TextView homeUserName = view.findViewById(R.id.home_user_name); // 昵称
            TextView homeWorkText = view.findViewById(R.id.home_work_text);   // 作品内容
            TextView homeWorkDiscussSize = view.findViewById(R.id.home_work_discuss_size);   // 评论数量
            TextView homeWorkLikeSize = view.findViewById(R.id.home_work_like_size); // 点赞数量
            TextView homeWorkCollect = view.findViewById(R.id.home_work_collect); // 收藏数量
            // 设置数据
            Glide.with(MyCollectActivity.this).load(headUrl + work.getUserHead()).into(homeUserHeadd);
            StringBuffer image = new StringBuffer(work.getWorkImageList().get(0));
            image.insert(image.indexOf("."), "_compress");
            image.insert(0, work.getPath() + "/");

            Glide.with(MyCollectActivity.this).load(workUrl + image.toString()).into(homeWorkImage);
            homeUserName.setText(work.getUserName());
            homeWorkText.setText(work.getWorkText());
            homeWorkDiscussSize.setText(String.valueOf(work.getWorkDiscussSize()));
            homeWorkLikeSize.setText(String.valueOf(work.getWorkLikeSize()));
            homeWorkCollect.setText(String.valueOf(work.getWorkCollect()));
            return view;
        }
    }
}
