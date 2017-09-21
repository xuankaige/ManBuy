package com.bawei.gekaixuan.net;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.bawei.gekaixuan.bean.Basebean;
import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

/**
 * Created by peng on 2017/9/1.
 */

public class HttpUtil {
    private static volatile HttpUtil httpUtil;
    private Gson gson;

    private HttpUtil() {
        gson = new Gson();
    }

    public static HttpUtil getHttpUtil() {
        if (httpUtil == null) {
            synchronized (HttpUtil.class) {
                if (httpUtil == null) {
                    httpUtil = new HttpUtil();
                }
            }
        }
        return httpUtil;
    }

    /**
     * post 请求
     *
     * @param url
     * @param params
     * @param clazz
     * @param onNetListener
     */
    public void post(final Context context, String url, Map<String, String> params, final Class clazz, final OnNetListener onNetListener) {
        if (TextUtils.isEmpty(url)) {
            throw new RuntimeException("url is null!!!");
        }
        PostFormBuilder postFormBuilder = OkHttpUtils
                .post()
                .url(url);
        //拼接参数
        for (Map.Entry<String, String> entry : params.entrySet()) {
            postFormBuilder.addParams(entry.getKey(), entry.getValue());
        }

        postFormBuilder
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        showErrorInfo(context);
                    }

                    @Override
                    public void onResponse(String response) {
                        dealWithResponse(context, response, clazz, onNetListener);
                    }
                });
    }

    /**
     * get 请求
     *
     * @param url
     * @param clazz
     * @param onNetListener
     */
    public void get(final Context context, String url, final Class clazz, final OnNetListener onNetListener) {
        if (TextUtils.isEmpty(url)) {
            throw new RuntimeException("url is null!!!");
        }
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        showErrorInfo(context);
                    }

                    @Override
                    public void onResponse(String response) {
                        dealWithResponse(context, response, clazz, onNetListener);
                    }
                });
    }

    /**
     * 处理返回的数据
     *
     * @param response
     * @param clazz
     * @param onNetListener
     */
    private void dealWithResponse(Context context, String response, Class clazz, OnNetListener onNetListener) {
        if (!TextUtils.isEmpty(response)) {
            Basebean basebean = (Basebean) gson.fromJson(response, clazz);
            if (basebean.getCode().equals("400")) {
                //提示错误信息
                showErrorInfo(context);
            } else {
                //返回正确结果
                if (onNetListener != null) {
                    onNetListener.onSuccess(basebean);
                }
            }
        }
    }

    private void showErrorInfo(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("请求数据有误，请稍后再试！");
        builder.setPositiveButton("确定", null);
        builder.setNegativeButton("取消", null);
        builder.show();
    }
}
