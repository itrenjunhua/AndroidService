package com.renj.service.pool;

import android.os.RemoteException;
import android.widget.Button;
import android.widget.TextView;

import com.renj.service.BaseActivity;
import com.renj.service.R;
import com.renj.service.aidl.IRemoteBookBinder;
import com.renj.service.aidl.IRemotePersonBinder;
import com.renj.service.aidl.IRemoteStringBinder;
import com.renj.service.bean.BookBean;
import com.renj.service.bean.PersonBean;
import com.renj.service.remote.RemoteBinderService;
import com.renj.service.utils.ListUtils;
import com.renj.service.utils.Logger;
import com.renj.service.utils.RandomUtils;
import com.renj.service.utils.ToastUtils;

import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-10   16:55
 * <p>
 * 描述：AIDL 绑定池实例
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public class BinderPoolActivity extends BaseActivity {
    private Button btPoolBind;
    private Button btAddBook;
    private Button btGetBook;
    private Button btAddPerson;
    private Button btGetPerson;
    private Button btAddString;
    private Button btGetString;
    private Button btPoolUnbind;
    private TextView tvContent;

    private BinderPoolManager binderPoolManagerInstance;

    @Override
    protected int getLayoutId() {
        return R.layout.binder_pool_activity;
    }

    @Override
    protected void initView() {
        btPoolBind = findViewById(R.id.bt_pool_bind);
        btAddBook = findViewById(R.id.bt_add_book);
        btGetBook = findViewById(R.id.bt_get_book);
        btAddPerson = findViewById(R.id.bt_add_person);
        btGetPerson = findViewById(R.id.bt_get_person);
        btAddString = findViewById(R.id.bt_add_string);
        btGetString = findViewById(R.id.bt_get_string);
        btPoolUnbind = findViewById(R.id.bt_pool_unbind);
        tvContent = findViewById(R.id.tv_content);
    }

    @Override
    protected void initPageData() {
        binderPoolManagerInstance = BinderPoolManager.getInstance(this);
    }

    @Override
    protected void initListener() {
        // 绑定服务
        btPoolBind.setOnClickListener(v -> {
            binderPoolManagerInstance.bindService();
            tvContent.setText("服务已绑定");
        });
        // 解绑服务
        btPoolUnbind.setOnClickListener(v -> {
            binderPoolManagerInstance.unBindService();
            tvContent.setText("服务未绑定");
        });
        // 增加Book信息
        btAddBook.setOnClickListener(v -> addBookInfo());
        // 获取Book信息
        btGetBook.setOnClickListener(v -> getBookInfo());
        // 增加Person信息
        btAddPerson.setOnClickListener(v -> addPersonInfo());
        // 获取Person信息
        btGetPerson.setOnClickListener(v -> getPersonInfo());
        // 增加String信息
        btAddString.setOnClickListener(v -> addStringInfo());
        // 获取String信息
        btGetString.setOnClickListener(v -> getStringInfo());
    }

    // 获取String信息
    private void getStringInfo() {
        IRemoteStringBinder stringBinder = IRemoteStringBinder.Stub.asInterface(binderPoolManagerInstance
                .getBinder(BinderPoolBinderImpl.BINDER_TYPE_STRING));
        if (stringBinder == null) {
            ToastUtils.showToast("请先绑定服务");
            return;
        }
        List<String> strings = null;
        try {
            strings = stringBinder.getStringList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (ListUtils.notEmpty(strings)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("String总数: " + strings.size()).append("\n");
            Logger.i("-------- String总数: " + strings.size() + " ------------");
            for (String s : strings) {
                Logger.i("String => " + s);
                stringBuilder.append(s).append("\n");
            }
            tvContent.setText(stringBuilder.toString());
            Logger.i("-----------------------------------");
        } else {
            tvContent.setText("没有String数据");
            ToastUtils.showToast("没有String数据");
        }
    }

    // 增加String信息
    private void addStringInfo() {
        try {
            IRemoteStringBinder stringBinder = IRemoteStringBinder.Stub.asInterface(binderPoolManagerInstance
                    .getBinder(BinderPoolBinderImpl.BINDER_TYPE_STRING));
            if (stringBinder == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }
            // addString
            Logger.i("-------- addString() ------------------------");
            String value = "String Content-" + RandomUtils.randomInt(10000);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addString() before info：" + value);
            stringBinder.addString(value);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addString() after info：" + value);

            Logger.i("-------------------------------------------------");

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("增加 String: ").append("\n")
                    .append(value);
            tvContent.setText(stringBuilder.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // 获取Person信息
    private void getPersonInfo() {
        IRemotePersonBinder personBinder = IRemotePersonBinder.Stub.asInterface(binderPoolManagerInstance
                .getBinder(BinderPoolBinderImpl.BINDER_TYPE_PERSON));
        if (personBinder == null) {
            ToastUtils.showToast("请先绑定服务");
            return;
        }
        List<PersonBean> personBeans = null;
        try {
            personBeans = personBinder.getPersonList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (ListUtils.notEmpty(personBeans)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Person总数: " + personBeans.size()).append("\n");
            Logger.i("-------- Person总数: " + personBeans.size() + " ------------");
            for (PersonBean personBean : personBeans) {
                Logger.i("PersonBean => " + personBean);
                stringBuilder.append(personBean).append("\n");
            }
            tvContent.setText(stringBuilder.toString());
            Logger.i("-----------------------------------");
        } else {
            tvContent.setText("没有Person数据");
            ToastUtils.showToast("没有Person数据");
        }
    }

    // 增加Person信息
    private void addPersonInfo() {
        try {
            IRemotePersonBinder personBinder = IRemotePersonBinder.Stub.asInterface(binderPoolManagerInstance
                    .getBinder(BinderPoolBinderImpl.BINDER_TYPE_PERSON));
            if (personBinder == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }
            // addPersonIn
            Logger.i("-------- addPersonIn() ------------------------");
            PersonBean personBeanIn = new PersonBean("用户名-" + RandomUtils.randomInt(10000),
                    RandomUtils.randomInt(80) + 19, RandomUtils.randomInt(2));
            Logger.i(RemoteBinderService.SERVICE_NAME + " addPersonIn() before info：" + personBeanIn.toString());
            personBinder.addPersonIn(personBeanIn);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addPersonIn() after info：" + personBeanIn.toString());

            // addBookOut
            Logger.i("-------- addPersonOut() ------------------------");
            PersonBean personBeanOut = new PersonBean("用户名-" + RandomUtils.randomInt(10000),
                    RandomUtils.randomInt(80) + 19, RandomUtils.randomInt(2));
            Logger.i(RemoteBinderService.SERVICE_NAME + " addPersonOut() before info：" + personBeanOut.toString());
            personBinder.addPersonOut(personBeanOut);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addPersonOut() after info：" + personBeanOut.toString());

            // addBookInOut
            Logger.i("-------- addPersonInOut() ------------------------");
            PersonBean personBeanInOut = new PersonBean("用户名-" + RandomUtils.randomInt(10000),
                    RandomUtils.randomInt(80) + 19, RandomUtils.randomInt(2));
            Logger.i(RemoteBinderService.SERVICE_NAME + " addPersonInOut() before info：" + personBeanInOut.toString());
            personBinder.addPersonInOut(personBeanInOut);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addPersonInOut() after info：" + personBeanInOut.toString());

            Logger.i("-------------------------------------------------");

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("增加 Person: ").append("\n")
                    .append(personBeanIn).append("\n")
                    .append(personBeanOut).append("\n")
                    .append(personBeanInOut);
            tvContent.setText(stringBuilder.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    // 获取Book信息
    private void getBookInfo() {
        IRemoteBookBinder bookBinder = IRemoteBookBinder.Stub.asInterface(binderPoolManagerInstance
                .getBinder(BinderPoolBinderImpl.BINDER_TYPE_BOOK));
        if (bookBinder == null) {
            ToastUtils.showToast("请先绑定服务");
            return;
        }

        List<BookBean> bookList = null;
        try {
            bookList = bookBinder.getBookList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (ListUtils.notEmpty(bookList)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Book总数: " + bookList.size()).append("\n");
            Logger.i("-------- Book总数: " + bookList.size() + " ------------");
            for (BookBean bookBean : bookList) {
                Logger.i("BookList => " + bookBean);
                stringBuilder.append(bookBean).append("\n");
            }
            tvContent.setText(stringBuilder.toString());
            Logger.i("-----------------------------------");
        } else {
            tvContent.setText("没有Book数据");
            ToastUtils.showToast("没有Book数据");
        }
    }

    // 增加Book信息
    private void addBookInfo() {
        try {
            IRemoteBookBinder bookBinder = IRemoteBookBinder.Stub.asInterface(binderPoolManagerInstance
                    .getBinder(BinderPoolBinderImpl.BINDER_TYPE_BOOK));
            if (bookBinder == null) {
                ToastUtils.showToast("请先绑定服务");
                return;
            }
            // addBookIn
            Logger.i("-------- addBookIn() ------------------------");
            int nextIntIn = RandomUtils.randomInt(10000);
            BookBean bookBeanIn = new BookBean("书名-" + nextIntIn, "作者-" + nextIntIn,
                    (RandomUtils.randomInt(10000) + 10000) / 100d);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addBookIn() before info：" + bookBeanIn.toString());
            bookBinder.addBookIn(bookBeanIn);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addBookIn() after info：" + bookBeanIn.toString());

            // addBookOut
            Logger.i("-------- addBookOut() ------------------------");
            int nextIntOut = RandomUtils.randomInt(10000);
            BookBean bookBeanOut = new BookBean("书名-" + nextIntOut, "作者-" + nextIntOut,
                    (RandomUtils.randomInt(10000) + 10000) / 100d);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addBookOut() before info：" + bookBeanOut.toString());
            bookBinder.addBookOut(bookBeanOut);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addBookOut() after info：" + bookBeanOut.toString());

            // addBookInOut
            Logger.i("-------- addBookInOut() ------------------------");
            int nextIntInOut = RandomUtils.randomInt(10000);
            BookBean bookBeanInOut = new BookBean("书名-" + nextIntInOut, "作者-" + nextIntInOut,
                    (RandomUtils.randomInt(10000) + 10000) / 100d);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addBookInOut() before info：" + bookBeanInOut.toString());
            bookBinder.addBookInOut(bookBeanInOut);
            Logger.i(RemoteBinderService.SERVICE_NAME + " addBookInOut() after info：" + bookBeanInOut.toString());

            Logger.i("-------------------------------------------------");

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("增加 Book: ").append("\n")
                    .append(bookBeanIn).append("\n")
                    .append(bookBeanOut).append("\n")
                    .append(bookBeanInOut);
            tvContent.setText(stringBuilder.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
