package com.renj.service.remote;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.renj.service.aidl.IRemoteBookBinder;
import com.renj.service.bean.BookBean;
import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;
import com.renj.service.utils.StringUtils;
import com.renj.service.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-09   09:54
 * <p>
 * 描述：远程绑定服务
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RemoteBinderService extends Service {
    public static final String SERVICE_NAME = RemoteBinderService.class.getSimpleName();

    private RemoteBookBinderImpl remoteBookBinder;

    @Override
    public void onCreate() {
        Logger.i(SERVICE_NAME + " onCreate()" + currentProgressAndThread());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        remoteBookBinder = new RemoteBookBinderImpl();
        Logger.i(SERVICE_NAME + " onBind()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 绑定");
        return remoteBookBinder;
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

    public class RemoteBookBinderImpl extends IRemoteBookBinder.Stub {
        private Random random = new Random();
        private List<BookBean> bookBeans = new ArrayList<>();

        @Override
        public void addBookIn(BookBean bookBean) throws RemoteException {
            Logger.i(SERVICE_NAME + " addBookIn() ,BookBean: " + bookBean.toString() + currentProgressAndThread());
            if (StringUtils.isEmpty(bookBean.bookName)) {
                int nextInt = random.nextInt(10000);
                bookBean.bookName = "(Reset)书名-" + nextInt;
                bookBean.bookAuthor = "作者-" + nextInt;
                bookBean.bookPrice = (random.nextInt(10000) + 10000) / 100d;
                Logger.i(SERVICE_NAME + " addBookIn() ,Reset BookBean: " + bookBean.toString() + currentProgressAndThread());
            }
            bookBeans.add(bookBean);
        }

        @Override
        public void addBookOut(BookBean bookBean) throws RemoteException {
            Logger.i(SERVICE_NAME + " addBookOut() ,BookBean: " + bookBean.toString() + currentProgressAndThread());
            if (StringUtils.isEmpty(bookBean.bookName)) {
                int nextInt = random.nextInt(10000);
                bookBean.bookName = "(Reset)书名-" + nextInt;
                bookBean.bookAuthor = "作者-" + nextInt;
                bookBean.bookPrice = (random.nextInt(10000) + 10000) / 100d;
                Logger.i(SERVICE_NAME + " addBookOut() ,Reset BookBean: " + bookBean.toString() + currentProgressAndThread());
            }
            bookBeans.add(bookBean);
        }

        @Override
        public void addBookInOut(BookBean bookBean) throws RemoteException {
            Logger.i(SERVICE_NAME + " addBookInOut() ,BookBean: " + bookBean.toString() + currentProgressAndThread());
            if (StringUtils.isEmpty(bookBean.bookName)) {
                int nextInt = random.nextInt(10000);
                bookBean.bookName = "(Reset)书名-" + nextInt;
                bookBean.bookAuthor = "作者-" + nextInt;
                bookBean.bookPrice = (random.nextInt(10000) + 10000) / 100d;
                Logger.i(SERVICE_NAME + " addBookInOut() ,Reset BookBean: " + bookBean.toString() + currentProgressAndThread());
            }
            bookBeans.add(bookBean);
        }

        @Override
        public List<BookBean> getBookList() throws RemoteException {
            return bookBeans;
        }
    }
}
