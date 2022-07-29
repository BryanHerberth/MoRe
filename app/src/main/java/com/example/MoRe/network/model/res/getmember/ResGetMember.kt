package com.example.MoRe.network.model.res.getmember

data class ResGetMember (
    val status: String,
    val data: Data
)

data class Data(
    val anggota:Array<Anggota>
)

data class Anggota(
    val id_pengguna: String,
    val nama_pengguna: String,
    val email: String,
    val foto_profil: String,
    val status: String,
)
