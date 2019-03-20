package com.yirong.baselib.bean

public class CacheBeans{
    data class RequestBean private constructor(var type:Int,var className:String,var methodName:String,var requestParams:Array<RequestParameter>){
        override fun toString(): String {
            return "RequestBean{type=$type,className = $className,methodName=$methodName},requestParams.size=${requestParams.size}"
        }
    }

    data class ResponseBean private constructor(var data:Any)


    data class RequestParameter private constructor(var parameterClassName: String,var parameterValue:String){
        override fun toString(): String {
            return "RequestParameter{parameterClassName=$parameterClassName,parameterValue=$parameterValue }"
        }
    }

}