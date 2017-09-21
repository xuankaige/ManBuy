package com.bawei.gekaixuan.application;

import android.app.Application;

import com.bawei.gekaixuan.net.HttpUtil;


/**
 * Created by peng on 2017/9/1.
 */

public class MyApp extends Application {


  private HttpUtil httpUtil;
    @Override
    public void onCreate() {
        super.onCreate();
        httpUtil = HttpUtil.getHttpUtil();
    }

    public HttpUtil getHttpUtil() {
        return httpUtil;
    }
}
