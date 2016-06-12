package com.simpleweater.tools.db;

import android.app.Application;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.x;
/**
 * Created by yszsyf on 16/6/12.
 */
public class db_province extends Application {

    private DbManager.DaoConfig daoConfig;
    public DbManager.DaoConfig getDaoConfig() {
        return daoConfig;
    }

    protected void or(){
        x.Ext.init(this);//Xutils初始化
        daoConfig = new DbManager.DaoConfig()
                .setDbName("lyj_db")//创建数据库的名称
                .setDbVersion(1)//数据库版本号
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        // db.addColumn(...);
                        // db.dropTable(...);
                        // ...
                    }
                });//数据库更新操作
    }
}
