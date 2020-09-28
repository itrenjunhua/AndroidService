package com.renj.service.local;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.renj.service.utils.ToastUtils;
import com.renj.service.utils.Logger;

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
        Logger.i("LocalStartService onCreate()");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Logger.i("LocalStartService onStart()");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i("LocalStartService onStartCommand()");
        ToastUtils.showToast("本地服务 \"" + SERVICE_NAME + "\" 启动");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.i("LocalStartService onDestroy()");
        ToastUtils.showToast("本地服务 \"" + SERVICE_NAME + "\" 停止");
        super.onDestroy();
    }
}
