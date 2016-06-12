package com.simpleweater.base;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.DbManager;
import org.xutils.x;

/**
 * Created by yszsyf on 16/5/31.
 */
public class BaseApplication extends Application {

    private DbManager.DaoConfig daoConfig;
    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    public void onCreate() {
        super.onCreate();
        // 初始化
        x.Ext.init(this);
        // 设置是否输出debug
        x.Ext.setDebug(BuildConfig.DEBUG);

        daoConfig = new DbManager.DaoConfig()
                .setDbName("simpleweather")//创建数据库的名称
                .setDbVersion(1)//数据库版本号
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                    }
                });
    }

}
