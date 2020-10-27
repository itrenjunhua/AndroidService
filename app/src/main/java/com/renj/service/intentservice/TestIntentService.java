package com.renj.service.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;
import com.renj.service.utils.ToastUtils;

import java.util.Set;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-27   13:51
 * <p>
 * 描述：IntentService 使用
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class TestIntentService extends IntentService {
    public static final String SERVICE_NAME = TestIntentService.class.getSimpleName();

    public TestIntentService() {
        // onHandleIntent() 运行的线程名
        super("Test IntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Logger.i(SERVICE_NAME + " onHandleIntent()" + currentProgressAndThread());
        Logger.i(SERVICE_NAME + " ------------ onHandleIntent Intent Extras Info ------------ ");
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Set<String> keys = extras.keySet();
                for (String key : keys) {
                    Object value = extras.get(key);
                    Logger.i(SERVICE_NAME + " key: " + key + "  value: " + value);

                    // 每打印一条信息，休眠1秒钟，因为 onHandleIntent 运行在子线程，不会阻塞线程
                    SystemClock.sleep(1000);
                }
            }
        }
    }

    @Override
    public void onCreate() {
        Logger.i(SERVICE_NAME + " onCreate()" + currentProgressAndThread());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.i(SERVICE_NAME + " onBind()" + currentProgressAndThread());
        return null;
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
