package clwater.study_xutils;

import android.app.Application;
import android.os.Bundle;
import org.xutils.x;

/**
 * Created by yszsyf on 16/5/31.
 */
public class BaseApplication extends Application {

    public void onCreate() {
        super.onCreate();
        // 初始化
        x.Ext.init(this);
        // 设置是否输出debug
        x.Ext.setDebug(BuildConfig.DEBUG);
    }

}
