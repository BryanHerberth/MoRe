package com.example.MoRe.network.model.res.getpabrik

import com.example.MoRe.network.model.res.DataPabrik

data class ResGetPabrik(
    val status: String,
    val data: DataArr

)
data class ResGetPabrikByName(
    val status: String,
    val data: DataArr
)

data class ResGetPabrikById(
    val status: String,
    val data: Data
)

data class DataArr(
    val pabrik: ArrayList<DataPabrik>
)
data class Data(
    val pabrik: Array<DataPabrik>
)
