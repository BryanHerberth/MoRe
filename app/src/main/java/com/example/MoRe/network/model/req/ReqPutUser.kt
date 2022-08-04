package com.example.MoRe.network.model.req

data class ReqPutUser(
    val nama_pengguna : String,
    val password: String,
    val no_telepon: String,
)