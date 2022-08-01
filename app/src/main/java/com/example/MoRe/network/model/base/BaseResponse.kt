package com.example.MoRe.network.model.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<D, E>(
    @SerializedName("success")
    val isSuccess: Boolean,
    @SerializedName("data")
    val data: D,
    @SerializedName("errors")
    val errorMsg: E
)
