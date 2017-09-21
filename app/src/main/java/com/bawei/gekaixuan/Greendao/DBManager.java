package com.bawei.gekaixuan.Greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by 葛凯旋 on 2017/9/15.
 */

public class DBManager {
    private final static String dbName = "test_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }
    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }
    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }
    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(shopcartdaobean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        shopcartdaobeanDao userDao = daoSession.getShopcartdaobeanDao();
        userDao.insert(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<shopcartdaobean> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        shopcartdaobeanDao userDao = daoSession.getShopcartdaobeanDao();
        userDao.insertInTx(users);
    }
    /**
     * 删除一条记录
     *
     * @param user
     */
    public void deleteUser(shopcartdaobean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        shopcartdaobeanDao userDao = daoSession.getShopcartdaobeanDao();
        userDao.delete(user);
    }
    /**
     * 更新一条记录
     *
     * @param user
     */
    public void updateUser(shopcartdaobean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        shopcartdaobeanDao userDao = daoSession.getShopcartdaobeanDao();
        userDao.update(user);
    }
    /**
     * 查询用户列表
     */
    public List<shopcartdaobean> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        shopcartdaobeanDao userDao = daoSession.getShopcartdaobeanDao();
        QueryBuilder<shopcartdaobean> qb = userDao.queryBuilder();
        List<shopcartdaobean> list = qb.list();
        return list;
    }

    /**
     * 查询用户列表
     */
    public List<shopcartdaobean> queryUserList(long age) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        shopcartdaobeanDao userDao = daoSession.getShopcartdaobeanDao();
        QueryBuilder<shopcartdaobean> qb = userDao.queryBuilder();
        qb.where(shopcartdaobeanDao.Properties.Id.gt(age)).orderAsc(shopcartdaobeanDao.Properties.Id);
        List<shopcartdaobean> list = qb.list();
        return list;
    }
}

