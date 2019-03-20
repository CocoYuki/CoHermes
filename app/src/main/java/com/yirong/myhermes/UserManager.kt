package com.yirong.myhermes

class UserManager private constructor():IUserManager {
    private lateinit var account:Account
    companion object {
        private var instance: UserManager? = null
            get() {
                if (field == null) {
                    field = UserManager()
                }
                return field
            }
        @Synchronized
        fun get(): UserManager{
            return instance!!
        }
    }

    override fun setAccount(account: Account){
        this.account = account
    }
    override fun getAccount():Account{
        return account
    }

    data class Account(var username:String,var password:String){
        override fun toString(): String {
            return "Account{username=$username,password=$password}"
        }
    }
}