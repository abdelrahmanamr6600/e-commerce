package com.abdelrahman.amr.myshop.utils

import android.app.Application

class MyShopApplication:Application() {

    companion object
    {
        var instance :MyShopApplication = MyShopApplication()

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getInstance():MyShopApplication{
        return instance
    }
}