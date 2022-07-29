package com.example.MoRe.network.model.res.getuser

data class User(
    val id_pengguna: String,
    val nama_pengguna: String,
    val email: String,
    val password: String,
    val no_telepon: String,
    val status_verifikasi: Boolean,
    val foto_profil: String
)
