# Android Service

该项目主要包含Android中Service各种使用，包括：

* 本地服务：开启本地服务、绑定本地服务（方法调用，传递数据）、开启前台服务
* 远程服务：开启远程服务、绑定远程服务（方法调用，传递数据）
* AIDL使用：通过AIDL进行IPC通讯
* 多个AIDL时使用Binder池实现
* 客户端设置监听到服务端，服务端数据发生改变了通过监听回调通知客户端