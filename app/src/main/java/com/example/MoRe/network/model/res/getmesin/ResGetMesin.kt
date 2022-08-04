package com.example.MoRe.network.model.res.getmesin

data class ResGetMesin(
    val status: String,
    val data: ArrData,
)

data class ResGetMesinByName(
    val status: String,
    val data: ArrData
)

data class ResGetMesinById(
    val status: String,
    val data: Data
)

data class ArrData(
    val mesin: ArrayList<Mesin>
)

data class Data(
    val mesin: Mesin
)

data class Mesin(
    val id_mesin: String,
    val nama_mesin: String,
    val tipe_mesin: String,
    val merek_mesin: String,
    val gambar_mesin: String,
    val id_pabrik: String
)
