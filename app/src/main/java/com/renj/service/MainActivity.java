package com.renj.service;


import android.widget.Button;

import com.renj.service.local.LocalServiceActivity;

public class MainActivity extends BaseActivity {
    private Button btLocalService;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btLocalService = findViewById(R.id.bt_local_service);
    }

    @Override
    protected void initListener() {
        btLocalService.setOnClickListener(v -> startActivity(LocalServiceActivity.class));
    }

    @Override
    protected void initPageData() {

    }
}