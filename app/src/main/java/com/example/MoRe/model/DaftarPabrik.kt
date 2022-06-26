package com.example.MoRe.model

import com.example.MoRe.R

data class DaftarPabrik(
    val  id : String,
    val namaPabrik : String,
    val alamatPabrik : String,
    val fotoPabrik: Int
)

fun getPabrik(): List<DaftarPabrik>{
    return listOf(
        DaftarPabrik(
            id ="0001",
            namaPabrik = "PT Numalos Abadi",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \n Sumatera Utara - Indonesia",
            R.drawable.numalos,
            ),
        DaftarPabrik(id ="0002",
            namaPabrik = "PT Numalos Abadi",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \n Sumatera Utara - Indonesia",
            R.drawable.numalos,
        ),
        DaftarPabrik(id ="0003",
            namaPabrik = "PT Numalos Abadi",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \nSumatera Utara - Indonesia",
            R.drawable.numalos,
        ),
        DaftarPabrik(id ="0004",
            namaPabrik = "PT Numalos Abadi",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \n Sumatera Utara - Indonesia",
            R.drawable.numalos,
        ),
    )
}