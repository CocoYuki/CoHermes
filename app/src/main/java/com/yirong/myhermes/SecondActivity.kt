package com.yirong.myhermes

import android.os.Bundle
import android.view.View
import com.yirong.baselib.ProcessManager
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity :BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //连接远程服务
        ProcessManager.connect(this)
        //获取请求结果
        UserManager.get().getAccount()
    }

    fun userManager(view:View){
        ProcessManager.get(IUserManager::class.java)
    }

    fun getUser(view:View){

    }

}