package com.yirong.myhermes

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import com.yirong.baselib.ProcessManager


class MainActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ProcessManager.register(UserManager::class.java)
        UserManager.get().setAccount(UserManager.Account("青江菜","123456"))
    }
    //跳转
    fun jump(v: View){
        startActivity(Intent(this,SecondActivity::class.java))
    }
}
