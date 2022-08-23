package com.example.MoRe.model

data class LineChartEntity(
    val value: Float,
    val label: String? = "",
    val isZoomAllowed: Boolean = true,
)
