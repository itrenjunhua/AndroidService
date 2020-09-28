package com.renj.service.utils;

import android.widget.Toast;

import com.renj.service.MyApplication;

/**
 * Created by xsw on 2016/10/25.
 */
public class ToastUtils {

    private static Toast mToast;

    /**
     * @param msg 显示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(MyApplication.getApplication(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

}
