package com.bawei.gekaixuan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.activity.User_Login;

/**
 * Created by 葛凯旋 on 2017/9/1.
 */

public class UserFragment extends Fragment implements View.OnClickListener {

    private ImageView userhead;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user, container, false);
        userhead = view.findViewById(R.id.userhead);
        userhead.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(), User_Login.class);
        startActivity(intent);
    }
}
