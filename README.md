# Android Service

该项目主要包含Android中Service各种使用，包括：

* 本地服务：开启本地服务、绑定本地服务（方法调用，传递数据）、开启前台服务 

    [java/com.renj.service.local](https://github.com/itrenjunhua/AndroidService/tree/master/app/src/main/java/com/renj/service/local)

* 远程服务：开启远程服务、绑定远程服务（方法调用，传递数据）

	[java/com.renj.service.remote/RemoteStartService](https://github.com/itrenjunhua/AndroidService/tree/master/app/src/main/java/com/renj/service/remote/RemoteStartService.java)

* AIDL使用：通过AIDL进行IPC通讯
    
	[java/com.renj.service.remote/RemoteBinderService](https://github.com/itrenjunhua/AndroidService/blob/master/app/src/main/java/com/renj/service/remote/RemoteBinderService.java)  
    [aidl/com.renj.service.aidl/IRemoteBookBinder.aidl](https://github.com/itrenjunhua/AndroidService/tree/master/app/src/main/aidl/com/renj/service/aidl/IRemoteBookBinder.aidl)

* 多个AIDL时使用Binder池实现
    
	[java/com.renj.service.pool](https://github.com/itrenjunhua/AndroidService/tree/master/app/src/main/java/com/renj/service/pool)  
    [aidl/com.renj.service.aidl(除callback包之外)](https://github.com/itrenjunhua/AndroidService/tree/master/app/src/main/aidl/com/renj/service/aidl)

* 客户端设置监听到服务端，服务端数据发生改变了通过监听回调通知客户端
    
	[java/com.renj.service.callback](https://github.com/itrenjunhua/AndroidService/tree/master/app/src/main/java/com/renj/service/callback)  
    [aidl/com.renj.service.aidl.allback](https://github.com/itrenjunhua/AndroidService/tree/master/app/src/main/aidl/com/renj/service/aidl/callback)

* Messenger 的使用
    
	[java/com.renj.service.messenger](https://github.com/itrenjunhua/AndroidService/tree/master/app/src/main/java/com/renj/service/messenger)

* IntentService 的使用
    
	[java/com.renj.service.intentservice](https://github.com/itrenjunhua/AndroidService/tree/master/app/src/main/java/com/renj/service/intentservice)