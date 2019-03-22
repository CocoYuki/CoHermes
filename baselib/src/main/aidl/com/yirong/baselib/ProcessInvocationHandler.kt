package com.yirong.baselib

import android.text.TextUtils
import android.util.Log
import com.yirong.baselib.service.ProcessService
import com.google.gson.Gson
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method


class ProcessInvocationHandler(mclazz: Class<*>) : InvocationHandler {

    private val clazz: Class<*>
    private val gson = Gson()
    init {
        clazz = mclazz
        Log.i("ProcessHandler", "ProcessInvocationHandler: $clazz")
    }


    @Throws(Throwable::class)
    override fun invoke(proxy: Any, method: Method, args: Array<Any>): Any? {

        Log.i("ProcessHandler", "ProcessInvocationHandler: $clazz<<<<<<$method<<<<$args")

        //发请求
        val respones = ProcessManager.sendRequest(ProcessService.GET_METHOD, clazz, method, args)

        if (!TextUtils.isEmpty(respones) && respones != "null") {
            val userClass = method.returnType
            return gson.fromJson(respones, userClass)
        }
        return null

    }

}