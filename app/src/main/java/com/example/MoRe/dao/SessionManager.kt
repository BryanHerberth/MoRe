package com.example.MoRe.dao

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


@SuppressLint("StaticFieldLeak")
object SessionManager {
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context){
        this.context = context
        sharedPreferences = context.getSharedPreferences("DKM_POS", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveAccessToken(accessToken: String){
        editor.putString(SessionConstant.ACCESS_TOKEN, accessToken).apply()
    }

    val accessToken: String get() = sharedPreferences.getString(SessionConstant.ACCESS_TOKEN, "") ?: ""

//    fun saveUserData(data: )

    fun logOut(){
        editor.clear().commit()
    }
    object SessionConstant {
        const val ACCESS_TOKEN = "accessToken"
        const val REFRESH_TOKEN = "refreshToken"
    }
}