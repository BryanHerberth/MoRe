package com.example.MoRe.network.model.res.register

data class ResRegister(
    val status : String,
    val data: Data
)

data class Data(
    val id_pengguna: String
)
