package com.renj.service;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-27   17:54
 * <p>
 * 描述：Activity基类
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initListener();
        initPageData();
    }

    protected <T> void startActivity(Class<T> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected void initListener() {

    }

    protected abstract void initPageData();

}
