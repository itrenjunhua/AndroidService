package com.renj.service.remote;

import android.content.Intent;
import android.widget.Button;

import com.renj.service.BaseActivity;
import com.renj.service.R;
import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;

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

    private Button btStartBindStart;
    private Button btStartBindBind;
    private Button btStartBindAdd;
    private Button btStartBindGet;
    private Button btStartBindUnbind;
    private Button btStartBindStop;

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
        btStartBindStart = findViewById(R.id.bt_start_bind_start);
        btStartBindBind = findViewById(R.id.bt_start_bind_bind);
        btStartBindAdd = findViewById(R.id.bt_start_bind_add);
        btStartBindGet = findViewById(R.id.bt_start_bind_get);
        btStartBindUnbind = findViewById(R.id.bt_start_bind_unbind);
        btStartBindStop = findViewById(R.id.bt_start_bind_stop);
    }

    @Override
    protected void initListener() {
        // --------------------- 开启服务  --------------------- //
        // 开启服务
        btRemoteStart.setOnClickListener(v -> {
            Logger.i("点击开启服务" + currentProgressAndThread());
            Intent intent = new Intent(this, RemoteStartService.class);
            startService(intent);
        });

        // 停止服务
        btRemoteStop.setOnClickListener(v -> {
            Intent intent = new Intent(this, RemoteStartService.class);
            stopService(intent);
        });
    }

    @Override
    protected void initPageData() {

    }

    private String currentProgressAndThread() {
        return " ,Progress Name: " + ProgressUtils.getProcessName(this) + " ,Thread Name： " + Thread.currentThread().getName();
    }
}
