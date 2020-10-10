package com.renj.service.pool;

import android.os.RemoteException;

import com.renj.service.aidl.IRemoteBookBinder;
import com.renj.service.bean.BookBean;
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
 * 描述：继承 IRemoteBookBinder.Stub 类的Binder
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class RemoteBookBinderImpl extends IRemoteBookBinder.Stub {
    private List<BookBean> bookBeans = new ArrayList<>();

    @Override
    public void addBookIn(BookBean bookBean) throws RemoteException {
        Logger.i("addBookIn() ,BookBean: " + bookBean.toString() + currentThread());
        if (StringUtils.isEmpty(bookBean.bookName)) {
            int nextInt = RandomUtils.randomInt(10000);
            bookBean.bookName = "(Reset)书名-" + nextInt;
            bookBean.bookAuthor = "作者-" + nextInt;
            bookBean.bookPrice = (RandomUtils.randomInt(10000) + 10000) / 100d;
            Logger.i("addBookIn() ,Reset BookBean: " + bookBean.toString() + currentThread());
        }
        bookBeans.add(bookBean);
    }

    @Override
    public void addBookOut(BookBean bookBean) throws RemoteException {
        Logger.i("addBookOut() ,BookBean: " + bookBean.toString() + currentThread());
        if (StringUtils.isEmpty(bookBean.bookName)) {
            int nextInt = RandomUtils.randomInt(10000);
            bookBean.bookName = "(Reset)书名-" + nextInt;
            bookBean.bookAuthor = "作者-" + nextInt;
            bookBean.bookPrice = (RandomUtils.randomInt(10000) + 10000) / 100d;
            Logger.i("addBookOut() ,Reset BookBean: " + bookBean.toString() + currentThread());
        }
        bookBeans.add(bookBean);
    }

    @Override
    public void addBookInOut(BookBean bookBean) throws RemoteException {
        Logger.i("addBookInOut() ,BookBean: " + bookBean.toString() + currentThread());
        if (StringUtils.isEmpty(bookBean.bookName)) {
            int nextInt = RandomUtils.randomInt(10000);
            bookBean.bookName = "(Reset)书名-" + nextInt;
            bookBean.bookAuthor = "作者-" + nextInt;
            bookBean.bookPrice = (RandomUtils.randomInt(10000) + 10000) / 100d;
            Logger.i("addBookInOut() ,Reset BookBean: " + bookBean.toString() + currentThread());
        }
        bookBeans.add(bookBean);
    }

    @Override
    public List<BookBean> getBookList() throws RemoteException {
        return bookBeans;
    }

    private String currentThread() {
        return " ,Thread Name： " + Thread.currentThread().getName();
    }
}
