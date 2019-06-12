package com.alleyway.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alleyway.R;

public class HomeActivity extends Fragment {

    private ListView work_list_item;

    public HomeActivity(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        work_list_item=getActivity().findViewById(R.id.work_list_item);

        return inflater.inflate(R.layout.activity_home, container ,false);

    }



}
