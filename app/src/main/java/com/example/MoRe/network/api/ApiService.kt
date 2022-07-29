package com.example.MoRe.network.api

import com.example.MoRe.network.model.ResStart
import com.example.MoRe.network.model.req.ReqLogin
import com.example.MoRe.network.model.req.ReqRegister
import com.example.MoRe.network.model.req.ReqSendVerifikasi
import com.example.MoRe.network.model.req.ReqVerifikasi
import com.example.MoRe.network.model.res.ResGetUser
import com.example.MoRe.network.model.res.ResSendVerifikasi
import com.example.MoRe.network.model.res.ResVerifikasi
import com.example.MoRe.network.model.res.dokumen.ResGetDokumen
import com.example.MoRe.network.model.res.getmember.ResGetMember
import com.example.MoRe.network.model.res.getmesin.ResGetMesin
import com.example.MoRe.network.model.res.getmesin.ResGetMesinById
import com.example.MoRe.network.model.res.getmesin.ResGetMesinByName
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrik
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrikById
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrikByName
import com.example.MoRe.network.model.res.laporan.ResGetVarLaporan
import com.example.MoRe.network.model.res.login.ResLogin
import com.example.MoRe.network.model.res.monitor.ResGetMonitor
import com.example.MoRe.network.model.res.notifikasi.ResGetNotifikasi
import com.example.MoRe.network.model.res.notifikasi.ResPutNotifikasi
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

    @POST("/users/sendVerifikasi")
    suspend fun postUserSendVerifikasi(
        @Body reqSendVerifikasi: ReqSendVerifikasi
    ): Response<ResSendVerifikasi>

    @POST("/users/verifikasi")
    suspend fun postUserVerifikasi(
        @Body reqVerifikasi: ReqVerifikasi
    ): Response<ResVerifikasi>

    @GET("/users")
    suspend fun getUser() : Response<ResGetUser>

    // Authentications
    @POST("/authentications")
    suspend fun postUserLogin(
        @Body reqLogin: ReqLogin
    ): Response<ResLogin>

    // PANDINGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
    @DELETE("/authentications")
    suspend fun deleteUserLogin()

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