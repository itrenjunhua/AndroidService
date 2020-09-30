package com.renj.service.local;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.renj.service.R;
import com.renj.service.utils.Logger;
import com.renj.service.utils.NotificationUtils;
import com.renj.service.utils.ToastUtils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-27   17:44
 * <p>
 * 描述：开启本地前台服务
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class LocalForeService extends Service {
    private final String SERVICE_NAME = LocalForeService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Logger.i(SERVICE_NAME + " onCreate()");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Logger.i(SERVICE_NAME + " onStart()");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i(SERVICE_NAME + " onStartCommand()");
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 启动");

        NotificationUtils.ChannelInfo channelInfo = new NotificationUtils.ChannelInfo("1", "Service");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationUtils.getInstance(this).addChannel(channelInfo, false);
        }
        Notification notification = NotificationUtils
                .getInstance(this)
                .getNotification(this, 1, channelInfo, R.mipmap.ic_launcher,
                        "Service Start", "服务", "前台服务运行", null);
        startForeground(1, notification);

        // 停止前台服务
        // stopForeground(true);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.i(SERVICE_NAME + " onDestroy()");
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 停止");
        super.onDestroy();
    }
}
