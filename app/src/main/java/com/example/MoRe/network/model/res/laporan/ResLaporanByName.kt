package com.example.MoRe.network.model.res.laporan

data class ResLaporanByName(
    val status: String,
    val data: DataLaporan
)

data class DataLaporan(
    val laporan: List<LaporanByName>
)

data class LaporanByName(
    val nomor: String,
    val nama: String,
    val value: String,
    val timestamp: String
)
