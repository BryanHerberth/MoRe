package com.example.MoRe.model

import com.example.MoRe.R

data class DaftarPabrik(
    val id : String,
    val namaPabrik : String,
    val alamatPabrik : String,
    val fotoPabrik: String
)

fun getPabrik(): List<DaftarPabrik>{
    return listOf(
        DaftarPabrik(
            id ="0001",
            namaPabrik = "PT Numalos Abadi",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \n Sumatera Utara - Indonesia",
            "https://pbs.twimg.com/media/FY5IcgTaMAA6C3n?format=jpg&name=medium",
            ),
        DaftarPabrik(id ="0002",
            namaPabrik = "PT Numalos Aqua",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \n Sumatera Utara - Indonesia",
            "https://i.picsum.photos/id/1/200/300.jpg?hmac=jH5bDkLr6Tgy3oAg5khKCHeunZMHq0ehBZr6vGifPLY",
        ),
        DaftarPabrik(id ="0003",
            namaPabrik = "PT Numalos Abadi",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \nSumatera Utara - Indonesia",
            "https://pbs.twimg.com/media/FYvnAF8aMAAjcyx?format=jpg&name=large",
        ),
        DaftarPabrik(id ="0004",
            namaPabrik = "PT Numalos Abadi",
            alamatPabrik = "Jl. Pulau Irian, No. 17 KIM I, Deli Serdang \n Sumatera Utara - Indonesia",
            "https://pbs.twimg.com/media/EbHOzkgUwAUMCGw?format=jpg&name=large",
        ),
    )
}