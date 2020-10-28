package com.renj.service.callback;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.TextView;

import com.renj.service.BaseActivity;
import com.renj.service.R;
import com.renj.service.aidl.callback.ICallBack;
import com.renj.service.aidl.callback.ICallBackBinder;
import com.renj.service.bean.BookBean;
import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;
import com.renj.service.utils.ToastUtils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-14   15:47
 * <p>
 * 描述：使用 RemoteCallbackList 注册回调
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RemoteCallbackListActivity extends BaseActivity {
    private Button btCallbackBind;
    private Button btCallbackRegister;
    private Button btCallbackUnRegister;
    private Button btCallbackUnBind;
    private TextView tvContent;

    // 绑定服务连接对象
    private ICallBackBinder iCallBackBinder;
    private ServiceConnection bindConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Logger.i(RemoteCallbackListService.SERVICE_NAME + " onServiceConnected()" + currentProgressAndThread());
            iCallBackBinder = ICallBackBinder.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.i(RemoteCallbackListService.SERVICE_NAME + " onServiceDisconnected()" + currentProgressAndThread());
            iCallBackBinder = null;
        }
    };

    private ICallBack iCallBack = new ICallBack.Stub() {

        @Override
        public void callBack(BookBean bookBean) throws RemoteException {
            Logger.i("Update BookBean  " + bookBean.toString() + currentProgressAndThread());
            runOnUiThread(() -> {
                String content = tvContent.getText().toString().trim();
                tvContent.setText(content + "\n" + bookBean.toString());
            });
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.remote_callback_list_activity;
    }

    @Override
    protected void initView() {
        btCallbackBind = findViewById(R.id.bt_callback_bind);
        btCallbackRegister = findViewById(R.id.bt_callback_register);
        btCallbackUnRegister = findViewById(R.id.bt_callback_unregister);
        btCallbackUnBind = findViewById(R.id.bt_callback_unbind);
        tvContent = findViewById(R.id.tv_content);
    }

    @Override
    protected void initListener() {
        // 绑定服务
        btCallbackBind.setOnClickListener(v -> {
            Logger.i("Click Binder Service  " + currentProgressAndThread());
            Intent intent = new Intent();
            intent.setAction("com.renj.remote.remote");
            intent.setPackage("com.renj.service");
            bindService(intent, bindConnection, Service.BIND_AUTO_CREATE);
        });

        // 注册监听
        btCallbackRegister.setOnClickListener(v -> {
            if (iCallBackBinder == null) {
                ToastUtils.showToast("服务未绑定或已解绑");
                return;
            }

            Logger.i("Click Register CallBack  " + currentProgressAndThread());
            try {
                iCallBackBinder.registerCallBack(iCallBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        // 取消监听
        btCallbackUnRegister.setOnClickListener(v -> {
            if (iCallBackBinder == null) {
                ToastUtils.showToast("服务未绑定或已解绑");
                return;
            }

            Logger.i("Click UnRegister CallBack  " + currentProgressAndThread());
            try {
                iCallBackBinder.unregisterCallBack(iCallBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        // 解绑服务
        btCallbackUnBind.setOnClickListener(v -> {
            if (iCallBackBinder == null) {
                ToastUtils.showToast("服务未绑定或已解绑");
                return;
            }

            Logger.i("Click UnBinder Service  " + currentProgressAndThread());
            Intent intent = new Intent();
            intent.setAction("com.renj.remote.remote");
            intent.setPackage("com.renj.service");
            unbindService(bindConnection);
            iCallBackBinder = null;
        });
    }

    @Override
    protected void initPageData() {

    }

    private String currentProgressAndThread() {
        return " ,Progress Name: " + ProgressUtils.getProcessName(this) + " ,Thread Name： " + Thread.currentThread().getName();
    }
}
