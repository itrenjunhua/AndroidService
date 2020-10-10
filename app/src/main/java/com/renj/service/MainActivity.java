package com.renj.service;


import android.widget.Button;

import com.renj.service.local.LocalServiceActivity;
import com.renj.service.pool.BinderPoolActivity;
import com.renj.service.remote.RemoteServiceActivity;

public class MainActivity extends BaseActivity {
    private Button btLocalService;
    private Button btRemoteService;
    private Button btBinderPool;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btLocalService = findViewById(R.id.bt_local_service);
        btRemoteService = findViewById(R.id.bt_remote_service);
        btBinderPool = findViewById(R.id.bt_binder_pool);
    }

    @Override
    protected void initListener() {
        btLocalService.setOnClickListener(v -> startActivity(LocalServiceActivity.class));

        btRemoteService.setOnClickListener(v -> startActivity(RemoteServiceActivity.class));

        btBinderPool.setOnClickListener(v -> startActivity(BinderPoolActivity.class));
    }

    @Override
    protected void initPageData() {

    }
}