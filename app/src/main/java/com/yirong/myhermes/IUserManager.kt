package com.yirong.myhermes
import com.yirong.myhermes.UserManager.Account

interface IUserManager {
    fun setAccount(account: Account)

    fun getAccount():Account
}
