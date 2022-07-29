package com.example.MoRe.network.model.res.dokumen

data class ResGetDokumen (
    val status: String,
    val data: Data,
)

data class Data(
    val dokumen: Array<Doc>
)

data class Doc(
    val id_dokumen: String,
    val id_mesin: String,
    val nama: String,
    val dokumen: String
)