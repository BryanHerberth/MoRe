package com.example.MoRe.network.repository

import android.util.Log
import com.example.MoRe.network.api.RetrofitInstance
import com.example.MoRe.network.model.ResStart
import com.example.MoRe.network.model.req.*
import com.example.MoRe.network.model.res.ResGetUser
import com.example.MoRe.network.model.res.ResSendVerifikasi
import com.example.MoRe.network.model.res.ResVerifikasi
import com.example.MoRe.network.model.res.dokumen.ResGetDokumen
import com.example.MoRe.network.model.res.getmember.ResGetMember
import com.example.MoRe.network.model.res.getmesin.ResGetMesin
import com.example.MoRe.network.model.res.getmesin.ResGetStatusMesin
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrik
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrikById
import com.example.MoRe.network.model.res.laporan.ResGetVarLaporan
import com.example.MoRe.network.model.res.laporan.ResLaporanByName
import com.example.MoRe.network.model.res.laporan.ResLaporanChart
import com.example.MoRe.network.model.res.login.ResLogin
import com.example.MoRe.network.model.res.monitor.ResGetMonitor
import com.example.MoRe.network.model.res.notifikasi.ResGetNotifikasi
import com.example.MoRe.network.model.res.notifikasi.ResPutNotifikasi
import com.example.MoRe.network.model.res.putUser.ResPutUser
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
    suspend fun putUser(reqPutUser: ReqPutUser): Response<ResPutUser>{
        return RetrofitInstance.api.putUser(reqPutUser)
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

    suspend fun getStatusMesin(idPabrik: String, idMesin: String): Response<ResGetStatusMesin>{
        return RetrofitInstance.api.getStatusMesin(idPabrik, idMesin)
    }

    // Notifikasi
    suspend fun getNotifikasi(): Response<ResGetNotifikasi>{
        return RetrofitInstance.api.getNotifikasi()
    }

    suspend fun putNotifikasi(idNotifikasi: String): Response<ResPutNotifikasi>{
        return RetrofitInstance.api.putNotifikasi(idNotifikasi)
    }

    // Monitor
    suspend fun getMonitor(idPabrik: String, idMesin: String): Response<ResGetMonitor>{
        return RetrofitInstance.api.getMonitor(idPabrik, idMesin)
    }

    // Laporan
    suspend fun getLaporanVariabel(idPabrik: String, idMesin: String): Response<ResGetVarLaporan>{
        return  RetrofitInstance.api.getLaporanVariabel(idPabrik, idMesin)
    }

    // Dokumen
    suspend fun getDokumen(idPabrik: String, idMesin: String): Response<ResGetDokumen>{
        return RetrofitInstance.api.getDokumen(idPabrik, idMesin)
    }

    // Laporan
    suspend fun postLaporan(idPabrik: String, idMesin: String, reqLaporan: ReqLaporan): Response<ResLaporanByName>{
        return RetrofitInstance.api.postLaporanByName(idPabrik,idMesin, reqLaporan)
    }

    suspend fun postLaporanChart(idPabrik: String, idMesin: String, reqLaporan: ReqLaporan): Response<ResLaporanChart>{
        return RetrofitInstance.api.postLaporanChartAndroid(idPabrik, idMesin, reqLaporan)
    }


}