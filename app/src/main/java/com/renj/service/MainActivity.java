package com.renj.service;


import android.widget.Button;

import com.renj.service.callback.CallBackActivity;
import com.renj.service.local.LocalServiceActivity;
import com.renj.service.messenger.MessengerActivity;
import com.renj.service.pool.BinderPoolActivity;
import com.renj.service.remote.RemoteServiceActivity;

public class MainActivity extends BaseActivity {
    private Button btLocalService;
    private Button btRemoteService;
    private Button btBinderPool;
    private Button btBinderCallBack;
    private Button btBinderMessenger;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btLocalService = findViewById(R.id.bt_local_service);
        btRemoteService = findViewById(R.id.bt_remote_service);
        btBinderPool = findViewById(R.id.bt_binder_pool);
        btBinderCallBack = findViewById(R.id.bt_binder_callback);
        btBinderMessenger = findViewById(R.id.bt_binder_messenger);
    }

    @Override
    protected void initListener() {
        btLocalService.setOnClickListener(v -> startActivity(LocalServiceActivity.class));

        btRemoteService.setOnClickListener(v -> startActivity(RemoteServiceActivity.class));

        btBinderPool.setOnClickListener(v -> startActivity(BinderPoolActivity.class));

        btBinderCallBack.setOnClickListener(v -> startActivity(CallBackActivity.class));

        btBinderMessenger.setOnClickListener(v -> startActivity(MessengerActivity.class));
    }

    @Override
    protected void initPageData() {

    }
}