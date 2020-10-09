package com.renj.service;

import android.app.Application;

import com.renj.service.utils.Logger;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-27   17:50
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class MyApplication extends Application {
    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Logger.fullClassName(-1);
    }

    public static Application getApplication() {
        return application;
    }
}
