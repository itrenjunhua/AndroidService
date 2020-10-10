package com.renj.service.remote;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;

import com.renj.service.BaseActivity;
import com.renj.service.R;
import com.renj.service.aidl.IRemoteBookBinder;
import com.renj.service.bean.BookBean;
import com.renj.service.utils.ListUtils;
import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;
import com.renj.service.utils.RandomUtils;
import com.renj.service.utils.ToastUtils;

import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-30   15:45
 * <p>
 * 描述：远程服务操作类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RemoteServiceActivity extends BaseActivity {
    private Button btRemoteStart;
    private Button btRemoteStop;

    private Button btRemoteBind;
    private Button btAddBook;
    private Button btGetBook;
    private Button btRemoteUnbind;

    // 绑定服务连接对象
    private IRemoteBookBinder iRemoteBookBinder;
    private ServiceConnection bindConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.i(RemoteBinderService.SERVICE_NAME + " onServiceConnected()" + currentProgressAndThread());
            iRemoteBookBinder = IRemoteBookBinder.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.i(RemoteBinderService.SERVICE_NAME + " onServiceDisconnected()" + currentProgressAndThread());
            iRemoteBookBinder = null;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.remote_service_activity;
    }

    @Override
    protected void initView() {
        btRemoteStart = findViewById(R.id.bt_remote_start);
        btRemoteStop = findViewById(R.id.bt_remote_stop);

        btRemoteBind = findViewById(R.id.bt_remote_bind);
        btAddBook = findViewById(R.id.bt_add_book);
        btGetBook = findViewById(R.id.bt_get_book);
        btRemoteUnbind = findViewById(R.id.bt_remote_unbind);
    }

    @Override
    protected void initListener() {
        // --------------------- 开启服务  --------------------- //
        // 开启服务
        btRemoteStart.setOnClickListener(v -> {
            Logger.i("Click Start Service  " + currentProgressAndThread());
            // Intent intent = new Intent(this, RemoteStartService.class);
            Intent intent = new Intent();
            intent.setAction("com.renj.remote.start");
            intent.setPackage("com.renj.service");
            startService(intent);
        });

        // 停止服务
        btRemoteStop.setOnClickListener(v -> {
            // Intent intent = new Intent(this, RemoteStartService.class);
            Intent intent = new Intent();
            intent.setAction("com.renj.remote.start");
            intent.setPackage("com.renj.service");
            stopService(intent);
        });

        // --------------------- 绑定服务  --------------------- //
        // 绑定服务
        btRemoteBind.setOnClickListener(v -> {
            Logger.i("Click Binder Service  " + currentProgressAndThread());
            Intent intent = new Intent();
            intent.setAction("com.renj.remote.binder");
            intent.setPackage("com.renj.service");
            bindService(intent, bindConnection, Service.BIND_AUTO_CREATE);
        });

        // 增加数据
        btAddBook.setOnClickListener(v -> {
            if (iRemoteBookBinder == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }

            try {
                // addBookIn
                Logger.i("-------- addBookIn() ------------------------");
                int nextIntIn = RandomUtils.randomInt(10000);
                BookBean bookBeanIn = new BookBean("书名-" + nextIntIn, "作者-" + nextIntIn,
                        (RandomUtils.randomInt(10000) + 10000) / 100d);
                Logger.i(RemoteBinderService.SERVICE_NAME + " addBookIn() before info：" + bookBeanIn.toString());
                iRemoteBookBinder.addBookIn(bookBeanIn);
                Logger.i(RemoteBinderService.SERVICE_NAME + " addBookIn() after info：" + bookBeanIn.toString());

                // addBookOut
                Logger.i("-------- addBookOut() ------------------------");
                int nextIntOut = RandomUtils.randomInt(10000);
                BookBean bookBeanOut = new BookBean("书名-" + nextIntOut, "作者-" + nextIntOut,
                        (RandomUtils.randomInt(10000) + 10000) / 100d);
                Logger.i(RemoteBinderService.SERVICE_NAME + " addBookOut() before info：" + bookBeanOut.toString());
                iRemoteBookBinder.addBookOut(bookBeanOut);
                Logger.i(RemoteBinderService.SERVICE_NAME + " addBookOut() after info：" + bookBeanOut.toString());

                // addBookInOut
                Logger.i("-------- addBookInOut() ------------------------");
                int nextIntInOut = RandomUtils.randomInt(10000);
                BookBean bookBeanInOut = new BookBean("书名-" + nextIntInOut, "作者-" + nextIntInOut,
                        (RandomUtils.randomInt(10000) + 10000) / 100d);
                Logger.i(RemoteBinderService.SERVICE_NAME + " addBookInOut() before info：" + bookBeanInOut.toString());
                iRemoteBookBinder.addBookInOut(bookBeanInOut);
                Logger.i(RemoteBinderService.SERVICE_NAME + " addBookInOut() after info：" + bookBeanInOut.toString());

                Logger.i("-------------------------------------------------");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        // 获取数据
        btGetBook.setOnClickListener(v -> {
            if (iRemoteBookBinder == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }

            List<BookBean> bookList = null;
            try {
                bookList = iRemoteBookBinder.getBookList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

            if (ListUtils.notEmpty(bookList)) {
                ToastUtils.showToast("书本总数: " + bookList.size());
                Logger.i("-------- 书本总数: " + bookList.size() + " ------------");
                for (BookBean bookBean : bookList) {
                    Logger.i("BookList => " + bookBean);
                }
                Logger.i("-----------------------------------");
            } else {
                ToastUtils.showToast("没有数据");
            }
        });

        // 解绑服务
        btRemoteUnbind.setOnClickListener(v -> {
            if (iRemoteBookBinder == null) {
                ToastUtils.showToast("服务未绑定或已解绑");
                return;
            }
            unbindService(bindConnection);
            iRemoteBookBinder = null;
        });
    }

    @Override
    protected void initPageData() {

    }

    private String currentProgressAndThread() {
        return " ,Progress Name: " + ProgressUtils.getProcessName(this) + " ,Thread Name： " + Thread.currentThread().getName();
    }
}
