package com.renj.service.aidl.callback;

import com.renj.service.aidl.callback.ICallBack;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-14   14:24
 * <p>
 * 描述：注册回调binder
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
interface ICallBackBinder {
    void registerCallBack(ICallBack callBack);
    void unregisterCallBack(ICallBack callBack);
}
