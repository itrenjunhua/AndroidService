package com.renj.service.local;

import android.content.Intent;
import android.widget.Button;

import com.renj.service.BaseActivity;
import com.renj.service.R;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-27   17:54
 * <p>
 * 描述：开启本地服务
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class LocalStartActivity extends BaseActivity {
    private Button btStartService;
    private Button btStopService;

    @Override
    protected int getLayoutId() {
        return R.layout.local_start_activity;
    }

    @Override
    protected void initView() {
        btStartService = findViewById(R.id.bt_local_start);
        btStopService = findViewById(R.id.bt_local_stop);
    }

    @Override
    protected void initListener() {
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
    }

    @Override
    protected void initPageData() {

    }
}
