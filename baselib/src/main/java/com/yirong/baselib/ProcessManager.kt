package com.yirong.baselib

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import com.google.gson.Gson
import com.yirong.baselib.cache.CacheCenter
import android.content.ComponentName
import android.os.IBinder
import android.content.ServiceConnection

import android.content.Intent
import android.text.TextUtils
import com.yirong.baselib.service.ProcessService
import java.lang.reflect.Method


object ProcessManager {
    var gson = Gson()
    var cache = CacheCenter
    lateinit var processInterface:ProcessInterface
    @RequiresApi(Build.VERSION_CODES.N)
    fun register(clazz:Class<*>){
        cache.register(clazz)
    }

    //b进程调用
    //json格式
    /*json 数据格式
       "className":"com.yirong.baselib.UserManager",
       "methodName":"getInstance",
       "requestParameters":[{
           "parameterClassName":"java.lang.String",
           "parameterValue":"10001"
       }],
       "type":1
        */

    fun <T> getInstance(clazz: Class<T>, vararg parameters: Any): T {
        sendRequest(ProcessService.GET_INSTANCE, clazz, null, parameters)
        return getProxy(clazz)
    }

    private fun <T> sendRequest(type: Int, clazz: Class<T>, method:Method?,parameters: Array<out Any>) {
        if(parameters.isNotEmpty()){
            parameters.
        }
    }

    private fun <T> getProxy(clazz: Class<T>): T {

    }

    fun connect(context: Context) {
        bind(context, null, ProcessService::class.java)
    }

    fun connect(context: Context, packageName: String) {
        bind(context, packageName, ProcessService::class.java)
    }


    fun bind(context: Context, packageName: String?, service: Class<out ProcessService>) {
        //开启service 连接服务
        val intent: Intent
        if (TextUtils.isEmpty(packageName)) {
            intent = Intent(context, service)
        } else {
            intent = Intent()
            intent.setPackage(packageName)
            intent.action = service.name

        }
        context.bindService(intent, ProcessConnection(), Context.BIND_AUTO_CREATE)
    }


    private class ProcessConnection : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            processInterface = ProcessInterface.Stub.asInterface(iBinder)

        }
        override fun onServiceDisconnected(componentName: ComponentName) {
        }
    }


}