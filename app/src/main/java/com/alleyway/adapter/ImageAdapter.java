package com.alleyway.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alleyway.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class ImageAdapter extends ArrayAdapter<String> {

    private int layoutId;
    private String url = "http://www.cyhfwq.top/designForum2/images/work/";

    public ImageAdapter(Context context, int layoutId, List<String> list, String url){
        super(context, layoutId, list);
        this.layoutId = layoutId;
        this.url += url + "/";
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }else {
            view = convertView;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.image_adapter);

        String imageUrl = url + (position + 1) + "_compress.jpg";
        Glide.with(getContext()).load(imageUrl).into(imageView);

        return view;

    }
}
