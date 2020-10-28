package com.renj.service.callback;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.renj.service.aidl.callback.ICallBack;
import com.renj.service.aidl.callback.ICallBackBinder;
import com.renj.service.bean.BookBean;
import com.renj.service.remote.RemoteBinderService;
import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;
import com.renj.service.utils.RandomUtils;
import com.renj.service.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-14   14:07
 * <p>
 * 描述：使用 RemoteCallbackList 注册回调
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RemoteCallbackListService extends Service {
    public static final String SERVICE_NAME = RemoteBinderService.class.getSimpleName();

    private RemoteCallbackList<ICallBack> remoteCallbackList = new RemoteCallbackList<>();
    private CallBackBinderImpl callBackBinder;

    private Timer timer;
    private TimerTask timerTask;

    @Override
    public void onCreate() {
        Logger.i(SERVICE_NAME + " onCreate()" + currentProgressAndThread());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        callBackBinder = new CallBackBinderImpl();
        Logger.i(SERVICE_NAME + " onBind()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 绑定");
        // 定时产生书本信息
        createBookInfo();
        return callBackBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.i(SERVICE_NAME + " onUnbind()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 解绑");
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
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

    private void createBookInfo() {
        if (timer == null) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    int nextInt = RandomUtils.randomInt(10000);
                    BookBean bookBean = new BookBean("书名-" + nextInt, "作者-" + nextInt,
                            (RandomUtils.randomInt(10000) + 10000) / 100d);

                    Logger.i(SERVICE_NAME + " Create BookInfo: " + bookBean + currentProgressAndThread());

                    remoteCallbackList.beginBroadcast();
                    int registeredCallbackCount = remoteCallbackList.getRegisteredCallbackCount();
                    for (int i = 0; i < registeredCallbackCount; i++) {
                        ICallBack callBack = remoteCallbackList.getBroadcastItem(i);
                        try {
                            callBack.callBack(bookBean);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    remoteCallbackList.finishBroadcast();
                }
            };
        }
        timer.schedule(timerTask, 0, 5000);
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

    private class CallBackBinderImpl extends ICallBackBinder.Stub {
        @Override
        public void registerCallBack(ICallBack callBack) throws RemoteException {
            if (callBack != null) {
                remoteCallbackList.register(callBack);
            }
        }

        @Override
        public void unregisterCallBack(ICallBack callBack) throws RemoteException {
            if (callBack != null) {
                remoteCallbackList.unregister(callBack);
            }
        }
    }
}
