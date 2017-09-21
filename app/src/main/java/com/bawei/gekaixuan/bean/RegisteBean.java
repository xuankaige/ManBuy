package com.bawei.gekaixuan.bean;

import com.google.gson.Gson;

/**
 * Created by 葛凯旋 on 2017/9/13.
 */

public class RegisteBean extends Basebean {

    /**
     * code : 400
     * datas : {"error":"请填写用户名"}
     */


    private DatasBean datas;

    public static RegisteBean objectFromData(String str) {

        return new Gson().fromJson(str, RegisteBean.class);
    }





    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * error : 请填写用户名
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
