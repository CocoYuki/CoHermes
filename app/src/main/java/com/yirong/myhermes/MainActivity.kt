package com.yirong.myhermes

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.yirong.baselib.ProcessManager

class MainActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        UserManager.get().setAccount(UserManager.Account("青江菜","123456"))
        ProcessManager.register(UserManager::class.java)
    }
    //跳转
    fun jump(){
        startActivity(Intent(this,SecondActivity::class.java))
    }
}
