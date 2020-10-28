package com.renj.service.messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.renj.service.BaseActivity;
import com.renj.service.R;
import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;
import com.renj.service.utils.ToastUtils;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-26   16:02
 * <p>
 * 描述：Messenger 操作页面
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class MessengerActivity extends BaseActivity {
    private Button btMessengerBind;
    private Button btMessengerSend;
    private Button btMessengerSend2;
    private Button btMessengerUnbind;
    private TextView tvContent;

    private Messenger serviceMessenger;
    private Messenger clientMessenger;
    private Handler clientServiceHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            Logger.i("MessengerActivity  what: " + msg.what + " content: " + msg.obj + " onBind()" + currentProgressAndThread());
            switch (msg.what) {
                case MessengerWhatConstant.MESSENGER_SERVICE_SEND:
                    String oldContent = tvContent.getText().toString();
                    tvContent.setText(oldContent + "\n服务端返回信息： " + msg.obj);
                    break;
            }
        }
    };
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            serviceMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceMessenger = null;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.messenger_activity;
    }

    @Override
    protected void initView() {
        btMessengerBind = findViewById(R.id.bt_messenger_bind);
        btMessengerSend = findViewById(R.id.bt_messenger_send);
        btMessengerSend2 = findViewById(R.id.bt_messenger_send2);
        btMessengerUnbind = findViewById(R.id.bt_messenger_unbind);
        tvContent = findViewById(R.id.tv_content);
    }

    @Override
    protected void initListener() {
        // 绑定
        btMessengerBind.setOnClickListener(v -> {
            Intent intent = new Intent(this, MessengerService.class);
            bindService(intent, conn, Context.BIND_AUTO_CREATE);
            tvContent.setText("服务已绑定");
        });

        // 发送数据到服务器
        btMessengerSend.setOnClickListener(v -> {
            if (serviceMessenger == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }

            if (clientMessenger == null)
                clientMessenger = new Messenger(clientServiceHandler);
            Message message = Message.obtain();
            // 将客户端的 Messenger 传递给服务端，用于服务端向客户端发送数据
            // message.replyTo = clientMessenger; // 不需要服务端返回，可以不用
            message.what = MessengerWhatConstant.MESSENGER_CLIENT_SEND;
            message.obj = "我是客户端数据";
            try {
                serviceMessenger.send(message);
                tvContent.setText("客户端发送数据： " + message.obj);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        // 发送数据到服务器并接收服务器的返回
        btMessengerSend2.setOnClickListener(v -> {
            if (serviceMessenger == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }

            if (clientMessenger == null)
                clientMessenger = new Messenger(clientServiceHandler);
            Message message = Message.obtain();
            // 将客户端的 Messenger 传递给服务端，用于服务端向客户端发送数据
            message.replyTo = clientMessenger;
            message.what = MessengerWhatConstant.MESSENGER_CLIENT_SEND_WAIT;
            message.obj = "我是客户端数据，收到请告知。";
            try {
                serviceMessenger.send(message);
                tvContent.setText("客户端发送数据： " + message.obj);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        // 解绑
        btMessengerUnbind.setOnClickListener(v -> {
            if (serviceMessenger == null) {
                ToastUtils.showToast("服务未绑定或已解绑");
                return;
            }
            unbindService(conn);
            serviceMessenger = null;
            tvContent.setText("服务未绑定");
        });
    }

    @Override
    protected void initPageData() {
    }

    private String currentProgressAndThread() {
        return " ,Progress Name: " + ProgressUtils.getProcessName(this) + " ,Thread Name： " + Thread.currentThread().getName();
    }
}
