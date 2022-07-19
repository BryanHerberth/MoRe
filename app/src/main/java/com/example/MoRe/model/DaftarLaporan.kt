package com.example.MoRe.model

data class DaftarLaporan(
    val Laporan : String ,
    val Nomor: Int,
    val TanggalMulai :String,
    val TanggalBerhenti: String
){
    override fun toString(): String {
        return "$Laporan "
    }
}
