package com.renj.service.local;

import com.renj.service.bean.BookBean;

import java.util.List;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-09-28   10:01
 * <p>
 * 描述：本地绑定服务方法定义
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface ILocalBinder {
    void addBook(BookBean bookBean);

    List<BookBean> getBookList();
}
