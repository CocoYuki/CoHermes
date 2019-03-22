package com.yirong.baselib.annotation

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
annotation class ClassId(val value:String)