package com.example.MoRe.model

import com.example.MoRe.network.model.res.DataPabrik

data class PabrikSearchState(
    val searchText: String = "",
    val listDataPabrik: List<DataPabrik> = arrayListOf(),
){
    companion object {
        val Empty = PabrikSearchState()
    }

}
