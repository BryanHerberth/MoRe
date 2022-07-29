package com.example.MoRe.network.model.res.monitor

data class ResGetMonitor(
    val status: String,
    val data: Data
)

data class Data(
    val monitor: Array<Monitor>
)

data class Monitor(
    val nama: String,
    val satuan: String,
    val alarm: Boolean,
    val enableAlarm: Boolean,
    val min: Int,
    val max: Int,
    val value: Int
)
