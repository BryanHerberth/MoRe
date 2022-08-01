package com.example.MoRe.model

data class DaftarMonitoring(
    val jenisMonitoring : String,
    val satuanMonitoring : String,
    val valueMonitoring: String
)

fun getDataMonitor(): List<DaftarMonitoring>{
    return listOf(
        DaftarMonitoring(
            jenisMonitoring = "Kecepatan",
            satuanMonitoring = "I/min",
            valueMonitoring = "124"
        ),
        DaftarMonitoring(
            jenisMonitoring = "Kecepatan",
            satuanMonitoring = "I/min",
            valueMonitoring = "124"
        ),
        DaftarMonitoring(
            jenisMonitoring = "Kecepatan",
            satuanMonitoring = "I/min",
            valueMonitoring = "124"
        ),
        DaftarMonitoring(
            jenisMonitoring = "Kecepatan",
            satuanMonitoring = "I/min",
            valueMonitoring = "124"
        )
    )

}