package com.renj.service.local;

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
 * 创建时间：2020-09-27   17:44
 * <p>
 * 描述：开启本地服务
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class LocalStartService extends Service {
    private final String SERVICE_NAME = LocalStartService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Logger.i(SERVICE_NAME + " onCreate()" + currentProgressAndThread());
        super.onCreate();
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
