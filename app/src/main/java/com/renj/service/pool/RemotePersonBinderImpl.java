package com.renj.service.pool;

import android.os.RemoteException;

import com.renj.service.aidl.IRemotePersonBinder;
import com.renj.service.bean.PersonBean;
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
 * 描述：继承 IRemotePersonBinder.Stub 类的Binder
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RemotePersonBinderImpl extends IRemotePersonBinder.Stub {
    private List<PersonBean> personBeans = new ArrayList<>();

    @Override
    public void addPersonIn(PersonBean personBean) throws RemoteException {
        Logger.i("addPersonIn() ,PersonBean: " + personBean.toString() + currentThread());
        if (StringUtils.isEmpty(personBean.userName)) {
            int nextInt = RandomUtils.randomInt(10000);
            personBean.userName = "(Reset)用户名-" + nextInt;
            personBean.age = RandomUtils.randomInt(80) + 19;
            personBean.sex = RandomUtils.randomInt(2);
            Logger.i("addPersonIn() ,Reset PersonBean: " + personBean.toString() + currentThread());
        }
        personBeans.add(personBean);
    }

    @Override
    public void addPersonOut(PersonBean personBean) throws RemoteException {
        Logger.i("addPersonOut() ,PersonBean: " + personBean.toString() + currentThread());
        if (StringUtils.isEmpty(personBean.userName)) {
            int nextInt = RandomUtils.randomInt(10000);
            personBean.userName = "(Reset)用户名-" + nextInt;
            personBean.age = RandomUtils.randomInt(80) + 19;
            personBean.sex = RandomUtils.randomInt(2);
            Logger.i("addPersonOut() ,Reset PersonBean: " + personBean.toString() + currentThread());
        }
        personBeans.add(personBean);
    }

    @Override
    public void addPersonInOut(PersonBean personBean) throws RemoteException {
        Logger.i("addPersonInOut() ,PersonBean: " + personBean.toString() + currentThread());
        if (StringUtils.isEmpty(personBean.userName)) {
            int nextInt = RandomUtils.randomInt(10000);
            personBean.userName = "(Reset)用户名-" + nextInt;
            personBean.age = RandomUtils.randomInt(80) + 19;
            personBean.sex = RandomUtils.randomInt(2);
            Logger.i("addPersonInOut() ,Reset PersonBean: " + personBean.toString() + currentThread());
        }
        personBeans.add(personBean);
    }

    @Override
    public List<PersonBean> getPersonList() throws RemoteException {
        return personBeans;
    }

    private String currentThread() {
        return " ,Thread Name： " + Thread.currentThread().getName();
    }
}
