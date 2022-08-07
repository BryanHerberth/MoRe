package com.example.MoRe.network.model.res.getmesin

data class ResGetStatusMesin(
    val status: String,
    val data: DataStsMesin
)

data class DataStsMesin(
    val online: Boolean,
)