package com.yirong.myhermes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UserManager.get().setAccount(UserManager.Account("青江菜","123456"))

    }
    //跳转
    fun jump(){
        startActivity(Intent(this,SecondActivity::class.java))
    }
}
