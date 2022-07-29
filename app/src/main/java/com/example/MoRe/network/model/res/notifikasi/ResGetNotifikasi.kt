package com.example.MoRe.network.model.res.notifikasi

data class ResGetNotifikasi (
    val status: String,
    val data: DataGet
)

data class DataGet(
    val notifikasi: Array<Notifikasi>
)

data class Notifikasi(
    val id_notifikasi: String,
    val id_pengguna: String,
    val id_pabrik: String,
    val id_mesin: String,
    val text: String,
    val baca: Boolean,
    val nama_pabrik: String,
    val nama_mesin: String
)

data class ResPutNotifikasi(
    val status: String,
    val message: String
)