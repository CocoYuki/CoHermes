package com.yirong.baselib.cache

import android.os.Build
import android.support.annotation.RequiresApi
import android.text.TextUtils
import java.lang.reflect.Method





object CacheCenter {
    private var mClasses:HashMap<String,Class<*>> = HashMap()//缓存class
    private var mMethods:HashMap<Class<*>,HashMap<String,Method>> = HashMap()//缓存方法列表
    private var mObjects:HashMap<String,Any> = HashMap()//缓存对象

    @RequiresApi(Build.VERSION_CODES.N)
    fun register(clazz:Class<*> ){
        mClasses[clazz.name] = clazz
        //缓存方法列表
        registerMethod(clazz)
    }
    //获取缓存对象
    fun getObject(name:String):Any?{
        return mObjects[name]
    }

    fun putObject(name: String, `object`: Any) {
        mObjects[name] = `object`
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun registerMethod(clazz: Class<*>) {
        var methods:Array<Method> = clazz.methods//获取类中方法数组
        for (method in methods){//遍历
            mMethods.putIfAbsent(clazz, HashMap<String,Method>())
            var map:HashMap<String,Method> = mMethods[clazz]!!
            map[method.name] = method
        }

    }

    //获取缓存方法
    @RequiresApi(Build.VERSION_CODES.N)
    fun getMethod(className:String, name:String?):Method?{
        var clazz = getClassType(className)
        if (name != null) {
            mMethods.putIfAbsent(clazz!!, HashMap())
            val methods = mMethods[clazz]
            val method = methods!![name]
            if (method != null) {
                return method!!
            }
        }
        return null
    }

    fun getClassType(className: String?): Class<*>?{
        if(TextUtils.isEmpty(className)){
            return null
        }
        var clazz = mClasses[className]
        if(clazz==null){
            clazz = Class.forName(className!!)
        }
        return clazz
    }

}