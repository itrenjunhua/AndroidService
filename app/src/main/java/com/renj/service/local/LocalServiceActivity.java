package com.renj.service.local;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.Button;

import com.renj.service.BaseActivity;
import com.renj.service.R;
import com.renj.service.bean.BookBean;
import com.renj.service.utils.ListUtils;
import com.renj.service.utils.Logger;
import com.renj.service.utils.ToastUtils;

import java.util.List;
import java.util.Random;

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

    private Random random = new Random();

    // 绑定服务连接对象
    private ILocalBinder iLocalBinder;
    private ServiceConnection bindConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.i("LocalBinderService onServiceConnected()");
            iLocalBinder = (ILocalBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.i("LocalBinderService onServiceDisconnected()");
            iLocalBinder = null;
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

            int nextInt = random.nextInt();
            iLocalBinder.addBook(new BookBean("书名-" + nextInt, "作者-" + nextInt,
                    (random.nextInt(10000) + 10000) / 100d));
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
                for (BookBean bookBean : bookList) {
                    Logger.i("BookList => " + bookBean);
                }
            } else {
                ToastUtils.showToast("没有数据");
            }
        });

        // 解绑服务
        btUnBindService.setOnClickListener(v -> {
            unbindService(bindConnection);
            iLocalBinder = null;
        });
    }

    @Override
    protected void initPageData() {

    }
}
