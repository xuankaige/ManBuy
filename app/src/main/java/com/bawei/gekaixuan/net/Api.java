package com.bawei.gekaixuan.net;

/**
 * Created by peng on 2017/9/1.
 */

public interface Api {
    public static boolean isOnline = false;
    public static final String PRODUCT = "http://www.baidu.com";
    public static final String DEVELOP = "http://169.254.11.137";
    public static final String HOST = isOnline ? PRODUCT : DEVELOP;


    public static final String MAIN_PAGE = HOST + "/mobile/index.php?act=index";//首页
    public static final String FENLEI_left = HOST + "/mobile/index.php?act=goods_class";//分类页左侧
    public static final String FENLEI_right = HOST + "/mobile/index.php?act=goods_class&gc_id=";//分类右侧二级列表
    public static final String PRODUCT_LIST = HOST + "/mobile/index.php?act=goods&op=goods_list&page=100&gc_id=";//首页
    public static final String PRODUCT_DETAIL = HOST + "/mobile/index.php?act=goods&op=goods_detail&goods_id=";//详情
    public static final String USER_REGISTER = HOST + "/mobile/index.php?act=login&op=register";//注册
    public static final String USER_LOGIN = HOST + "/mobile/index.php?act=login";//登录
}