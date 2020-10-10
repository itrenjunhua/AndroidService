package com.renj.service.pool;

import android.os.RemoteException;

import com.renj.service.aidl.IRemoteStringBinder;
import com.renj.service.utils.Logger;
import com.renj.service.utils.RandomUtils;
import com.renj.service.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-10   15:59
 * <p>
 * 描述：继承 IRemoteStringBinder.Stub 类的Binder
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RemoteStringBinderImpl extends IRemoteStringBinder.Stub {
    private List<String> strings = new ArrayList<>();

    @Override
    public void addString(String value) throws RemoteException {
        Logger.i("addString() ,value: " + value + currentThread());
        if (StringUtils.isEmpty(value)) {
            int nextInt = RandomUtils.randomInt(10000);
            value = "(Reset)RandomValue-" + nextInt;
            Logger.i("addString() ,Reset value: " + value + currentThread());
        }
        strings.add(value);
    }

    @Override
    public List<String> getStringList() throws RemoteException {
        return strings;
    }

    private String currentThread() {
        return " ,Thread Name： " + Thread.currentThread().getName();
    }
}
