package com.bawei.gekaixuan.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by 葛凯旋 on 2017/9/3.
 */

public class TitleBean extends  Basebean{


    private DatasBean datas;

    public static TitleBean objectFromData(String str) {

        return new Gson().fromJson(str, TitleBean.class);
    }




    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        private List<ClassListBean> class_list;

        public static DatasBean objectFromData(String str) {

            return new Gson().fromJson(str, DatasBean.class);
        }

        public List<ClassListBean> getClass_list() {
            return class_list;
        }

        public void setClass_list(List<ClassListBean> class_list) {
            this.class_list = class_list;
        }

        public static class ClassListBean {
            /**
             * gc_id : 4
                    * gc_name : 女装
             */

            private String gc_id;
            private String gc_name;

            public static ClassListBean objectFromData(String str) {

                return new Gson().fromJson(str, ClassListBean.class);
            }

            public String getGc_id() {
                return gc_id;
            }

            public void setGc_id(String gc_id) {
                this.gc_id = gc_id;
            }

            public String getGc_name() {
                return gc_name;
            }

            public void setGc_name(String gc_name) {
                this.gc_name = gc_name;
            }
        }
    }
}
