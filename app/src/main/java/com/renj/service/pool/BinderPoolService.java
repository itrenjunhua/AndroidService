package com.renj.service.pool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;
import com.renj.service.utils.ToastUtils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-10   15:45
 * <p>
 * 描述：AIDL binder池服务
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BinderPoolService extends Service {
    public static final String SERVICE_NAME = BinderPoolService.class.getSimpleName();

    private BinderPoolBinderImpl binderPoolBinder;

    @Override
    public void onCreate() {
        Logger.i(SERVICE_NAME + " onCreate()" + currentProgressAndThread());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        binderPoolBinder = new BinderPoolBinderImpl();
        Logger.i(SERVICE_NAME + " onBind()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 绑定");
        return binderPoolBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.i(SERVICE_NAME + " onUnbind()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 解绑");
        return super.onUnbind(intent);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Logger.i(SERVICE_NAME + " onStart()" + currentProgressAndThread());
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i(SERVICE_NAME + " onStartCommand()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 启动");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.i(SERVICE_NAME + " onDestroy()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 停止");
        super.onDestroy();
    }

    private String currentProgressAndThread() {
        return " ,Progress Name: " + ProgressUtils.getProcessName(this) + " ,Thread Name： " + Thread.currentThread().getName();
    }
}
