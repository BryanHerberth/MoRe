package com.example.MoRe.network.model.req

data class ReqRegister(
    val nama_pengguna: String,
    val email: String,
    val password: String,
    val no_telepon: String
)