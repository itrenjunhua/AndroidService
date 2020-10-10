package com.renj.service.aidl;

import com.renj.service.bean.PersonBean;

import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-28   10:01
 * <p>
 * 描述：远程绑定服务方法定义
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
interface IRemoteStringBinder {
    void addString(String value);

    List<String> getStringList();
}
