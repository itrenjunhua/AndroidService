package com.renj.service.intentservice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.renj.service.BaseActivity;
import com.renj.service.R;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-27   13:57
 * <p>
 * 描述：IntentService 使用
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class IntentServiceActivity extends BaseActivity {
    private Button btIntentStart;
    private TextView tvContent;

    @Override
    protected int getLayoutId() {
        return R.layout.intent_service_activity;
    }

    @Override
    protected void initView() {
        btIntentStart = findViewById(R.id.bt_intent_start);
        tvContent = findViewById(R.id.tv_content);
    }

    @Override
    protected void initListener() {
        btIntentStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, TestIntentService.class);
            Bundle extras = new Bundle();
            extras.putString("userName", "张三");
            extras.putString("age", "27");
            extras.putString("sex", "男");
            extras.putString("address", "浙江省杭州市");
            intent.putExtras(extras);
            startService(intent);
        });
    }

    @Override
    protected void initPageData() {
        StringBuilder content = new StringBuilder();

        content.append("IntentService 扩展了 Service 的功能").append("\n\n")
                .append("主要包含特征：").append("\n\t")
                .append("1. 会创建独立的工作线程来处理 onHandleIntent() 方法实现的代码，无需处理多线程问题；").append("\n\t")
                .append("2. 所有请求处理完成后，IntentService 会自动停止，无需调用 stopSelf() 方法停止 Service；").append("\n\t")
                .append("3. 为 Service 的 onBind() 提供默认实现，返回 null；").append("\n\t")
                .append("4. 为 Service 的 onStartCommand() 提供默认实现，将请求 Intent 添加到队列中。").append("\n\n")
                .append("IntentService 的好处可以根据其特征来分析，主要体现在：").append("\n\t")
                .append("1. 会创建独立的工作线程处理耗时操作，无需担心阻塞主线程").append("\n\t")
                .append("2. 无需关在什么时候停止服务，当线程执行完成之后会自动停止服务");

        tvContent.setText(content.toString());
    }
}
