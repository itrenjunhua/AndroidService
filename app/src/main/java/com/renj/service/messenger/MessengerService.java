package com.renj.service.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;
import com.renj.service.utils.ToastUtils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-26   15:22
 * <p>
 * 描述：Messenger 服务端代码
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class MessengerService extends Service {
    public static final String SERVICE_NAME = MessengerService.class.getSimpleName();

    private Messenger serviceMessenger;
    private Messenger clientMessenger;
    private Handler messageServiceHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            // 获取客户端传递过来的 Messenger
            clientMessenger = msg.replyTo;
            Logger.i(SERVICE_NAME + "  what: " + msg.what + " content: " + msg.obj + " onBind()" + currentProgressAndThread());
            switch (msg.what) {
                case MessengerWhatConstant.MESSENGER_CLIENT_SEND:
                    ToastUtils.showToast("收到客户端信息： " + msg.obj);
                    break;
                case MessengerWhatConstant.MESSENGER_CLIENT_SEND_WAIT:
                    ToastUtils.showToast("收到客户端信息： " + msg.obj);
                    Message message = Message.obtain();
                    message.what = MessengerWhatConstant.MESSENGER_SERVICE_SEND;
                    message.obj = "你的信息 \"" + msg.obj + "\" 已收到，现告知于你。";
                    try {
                        clientMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        Logger.i(SERVICE_NAME + " onCreate()" + currentProgressAndThread());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (serviceMessenger == null)
            serviceMessenger = new Messenger(messageServiceHandler);
        return serviceMessenger.getBinder();
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
