package com.example.MoRe.model

import com.example.MoRe.R

data class DaftarPabrik(
    val id : String,
    val namaPabrik : String,
    val alamatPabrik : String,
    val fotoPabrik: String
)

fun getPabrik(): ArrayList<DaftarPabrik>{
    return arrayListOf(
        DaftarPabrik(
            id ="0001",
            namaPabrik = "PT Numalos Abadi",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \n Sumatera Utara - Indonesia",
            "https://i.picsum.photos/id/1000/5626/3635.jpg?hmac=qWh065Fr_M8Oa3sNsdDL8ngWXv2Jb-EE49ZIn6c0P-g",
            ),
        DaftarPabrik(id ="0002",
            namaPabrik = "PT Aqua",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \n Sumatera Utara - Indonesia",
            "https://pbs.twimg.com/media/FYpVBlKaIAELber?format=jpg&name=4096x4096",
        ),
        DaftarPabrik(id ="0003",
            namaPabrik = "PT Abadi",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \nSumatera Utara - Indonesia",
            "https://pbs.twimg.com/media/FYvnAF8aMAAjcyx?format=jpg&name=large",
        ),
        DaftarPabrik(id ="0004",
            namaPabrik = "PT Abadi 1234",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \n Sumatera Utara - Indonesia",
            "https://pbs.twimg.com/media/EbHOzkgUwAUMCGw?format=jpg&name=large",
        ),
    )
}