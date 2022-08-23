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

data class ResLaporanChart(
    val status: String,
    val data: DataLaporanChart
)

data class DataLaporanChart(
    val laporan: List<lapo>
)

data class lapo(
    val nomor: String,
    val value: Int,
    val timestamp: String
)
