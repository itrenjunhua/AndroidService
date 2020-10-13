package com.renj.service.pool;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.renj.service.aidl.IBinderPoolBinder;
import com.renj.service.utils.ToastUtils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-10   15:37
 * <p>
 * 描述：
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BinderPoolManager {
    private volatile static BinderPoolManager instance;
    private Context applicationContext;

    // 绑定池aidl
    private IBinderPoolBinder iBinderPoolBinder;
    // ServiceConnection 对象
    private ServiceConnection bindConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinderPoolBinder = IBinderPoolBinder.Stub.asInterface(service);

            // 增加死亡代理
            try {
                service.linkToDeath(recipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    // 死亡代理
    private IBinder.DeathRecipient recipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (iBinderPoolBinder == null) return;

            iBinderPoolBinder.asBinder().unlinkToDeath(recipient, 0);
            iBinderPoolBinder = null;
            bindService();
        }
    };

    private BinderPoolManager(Context context) {
        applicationContext = context;
    }

    public static BinderPoolManager getInstance(Context context) {
        if (instance == null) {
            synchronized (BinderPoolManager.class) {
                if (instance == null) {
                    instance = new BinderPoolManager(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    // 绑定服务
    public void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.renj.remote.pool");
        intent.setPackage("com.renj.service");
        applicationContext.bindService(intent, bindConnection, Service.BIND_AUTO_CREATE);
    }

    // 绑定服务
    public void unBindService() {
        if (iBinderPoolBinder == null) {
            ToastUtils.showToast("服务未绑定或已解绑");
            return;
        }
        if (recipient != null)
            iBinderPoolBinder.asBinder().unlinkToDeath(recipient, 0);

        applicationContext.unbindService(bindConnection);
        iBinderPoolBinder = null;
    }

    /**
     * 根据类型返回 IBinder
     *
     * @param binderType 需要的Binder类型
     * @return
     */
    public IBinder getBinder(int binderType) {
        if (iBinderPoolBinder != null) {
            try {
                return iBinderPoolBinder.getBinder(binderType);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
