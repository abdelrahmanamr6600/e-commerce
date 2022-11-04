package com.abdelrahman.amr.myshop.db

import android.content.Context
import android.content.SharedPreferences
import com.abdelrahman.amr.myshop.models.user.UserResponse
import com.abdelrahman.amr.myshop.utils.Constants.Companion.KEY_PREFERENCE_NAME
import com.abdelrahman.amr.myshop.utils.Constants.Companion.USER_DATA_KEY
import com.google.gson.Gson


class PreferenceManager(context: Context) {

    private var sharedPreferences: SharedPreferences? = null


    init {
        sharedPreferences = context.getSharedPreferences(KEY_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun putBoolean(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putBoolean(key, value)
        editor?.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences!!.getBoolean(key, false)
    }

    fun putString(key: String, value: String) {
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences!!.getString(key, null)
    }

    fun logIn(user: UserResponse) = sharedPreferences!!.edit().putString(
        USER_DATA_KEY, Gson().toJson(user)
    ).apply()

    fun clear() {
        val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
    }
}