package com.bawei.gekaixuan.bean;

import com.google.gson.Gson;

/**
 * Created by 葛凯旋 on 2017/9/13.
 */

public class LoginBean extends  Basebean{

    /**
     * code : 400
     * datas : {"error":"登录失败"}
     */

    private DatasBean datas;

    public static LoginBean objectFromData(String str) {

        return new Gson().fromJson(str, LoginBean.class);
    }


    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * error : 登录失败
         */

        private String error;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
