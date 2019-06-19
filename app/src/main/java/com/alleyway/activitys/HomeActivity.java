package com.alleyway.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alleyway.R;
import com.alleyway.pojo.Paging;
import com.alleyway.pojo.Work;
import com.alleyway.utils.JsonUtils;
import com.alleyway.utils.SendHttpRequestUtils;
import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeActivity extends Fragment implements AdapterView.OnItemClickListener {

    private int page_num = 0;
    private ListView work_list_item;
    private Paging paging;
    private List<Work> works;
    RefreshLayout refreshLayout;
    MyAdapter adapter;

    public HomeActivity(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_home, container ,false);

    }
    // 水滴
    WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        work_list_item=getActivity().findViewById(R.id.work_list_item);
        try {
            String s = SendHttpRequestUtils.sendHttpRequest("work/getWorkList?work_type=1&page_num=" + page_num++, true);
            paging = JsonUtils.jsonPaging(s,Work.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 准备BaseAdapter对象
        adapter=new MyAdapter();

        // 设置Adapter显示列表
        work_list_item.setAdapter(adapter);

        // 给列表设置点击监听
        work_list_item.setOnItemClickListener(this);




        refreshLayout = (RefreshLayout)getActivity().findViewById(R.id.refreshLayout);
        // 下拉刷新
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Message message = new Message();
                message.what = 1 ;
                mHandler.sendMessageDelayed(message,2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                List<String>  data = initDatas();
                Message message = new Message();
                message.what = 2;
//                message.obj = data ;
                mHandler.sendMessageDelayed(message,2000);
            }
        });

    }
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 1:         //刷新加载
                    /*try {
                        String s = SendHttpRequestUtils.sendHttpRequest("work/getWorkList?work_type=1&page_num=1", true);
                        page_num = 1;
                        paging = JsonUtils.jsonPaging(s,Work.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    work_list_item.invalidate();
                    refreshLayout.finishRefresh(true);*/
                    break;
                case 2:         //加载更多
                    /*if (true) {
                        Toast.makeText(getActivity(),"已经没有更多啦", Toast.LENGTH_SHORT);
                        break;
                    }
                    if (page_num == paging.getPageSize()) {
                        Toast.makeText(getActivity(),"已经没有更多啦", Toast.LENGTH_SHORT);
                        break;
                    }
                    String s = null;
                    try {
                        s = SendHttpRequestUtils.sendHttpRequest("work/getWorkList?work_type=1&page_num=" + page_num++, true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Paging paging1 = JsonUtils.jsonPaging(s,Work.class);
                    paging.getList().addAll(paging1.getList());
                    work_list_item.invalidate();
                    refreshLayout.finishLoadMore(true);*/
                    break;
            }
            return false;
        }
    });


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 获取当前点击的作品的id值
        int work_id=((Work)paging.getList().get(position)).getId();
//
//        SharedPreferences sp=getActivity().getSharedPreferences("work", Context.MODE_PRIVATE);
//        sp.edit().putString("work_id", String.valueOf(work_id));
//        sp.edit().commit();
        Intent intent = new Intent(getActivity(),DetailsActivity.class);
        intent.putExtra("work_id",work_id);
        startActivity(intent);
    }


    //ListView的赋值
    class MyAdapter extends BaseAdapter {
        String headUrl = "http://www.cyhfwq.top/designForum2/images/head/";
        String workUrl = "http://www.cyhfwq.top/designForum2/images/work/";

        // 返回集合数据的数量
        public int getCount() {
            return paging.getList().size();
        }
        // 返回指定下标对应的数据对象
        public Object getItem(int i) {
            return paging.getList().get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        /**  返回指定下标所对应的item的View对象
         *
         * @param i     下标
         * @param view  可复用的缓存Item视图对象
         * @param viewGroup ListView对象
         * @return
         */
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null) {
                // 加载item的布局,得到View对象
                view=View.inflate(getActivity(),R.layout.home_list,null);
            }
            // 根据下标参数设置对应的数据
            // 得到当前行的数据对象
            Work work= (Work) paging.getList().get(i);
            // 得到子View对象
            ImageView homeUserHeadd=view.findViewById(R.id.home_user_head); // 头像
            ImageView homeWorkImage=view.findViewById(R.id.home_work_image); // 头像
            TextView homeUserName=view.findViewById(R.id.home_user_name); // 昵称
            TextView homeWorkText=view.findViewById(R.id.home_work_text);   // 作品内容
            TextView homeWorkDiscussSize=view.findViewById(R.id.home_work_discuss_size);   // 评论数量
            TextView homeWorkLikeSize=view.findViewById(R.id.home_work_like_size); // 点赞数量
            TextView homeWorkCollect=view.findViewById(R.id.home_work_collect); // 收藏数量
            // 设置数据
            Glide.with(getActivity()).load(headUrl +work.getUserHead()).into(homeUserHeadd);
            StringBuffer image = new StringBuffer(work.getWorkImageList().get(0));
            image.insert(image.indexOf("."),"_compress");
            image.insert(0,work.getPath()+"/" );

            Glide.with(getActivity()).load(workUrl+image.toString()).into(homeWorkImage);
            homeUserName.setText(work.getUserName());
            homeWorkText.setText(work.getWorkText());
            homeWorkDiscussSize.setText(String.valueOf(work.getWorkDiscussSize()));
            homeWorkLikeSize.setText(String.valueOf(work.getWorkLikeSize()));
            homeWorkCollect.setText(String.valueOf(work.getWorkCollect()));
            return view;
        }


    }




}
