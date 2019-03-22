package com.yirong.myhermes
import com.yirong.baselib.annotation.ClassId
import com.yirong.myhermes.UserManager.Account


@ClassId("com.yirong.myhermes.UserManager")
interface IUserManager {
    fun setAccount(account: Account)

    fun getAccount():Account
}
