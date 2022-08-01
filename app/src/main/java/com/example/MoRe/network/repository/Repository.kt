package com.example.MoRe.network.repository

import android.util.Log
import com.example.MoRe.network.api.RetrofitInstance
import com.example.MoRe.network.model.ResStart
import com.example.MoRe.network.model.req.ReqLogin
import com.example.MoRe.network.model.req.ReqRegister
import com.example.MoRe.network.model.req.ReqSendVerifikasi
import com.example.MoRe.network.model.req.ReqVerifikasi
import com.example.MoRe.network.model.res.ResGetUser
import com.example.MoRe.network.model.res.ResSendVerifikasi
import com.example.MoRe.network.model.res.ResVerifikasi
import com.example.MoRe.network.model.res.getmember.ResGetMember
import com.example.MoRe.network.model.res.getmesin.ResGetMesin
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrik
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrikById
import com.example.MoRe.network.model.res.login.ResLogin
import com.example.MoRe.network.model.res.notifikasi.ResGetNotifikasi
import com.example.MoRe.network.model.res.notifikasi.ResPutNotifikasi
import com.example.MoRe.network.model.res.register.ResRegister
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityScoped
class Repository @Inject constructor(
) {
    suspend fun getStart(): Response<ResStart>{
        return RetrofitInstance.api.getStart()
    }

    // User
    suspend fun postUserRegister(reqRegister: ReqRegister): Response<ResRegister>{
        return RetrofitInstance.api.postUserRegister(reqRegister)
    }
    suspend fun postUserSendVerifikasi(reqSendVerifikasi: ReqSendVerifikasi) : Response<ResSendVerifikasi>{
        return RetrofitInstance.api.postUserSendVerifikasi(reqSendVerifikasi)
    }
    suspend fun postUserVerifikasi(reqVerifikasi: ReqVerifikasi): Response<ResVerifikasi>{
        return RetrofitInstance.api.postUserVerifikasi(reqVerifikasi)
    }
    suspend fun getUser() : Response<ResGetUser>{
        return RetrofitInstance.api.getUser()
    }

    // Authentications
    suspend fun postLogin(reqLogin: ReqLogin): Response<ResLogin>{
        return  RetrofitInstance.api.postUserLogin(reqLogin)
    }

    suspend fun deleteUserLogin():Response<String>{ //Pending ------------
        return RetrofitInstance.api.deleteUserLogin()
    }

    // Pabrik
    suspend fun getPabrik(): Response<ResGetPabrik> {
        return RetrofitInstance.api.getPabrik()
    }
    suspend fun getParikById(id: String): Response<ResGetPabrikById>{
        Log.e("Masuk repository getPabrikById", id)
        return RetrofitInstance.api.getPabrikById(id)
    }

    // Member
    suspend fun getMember(idPabrik: String) : Response<ResGetMember>{
        return RetrofitInstance.api.getMember(idPabrik)
    }

    // Mesin
    suspend fun getMesin(idPabrik: String): Response<ResGetMesin>{
        return RetrofitInstance.api.getMesin(idPabrik)
    }

    // Notifikasi
    suspend fun getNotifikasi(): Response<ResGetNotifikasi>{
        return RetrofitInstance.api.getNotifikasi()
    }

    suspend fun putNotifikasi(idNotifikasi: String): Response<ResPutNotifikasi>{
        return RetrofitInstance.api.putNotifikasi(idNotifikasi)
    }


}