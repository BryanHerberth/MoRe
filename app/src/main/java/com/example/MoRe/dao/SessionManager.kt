package com.example.MoRe.dao

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import com.example.MoRe.network.model.res.Data
import com.example.MoRe.network.model.res.DataPabrik
import com.example.MoRe.network.model.res.getmesin.Mesin
import com.example.MoRe.network.model.res.getuser.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import javax.net.ssl.SSLSessionContext

object SessionManager {
    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun init(context: Context){
        this.context = context
        sharedPreferences = context.getSharedPreferences("tokenAuth", Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveAccessToken(accessToken: String){
        editor.putString(SessionConstant.ACCESS_TOKEN, accessToken).apply()
    }

    val accessToken: String get() = sharedPreferences.getString(SessionConstant.ACCESS_TOKEN, "") ?: ""

    fun savePabrik(pabrik: DataPabrik){
        val jsonText= Gson().toJson(pabrik)
        editor.putString(SessionConstant.ACTIVE_PABRIK, jsonText).apply()

    }

    fun getPabrikData() : DataPabrik? {
        Log.e("GetPabrikData ::::::", "")
        return try {
            val jsonText = sharedPreferences.getString(SessionConstant.ACTIVE_PABRIK, "")
            Log.e("GetPabrikData isi ::::::", jsonText.toString())
            return when {
                !TextUtils.isEmpty(jsonText) -> Gson().fromJson(jsonText, object : TypeToken<DataPabrik>() {}.type)
                else -> null
            }
        } catch (e: Exception){
            null
        }
    }

    fun saveMesin(mesin: Mesin){
        val jsonText = Gson().toJson(mesin)
        editor.putString(SessionConstant.ACTIVE_MESIN, jsonText).apply()
    }

    fun getMesinData() : Mesin? {
        return try {
            val jsonText = sharedPreferences.getString(SessionConstant.ACTIVE_MESIN, "")
            Log.e("isiGetMesinData : ", sharedPreferences.getString(SessionConstant.ACTIVE_MESIN, "").toString())
            return when {
                !TextUtils.isEmpty(jsonText) -> Gson().fromJson(jsonText, object : TypeToken<Mesin>() {}.type)
                else -> null
            }
        } catch (e: Exception){
            null
        }
    }
    fun saveUser(user: User){
        val jsonText = Gson().toJson(user)
        editor.putString(SessionConstant.ACTIVE_USER, jsonText).apply()
    }

    fun getUserData(): User? {
        return try{
            val jsonText = sharedPreferences.getString(SessionConstant.ACTIVE_USER, "")
            return when {
                !TextUtils.isEmpty(jsonText) -> Gson().fromJson(jsonText, object : TypeToken<User>() {}.type)
                else -> null
            }
        } catch (e: Exception){
            null
        }
    }
    fun saveStartDate(start: String){
        editor.putString(SessionConstant.STARTDATE, start).apply()
    }
    val getStartDate: String get() = sharedPreferences.getString(SessionConstant.STARTDATE, "") ?: ""

    fun saveStopDate(stop: String){
        editor.putString(SessionConstant.STOPDATE, stop).apply()
    }
    val getStopDate: String get() = sharedPreferences.getString(SessionConstant.STOPDATE, "") ?: ""



    fun logOut() {
        editor.clear().commit()
    }

    object SessionConstant {
        const val ACCESS_TOKEN = "accessToken"
        const val ACTIVE_PABRIK = "pabrik"
        const val ACTIVE_MESIN = "mesin"
        const val ACTIVE_USER = "user"
        const val STARTDATE = "startDate"
        const val STOPDATE = "stopDate"
        const val OAUTH_DATA = "oauthData"
    }
}