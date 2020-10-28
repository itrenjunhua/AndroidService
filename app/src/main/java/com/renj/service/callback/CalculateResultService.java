package com.renj.service.callback;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.renj.service.aidl.callback.ICalculateResultBinder;
import com.renj.service.aidl.callback.ICalculateResultCallBack;
import com.renj.service.utils.Logger;
import com.renj.service.utils.ProgressUtils;
import com.renj.service.utils.ToastUtils;

import java.math.BigDecimal;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-28   10:09
 * <p>
 * 描述：服务器计算结果，通过参数回调给客户端
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class CalculateResultService extends Service {
    public static final String SERVICE_NAME = CalculateResultService.class.getSimpleName();
    private CalculateResultBinderImpl iCalculateResultBinder;

    @Override
    public void onCreate() {
        Logger.i(SERVICE_NAME + " onCreate()" + currentProgressAndThread());
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        iCalculateResultBinder = new CalculateResultBinderImpl();
        Logger.i(SERVICE_NAME + " onBind()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 绑定");
        return iCalculateResultBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Logger.i(SERVICE_NAME + " onUnbind()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 解绑");
        return super.onUnbind(intent);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Logger.i(SERVICE_NAME + " onStart()" + currentProgressAndThread());
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i(SERVICE_NAME + " onStartCommand()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 启动");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.i(SERVICE_NAME + " onDestroy()" + currentProgressAndThread());
        ToastUtils.showToast("\"" + SERVICE_NAME + "\" 停止");
        super.onDestroy();
    }

    private String currentProgressAndThread() {
        return " ,Progress Name: " + ProgressUtils.getProcessName(this) + " ,Thread Name： " + Thread.currentThread().getName();
    }

    private class CalculateResultBinderImpl extends ICalculateResultBinder.Stub {

        @Override
        public void operatorAdd(double num1, double num2, ICalculateResultCallBack iCalculateResultCallBack) throws RemoteException {
            BigDecimal bigDecimal1 = BigDecimal.valueOf(num1);
            BigDecimal bigDecimal2 = BigDecimal.valueOf(num2);
            BigDecimal result = bigDecimal1.add(bigDecimal2);
            Logger.i(SERVICE_NAME + " num1: " + num1 + " num2: " + num2 + " operatorAdd() result: " + result + currentProgressAndThread());
            if (iCalculateResultCallBack != null)
                iCalculateResultCallBack.result(result.doubleValue());
        }

        @Override
        public void operatorSubtract(double num1, double num2, ICalculateResultCallBack iCalculateResultCallBack) throws RemoteException {
            BigDecimal bigDecimal1 = BigDecimal.valueOf(num1);
            BigDecimal bigDecimal2 = BigDecimal.valueOf(num2);
            BigDecimal result = bigDecimal1.subtract(bigDecimal2);
            Logger.i(SERVICE_NAME + " num1: " + num1 + " num2: " + num2 + " operatorSubtract() result: " + result + currentProgressAndThread());
            if (iCalculateResultCallBack != null)
                iCalculateResultCallBack.result(result.doubleValue());
        }

        @Override
        public void operatorMultiply(double num1, double num2, ICalculateResultCallBack iCalculateResultCallBack) throws RemoteException {
            BigDecimal bigDecimal1 = BigDecimal.valueOf(num1);
            BigDecimal bigDecimal2 = BigDecimal.valueOf(num2);
            BigDecimal result = bigDecimal1.multiply(bigDecimal2);
            Logger.i(SERVICE_NAME + " num1: " + num1 + " num2: " + num2 + " operatorMultiply() result: " + result + currentProgressAndThread());
            if (iCalculateResultCallBack != null)
                iCalculateResultCallBack.result(result.doubleValue());
        }

        @Override
        public void operatorDivide(double num1, double num2, ICalculateResultCallBack iCalculateResultCallBack) throws RemoteException {
            if (num2 == 0) {
                Logger.i(SERVICE_NAME + " num1: " + num1 + " num2: " + num2 + " operatorDivide() Division by zero" + currentProgressAndThread());
                throw new ArithmeticException("Division by zero");
            }
            BigDecimal bigDecimal1 = BigDecimal.valueOf(num1);
            BigDecimal bigDecimal2 = BigDecimal.valueOf(num2);
            BigDecimal result = bigDecimal1.divide(bigDecimal2, 2, BigDecimal.ROUND_HALF_UP);
            Logger.i(SERVICE_NAME + " num1: " + num1 + " num2: " + num2 + " operatorDivide() result: " + result + currentProgressAndThread());
            if (iCalculateResultCallBack != null)
                iCalculateResultCallBack.result(result.doubleValue());
        }
    }
}
