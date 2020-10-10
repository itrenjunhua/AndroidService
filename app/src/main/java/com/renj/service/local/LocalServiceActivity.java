package com.renj.service.local;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.widget.Button;

import com.renj.service.BaseActivity;
import com.renj.service.R;
import com.renj.service.bean.BookBean;
import com.renj.service.utils.ListUtils;
import com.renj.service.utils.Logger;
import com.renj.service.utils.RandomUtils;
import com.renj.service.utils.ToastUtils;

import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-27   17:54
 * <p>
 * 描述：本地服务
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class LocalServiceActivity extends BaseActivity {
    private Button btStartService;
    private Button btStopService;

    private Button btBindService;
    private Button btAddBook;
    private Button btGetBook;
    private Button btUnBindService;

    private Button btStartBindStart;
    private Button btStartBindBind;
    private Button btStartBindAdd;
    private Button btStartBindGet;
    private Button btStartBindUnbind;
    private Button btStartBindStop;

    private Button btStartFore;
    private Button btStopFore;


    // 绑定服务连接对象
    private ILocalBinder iLocalBinder;
    private ServiceConnection bindConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.i(LocalBinderService.SERVICE_NAME + " onServiceConnected()");
            iLocalBinder = (ILocalBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.i(LocalBinderService.SERVICE_NAME + " onServiceDisconnected()");
            iLocalBinder = null;
        }
    };

    // 开启并绑定服务连接对象
    private ILocalBinder startBindLocalBinder;
    private ServiceConnection startBindConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.i(LocalStartAndBinderService.SERVICE_NAME + " onServiceConnected()");
            startBindLocalBinder = (ILocalBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.i(LocalStartAndBinderService.SERVICE_NAME + " onServiceDisconnected()");
            startBindLocalBinder = null;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.local_start_activity;
    }

    @Override
    protected void initView() {
        btStartService = findViewById(R.id.bt_local_start);
        btStopService = findViewById(R.id.bt_local_stop);

        btBindService = findViewById(R.id.bt_local_bind);
        btAddBook = findViewById(R.id.bt_add_book);
        btGetBook = findViewById(R.id.bt_get_book);
        btUnBindService = findViewById(R.id.bt_local_unbind);

        btStartBindStart = findViewById(R.id.bt_start_bind_start);
        btStartBindBind = findViewById(R.id.bt_start_bind_bind);
        btStartBindAdd = findViewById(R.id.bt_start_bind_add);
        btStartBindGet = findViewById(R.id.bt_start_bind_get);
        btStartBindUnbind = findViewById(R.id.bt_start_bind_unbind);
        btStartBindStop = findViewById(R.id.bt_start_bind_stop);

        btStartFore = findViewById(R.id.bt_fore_start);
        btStopFore = findViewById(R.id.bt_fore_stop);
    }

    @Override
    protected void initListener() {
        // --------------------- 开启服务  --------------------- //
        // 开启服务
        btStartService.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocalStartService.class);
            startService(intent);
        });

        // 停止服务
        btStopService.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocalStartService.class);
            stopService(intent);
        });

        // --------------------- 绑定服务  --------------------- //
        // 绑定服务
        btBindService.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocalBinderService.class);
            bindService(intent, bindConnection, Service.BIND_AUTO_CREATE);
        });

        // 增加数据
        btAddBook.setOnClickListener(v -> {
            if (iLocalBinder == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }

            int nextInt = RandomUtils.randomInt(10000);
            iLocalBinder.addBook(new BookBean("书名-" + nextInt, "作者-" + nextInt,
                    (RandomUtils.randomInt(10000) + 10000) / 100d));
        });

        // 获取数据
        btGetBook.setOnClickListener(v -> {
            if (iLocalBinder == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }

            List<BookBean> bookList = iLocalBinder.getBookList();
            if (ListUtils.notEmpty(bookList)) {
                ToastUtils.showToast("书本总数: " + bookList.size());
                Logger.i("\n-------- 书本总数: " + bookList.size() + " ------------");
                for (BookBean bookBean : bookList) {
                    Logger.i("BookList => " + bookBean);
                }
                Logger.i("-----------------------------------");
            } else {
                ToastUtils.showToast("没有数据");
            }
        });

        // 解绑服务
        btUnBindService.setOnClickListener(v -> {
            if (iLocalBinder == null) {
                ToastUtils.showToast("服务未绑定或已解绑");
                return;
            }
            unbindService(bindConnection);
            iLocalBinder = null;
        });

        // --------------------- 开启并绑定服务  --------------------- //
        // 开启服务
        btStartBindStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocalStartAndBinderService.class);
            startService(intent);
        });

        // 绑定服务
        btStartBindBind.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocalStartAndBinderService.class);
            bindService(intent, startBindConnection, Service.BIND_AUTO_CREATE);
        });

        // 增加数据
        btStartBindAdd.setOnClickListener(v -> {
            if (startBindLocalBinder == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }

            int nextInt = RandomUtils.randomInt(10000);
            startBindLocalBinder.addBook(new BookBean("书名-" + nextInt, "作者-" + nextInt,
                    (RandomUtils.randomInt(10000) + 10000) / 100d));
        });

        // 获取数据
        btStartBindGet.setOnClickListener(v -> {
            if (startBindLocalBinder == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }

            List<BookBean> bookList = startBindLocalBinder.getBookList();
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
        btStartBindUnbind.setOnClickListener(v -> {
            if (startBindLocalBinder == null) {
                ToastUtils.showToast("服务未绑定或已解绑");
                return;
            }
            unbindService(startBindConnection);
            startBindLocalBinder = null;
        });

        // 停止服务
        btStartBindStop.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocalStartAndBinderService.class);
            stopService(intent);
        });

        // --------------------- 前台服务  --------------------- //
        // 开启服务
        btStartFore.setOnClickListener(v -> {
            // 需要申请 android.permission.FOREGROUND_SERVICE 权限
            Intent intent = new Intent(this, LocalForeService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }
        });

        // 停止服务
        btStopFore.setOnClickListener(v -> {
            Intent intent = new Intent(this, LocalForeService.class);
            stopService(intent);
        });
    }

    @Override
    protected void initPageData() {

    }
}
