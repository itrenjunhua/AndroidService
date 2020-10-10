package com.renj.service.aidl;

import android.os.IBinder;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-10   15:34
 * <p>
 * 描述：binder池
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
interface IBinderPoolBinder {
    IBinder getBinder(int type);
}