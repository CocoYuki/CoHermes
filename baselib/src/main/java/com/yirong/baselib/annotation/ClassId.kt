package com.yirong.baselib.annotation

@Target( AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ClassId(val value:String)