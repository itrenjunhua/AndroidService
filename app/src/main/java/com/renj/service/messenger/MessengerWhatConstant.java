package com.renj.service.messenger;

/**
 * ======================================================================
 * <p>
 * 作者：Renj
 * <p>
 * 创建时间：2020-10-26   15:28
 * <p>
 * 描述：Messenger 传递 Message 的 What 常量
 * <p>
 * 修订历史：
 * <p>
 * ======================================================================
 */
public interface MessengerWhatConstant {
    /**
     * 客户端发送的信息
     */
    int MESSENGER_CLIENT_SEND = 0;
    /**
     * 客户端发送信息并且等待服务器返回
     */
    int MESSENGER_CLIENT_SEND_WAIT = 1;
    /**
     * 服务端发送的信息
     */
    int MESSENGER_SERVICE_SEND = 2;
}
