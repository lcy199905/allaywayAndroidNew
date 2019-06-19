package com.alleyway.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.alleyway.R;
import com.alleyway.utils.BitmapUtil;
import com.alleyway.utils.SendHttpRequestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

public class FileUploadActivity extends Fragment {


    private static final String TAG = "FileUploadActivity";
    EditText editText1;
    GridView gridView1;
    Button btnCommit;
    Button btnBack;
    private final int IMAGE_OPEN = 1;        //打开图片标记
    private String pathImage;                //选择图片路径
    private Bitmap bmp;                      //导入临时图片
    private ArrayList<HashMap<String, Object>> imageItem;
    private SimpleAdapter simpleAdapter;
    private List<String> imgList;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private String url;

    public FileUploadActivity() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_file_upload, container, false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gridView1 = getView().findViewById(R.id.gridView1);
        editText1 = getView().findViewById(R.id.editText1);
        btnCommit = getView().findViewById(R.id.button1);
        btnBack = getView().findViewById(R.id.button2);
        // 获取到全局数据中的用户Id
        SharedPreferences userSettings = getActivity().getSharedPreferences("UserInfo", 0);
        String userId = userSettings.getString("userId", "null");
        String editText = editText1.getText().toString();
        url = "work/addWork?workType=1&workLables=1&userId" + userId + "&workText=" + editText + "&";

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userId == null) {
                    Toast.makeText(getActivity(), "你还未登陆，请先登录", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    if (imgList == null) {
                        Toast.makeText(getActivity(), "你还未选择发布的作品", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        MultipartBody.Builder builder = new MultipartBody.Builder()
                                .setType(MultipartBody.FORM);
                        StringBuilder sb = new StringBuilder();
                        /*sb.append();*/
                        for (String imgPath : imgList) {
                            Bitmap bitmap = BitmapUtil.getSmallBitmap(imgPath, 90, 90);
                            String base64Path = BitmapUtil.bitmapToString(bitmap);
                            sb.append("imgFile" + base64Path + "&");
                        }
                        String sbNew = sb.toString().substring(0, sb.length() - 1);
                        String sendURL = url + sbNew;
                        Log.e(TAG, "onClick: SendURL " + sendURL);
                        try {
                            String resultJSON = SendHttpRequestUtils.sendHttpRequest(sendURL, false);
                            Log.e(TAG, "onClick: resultJSON" + resultJSON);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


        //获取资源图片加号
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.jiahao);
        imageItem = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("itemImage", bmp);
        imageItem.add(map);
        simpleAdapter = new SimpleAdapter(getActivity(),
                imageItem, R.layout.griditem_addpic,
                new String[]{"itemImage"}, new int[]{R.id.imageView1});

        /** HashMap载入bmp图片在GridView中不显示,但是如果载入资源ID能显示 如
         * map.put("itemImage", R.drawable.img);
         * 解决方法:
         *              1.自定义继承BaseAdapter实现
         *              2.ViewBinder()接口实现
         *  参考 http://blog.csdn.net/admin_/article/details/7257901*/


        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data,
                                        String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    ImageView i = (ImageView) view;
                    i.setImageBitmap((Bitmap) data);
                    return true;
                }
                return false;
            }
        });
        gridView1.setAdapter(simpleAdapter);

        /** 监听GridView点击事件
         * 报错:该函数必须抽象方法 故需要手动导入import android.view.View;*/


        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (imageItem.size() == 10) { //第一张为默认图片
                    Toast.makeText(getActivity(), "图片数9张已满", Toast.LENGTH_SHORT).show();
                } else if (position == 0) { //点击图片位置为+ 0对应0张图片
                    Toast.makeText(getActivity(), "添加图片", Toast.LENGTH_SHORT).show();
                    //选择图片
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, IMAGE_OPEN);
                    //通过onResume()刷新数据
                } else {
                    dialog(position);
                    //Toast.makeText(MainActivity.this, "点击第"+(position + 1)+" 号图片",
                    //      Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //刷新图片
    @Override
    public void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(pathImage)) {
            Bitmap addbmp = BitmapFactory.decodeFile(pathImage);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", addbmp);
            imageItem.add(map);
            simpleAdapter = new SimpleAdapter(getActivity(),
                    imageItem, R.layout.griditem_addpic,
                    new String[]{"itemImage"}, new int[]{R.id.imageView1});
            simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                @Override
                public boolean setViewValue(View view, Object data,
                                            String textRepresentation) {
                    // TODO Auto-generated method stub
                    if (view instanceof ImageView && data instanceof Bitmap) {
                        ImageView i = (ImageView) view;
                        i.setImageBitmap((Bitmap) data);
                        return true;
                    }
                    return false;
                }
            });
            gridView1.setAdapter(simpleAdapter);
            simpleAdapter.notifyDataSetChanged();
            //刷新后释放防止手机休眠后自动添加
            pathImage = null;
        }
    }


    //获取图片路径 响应startActivityForResult
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //打开图片
        if (resultCode == RESULT_OK && requestCode == IMAGE_OPEN) {
            Uri uri = data.getData();
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                //查询选择图片
                Cursor cursor = getContext().getContentResolver().query(
                        uri,
                        new String[]{MediaStore.Images.Media.DATA},
                        null,
                        null,
                        null);
                //返回 没找到选择图片
                if (null == cursor) {
                    return;
                }
                //光标移动至开头 获取图片路径
                cursor.moveToFirst();
                pathImage = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                if (imgList == null) {
                    imgList = new ArrayList<>();
                    imgList.add(pathImage);
                } else {
                    imgList.add(pathImage);
                }
            }
        }  //end if 打开图片
    }


    /**
     * Dialog对话框提示用户删除操作
     * position为删除图片位置
     */

    protected void dialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("确认移除已添加图片吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imageItem.remove(position);
                simpleAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}         /**
 * 防止键盘挡住输入框
 * 不希望遮挡设置activity属性 android:windowSoftInputMode="adjustPan"
 * 希望动态调整高度 android:windowSoftInputMode="adjustResize"
 */


//        view.getWindow().setSoftInputMode(WindowManager.LayoutParams.
//                SOFT_INPUT_ADJUST_PAN);
//            //锁定屏幕
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        setContentView(R.layout.activity_main);

