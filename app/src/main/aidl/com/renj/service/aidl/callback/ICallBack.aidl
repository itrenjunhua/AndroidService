package com.renj.service.aidl.callback;

import com.renj.service.bean.BookBean;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-14   14:21
 * <p>
 * 描述：回调对象
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
interface ICallBack {
    void callBack(in BookBean bookBean);
}
