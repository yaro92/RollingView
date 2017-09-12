package com.ylye.rollingview;

import android.app.Application;

/**
 * Created by Administrator on 2017/8/15.
 */

public class MyApplication extends Application {

    private static MyApplication myApplication = null;

    public static MyApplication getApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }
}
