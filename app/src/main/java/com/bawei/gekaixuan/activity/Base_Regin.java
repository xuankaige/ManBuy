package com.bawei.gekaixuan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bawei.gekaixuan.application.MyApp;
import com.bawei.gekaixuan.net.HttpUtil;

/**
 * Created by 葛凯旋 on 2017/9/13.
 */

public class Base_Regin extends AppCompatActivity {
    protected MyApp myApp;
    protected HttpUtil httpUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApp = (MyApp) getApplication();
        httpUtil = myApp.getHttpUtil();
    }
}
