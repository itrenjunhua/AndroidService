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
 * 描述：本地绑定服务
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class LocalBinderService extends Service {
    private final String SERVICE_NAME = LocalBinderService.class.getSimpleName();
    private LocalBinderImpl localBinder = new LocalBinderImpl();
    private List<BookBean> bookBeanList;

    @Override
    public void onCreate() {
        Logger.i("LocalBinderService onCreate()");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.i("LocalBinderService onBind()");
        ToastUtils.showToast("本地绑定服务 \"" + SERVICE_NAME + "\" 绑定");
        return localBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.i("LocalBinderService onUnbind()");
        ToastUtils.showToast("本地绑定服务 \"" + SERVICE_NAME + "\" 解绑");
        return super.onUnbind(intent);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Logger.i("LocalBinderService onStart()");
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i("LocalBinderService onStartCommand()");
        ToastUtils.showToast("本地绑定服务 \"" + SERVICE_NAME + "\" 启动");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.i("LocalBinderService onDestroy()");
        ToastUtils.showToast("本地绑定服务 \"" + SERVICE_NAME + "\" 停止");
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
                Logger.i("LocalBinderService addBook: " + bookBean.toString());
            }
        }

        @Override
        public List<BookBean> getBookList() {
            return bookBeanList;
        }
    }
}
