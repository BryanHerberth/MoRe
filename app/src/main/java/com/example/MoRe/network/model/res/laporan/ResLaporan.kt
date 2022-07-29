package com.example.MoRe.network.model.res.laporan

data class ResGetVarLaporan(
    val status: String,
    val data: DataVal
)

data class DataVal(
    val variable : Array<String>
)

//data class ResGetLaporan(
//
//)
