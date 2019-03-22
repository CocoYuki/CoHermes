package com.yirong.baselib.service

import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import com.yirong.baselib.ProcessInterface
import com.google.gson.Gson
import com.yirong.baselib.bean.CacheBeans
import com.yirong.baselib.cache.CacheCenter
import java.lang.reflect.InvocationTargetException
import com.yirong.baselib.bean.CacheBeans.RequestBean

class ProcessService:Service() {

    companion object {
        val GET_INSTANCE = 1//获取对象
        val GET_METHOD = 2//执行方法
    }

    private val cacheCenter = CacheCenter
    private val gson = Gson()
    override fun onBind(intent: Intent?): IBinder? {
        return MyBind()
    }

    inner class MyBind: ProcessInterface.Stub() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun send(request: String?): String{

            var requestBean: CacheBeans.RequestBean = gson.fromJson(request,CacheBeans.RequestBean::class.java)
            when (requestBean.type) {
                GET_INSTANCE -> {
                    val method = cacheCenter.getMethod(requestBean.className, "getInstance")
                    val mParameters = makeParameterObject(requestBean)
                    try {
                        val userManager = method!!.invoke(null, mParameters)
                        cacheCenter.putObject(requestBean.className, userManager)
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    } catch (e: InvocationTargetException) {
                        e.printStackTrace()
                    }
                }
                GET_METHOD -> {
                    val userManager = cacheCenter.getObject(requestBean.className)
                    val getAccount = cacheCenter.getMethod(requestBean.className, requestBean.methodName)
                    val mParameters1 = makeParameterObject(requestBean)
                    try {
                        val person = getAccount!!.invoke(userManager, mParameters1)
                        return gson.toJson(person)
                    } catch (e: IllegalAccessException) {
                        e.printStackTrace()
                    } catch (e: InvocationTargetException) {
                        e.printStackTrace()
                    }
                }
            }
            return ""
        }

    }

    private fun makeParameterObject(requestBean: RequestBean): Array<Any?> {
        //参数还原
        var mParameters: Array<Any?>? = null
        val requestParameters = requestBean.requestParams
        if (requestParameters != null && requestParameters!!.isNotEmpty()) {
            mParameters = arrayOfNulls(requestParameters!!.size)
            for (i in requestParameters!!.indices) {
                val requestParameter = requestParameters!![i]
                val clazz = cacheCenter.getClassType(requestParameter!!.parameterClassName)
                mParameters[i] = gson.fromJson(requestParameter!!.parameterValue, clazz)
            }
        } else {
            mParameters = arrayOfNulls(0)
        }
        return mParameters

    }


}