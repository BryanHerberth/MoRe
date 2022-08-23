package com.example.MoRe.network.api

import com.example.MoRe.network.model.ResStart
import com.example.MoRe.network.model.req.*
import com.example.MoRe.network.model.res.ResGetUser
import com.example.MoRe.network.model.res.ResSendVerifikasi
import com.example.MoRe.network.model.res.ResVerifikasi
import com.example.MoRe.network.model.res.dokumen.ResGetDokumen
import com.example.MoRe.network.model.res.getmember.ResGetMember
import com.example.MoRe.network.model.res.getmesin.ResGetMesin
import com.example.MoRe.network.model.res.getmesin.ResGetMesinById
import com.example.MoRe.network.model.res.getmesin.ResGetMesinByName
import com.example.MoRe.network.model.res.getmesin.ResGetStatusMesin
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrik
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrikById
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrikByName
import com.example.MoRe.network.model.res.laporan.ResGetVarLaporan
import com.example.MoRe.network.model.res.laporan.ResLaporanByName
import com.example.MoRe.network.model.res.laporan.ResLaporanChart
import com.example.MoRe.network.model.res.login.ResLogin
import com.example.MoRe.network.model.res.monitor.ResGetMonitor
import com.example.MoRe.network.model.res.notifikasi.ResGetNotifikasi
import com.example.MoRe.network.model.res.notifikasi.ResPutNotifikasi
import com.example.MoRe.network.model.res.putUser.ResPutUser
import com.example.MoRe.network.model.res.register.ResRegister
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("/")
    suspend fun getStart(): Response<ResStart>

    // User
    @POST("/users/register")
    suspend fun postUserRegister(
        @Body reqRegister: ReqRegister
    ): Response<ResRegister>

    @POST("/users/sendverifikasi")
    suspend fun postUserSendVerifikasi(
        @Body reqSendVerifikasi: ReqSendVerifikasi
    ): Response<ResSendVerifikasi>

    @POST("/users/verifikasi")
    suspend fun postUserVerifikasi(
        @Body reqVerifikasi: ReqVerifikasi
    ): Response<ResVerifikasi>

    @GET("/users")
    suspend fun getUser() : Response<ResGetUser>

    @PUT("/users")
    suspend fun putUser(
        @Body reqPutUser: ReqPutUser
    ): Response<ResPutUser>

    // Authentications
    @POST("/authentications")
    suspend fun postUserLogin(
        @Body reqLogin: ReqLogin
    ): Response<ResLogin>

    // PANDINGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    @DELETE("/authentications")
    suspend fun deleteUserLogin(): Response<String>

    // Pabrik
    @GET("/pabrik")
    suspend fun getPabrik():Response<ResGetPabrik>

    @GET("/pabrik/{pabrikFilter}")
    suspend fun getPabrikByName(
        @Path("pabrikFilter") namaPabrik: String
    ): Response<ResGetPabrikByName>

    @GET("/pabrik/{id}")
    suspend fun getPabrikById(
        @Path("id") idPabrik: String
    ): Response<ResGetPabrikById>

    // Member
    @GET("/akses/{id}")
    suspend fun getMember(
        @Path("id") idPabrik: String
    ) : Response<ResGetMember>

    // Mesin
    @GET("/pabrik/{id}/mesin")
    suspend fun getMesin(
        @Path("id") idPabrik: String
    ): Response<ResGetMesin>

    @GET("/pabrik/{id}/mesin/{mesinFilter}")
    suspend fun getMesinByName(
        @Path("id") idPabrik: String,
        @Path("mesinFilter") namaMesin: String
    ): Response<ResGetMesinByName>

    @GET("/pabrik/{id}/mesin/{idMesin}")
    suspend fun getMesinById(
        @Path("id") idPabrik: String,
        @Path("idMesin") idMesin: String
    ): Response<ResGetMesinById>

    @GET("/pabrik/{id}/mesin/{idMesin}/status")
    suspend fun getStatusMesin(
        @Path("id") idPabrik: String,
        @Path("idMesin") idMesin: String
    ): Response<ResGetStatusMesin>

    // Monitor
    @GET("pabrik/{id}/mesin/{idMesin}/monitor")
    suspend fun getMonitor(
        @Path("id") idPabrik: String,
        @Path("idMesin") idMesin: String
    ) : Response<ResGetMonitor>

    // Laporan
    @GET("/pabrik/{id}/mesin/{idMesin}/laporan")
    suspend fun getLaporanVariabel(
        @Path("id") idPabrik: String,
        @Path("idMesin") idMesin: String
    ) : Response<ResGetVarLaporan>


    @POST("/pabrik/{id}/mesin/{idMesin}/laporan") // Pandinggggggggggggg
    suspend fun postLaporan(
        @Path("id") idPabrik: String,
        @Path("idMesin") idMesin: String
    )

    @POST("/pabrik/{id}/mesin/{idMesin}/laporanbyname")
    suspend fun postLaporanByName(
        @Path("id") idPabrik: String,
        @Path("idMesin") idMesin: String,
        @Body reqLaporan: ReqLaporan
    ) : Response<ResLaporanByName>

    @POST("/pabrik/{id}/mesin/{idMesin}/laporanchartandroid")
    suspend fun postLaporanChartAndroid(
        @Path("id") idPabrik: String,
        @Path("idMesin") idMesin: String,
        @Body reqLaporan: ReqLaporan
    ) : Response<ResLaporanChart>


    // Dokumen
    @GET("/pabrik/{id}/mesin/{idMesin}/dokumen")
    suspend fun getDokumen(
        @Path("id") idPabrik: String,
        @Path("idMesin") idMesin: String
    ) : Response<ResGetDokumen>

    // Notifikasi
    @GET("/notifikasi")
    suspend fun getNotifikasi() : Response<ResGetNotifikasi>

    @PUT("/notifikasi/{id}")
    suspend fun putNotifikasi(
        @Path("id") idNotifikasi: String
    ) : Response<ResPutNotifikasi>



}