package com.example.MoRe.network.model.res.putUser

data class ResPutUser(
    val status: String,
    val data:DataUserId
)

data class DataUserId(
    val userId : String
)
