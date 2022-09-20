package com.abdelrahman.amr.myshop.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.ContextCompat

class SupportFunctions {
    companion object{
        fun startActivity(context: Context, activity: Class<*>) {
            ContextCompat.startActivity(context, Intent(context, activity), null)
        }
    }


}