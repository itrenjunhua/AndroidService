package com.renj.service.local;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.renj.service.bean.BookBean;
import com.renj.service.utils.Logger;
import com.renj.service.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-28   09:59
 * <p>
 * 描述：本地开启并且绑定服务
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class LocalStartAndBinderService extends Service {
    public static final String SERVICE_NAME = LocalStartAndBinderService.class.getSimpleName();
    private LocalBinderImpl localBinder = new LocalBinderImpl();
    private List<BookBean> bookBeanList;

    @Override
    public void onCreate() {
        Logger.i(SERVICE_NAME + " onCreate()");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.i(SERVICE_NAME + " onBind()");
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 绑定");
        return localBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Logger.i(SERVICE_NAME + " onRebind()");
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 重新绑定");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.i(SERVICE_NAME + " onUnbind()");
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 解绑");
        // return super.onUnbind(intent);
        // 返回true，当服务未销毁并重新调用 bindService() 时回调 onRebind() 和 ServiceConnection 的 onServiceConnected() 方法；
        // 否则，只会回调 ServiceConnection 的 onServiceConnected() 方法。
        return true;
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
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.i(SERVICE_NAME + " onDestroy()");
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 停止");
        super.onDestroy();
    }

    public class LocalBinderImpl extends Binder implements ILocalBinder {

        @Override
        public void addBook(BookBean bookBean) {
            if (bookBeanList == null) {
                bookBeanList = new ArrayList<>();
            }
            if (bookBean != null) {
                bookBeanList.add(bookBean);
                Logger.i(SERVICE_NAME + " addBook: " + bookBean.toString());
                ToastUtils.showToast(SERVICE_NAME + ": " + bookBean.bookName);
            }
        }

        @Override
        public List<BookBean> getBookList() {
            return bookBeanList;
        }
    }
}
