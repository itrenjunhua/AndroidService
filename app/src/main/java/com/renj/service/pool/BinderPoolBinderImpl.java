package com.renj.service.pool;

import android.os.IBinder;
import android.os.RemoteException;

import com.renj.service.aidl.IBinderPoolBinder;
import com.renj.service.utils.Logger;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-10   15:57
 * <p>
 * 描述：继承 IBinderPoolBinder.Stub 类的Binder
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BinderPoolBinderImpl extends IBinderPoolBinder.Stub {
    public final static int BINDER_TYPE_BOOK = 0;
    public final static int BINDER_TYPE_PERSON = 1;
    public final static int BINDER_TYPE_STRING = 2;

    private RemoteBookBinderImpl remoteBookBinder;
    private RemotePersonBinderImpl remotePersonBinder;
    private RemoteStringBinderImpl remoteStringBinder;

    @Override
    public IBinder getBinder(int type) throws RemoteException {
        Logger.i("BinderPoolBinderImpl type: " + type + currentThread());
        switch (type) {
            case BINDER_TYPE_BOOK:
                if (remoteBookBinder == null)
                    remoteBookBinder = new RemoteBookBinderImpl();
                return remoteBookBinder;
            case BINDER_TYPE_PERSON:
                if (remotePersonBinder == null)
                    remotePersonBinder = new RemotePersonBinderImpl();
                return remotePersonBinder;
            case BINDER_TYPE_STRING:
                if (remoteStringBinder == null)
                    remoteStringBinder = new RemoteStringBinderImpl();
                return remoteStringBinder;
        }
        return null;
    }

    private String currentThread() {
        return " ,Thread Name： " + Thread.currentThread().getName();
    }
}
