package com.renj.service.aidl.callback;

import com.renj.service.aidl.callback.IBookCallBack;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-14   14:24
 * <p>
 * 描述：注册回调binder，将 IBookCallBack 注册到 RemoteCallbackList 的方式，类似观察者模式
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
interface IBookCallBackBinder {
    void registerCallBack(IBookCallBack callBack);
    void unregisterCallBack(IBookCallBack callBack);
}
