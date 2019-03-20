package com.yirong.myhermes

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity :BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        tv.text = UserManager.get().getAccount().toString()
    }
}