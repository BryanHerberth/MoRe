package com.example.MoRe.network.model.res

data class ResSendVerifikasi(
    val status: String,
    val message: String,
    val data: Data
)

data class Data(
    val kode: String
)
