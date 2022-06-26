package com.example.MoRe.model

import com.example.MoRe.R

data class DaftarMesinNotif(
    val  id : String,
    val namaMesin : String,
    val namaPabrik: String ,
    val pesan : String,
    val fotoMesin: Int
)

fun getDataMesin() : List<DaftarMesinNotif>{
    return listOf(
        DaftarMesinNotif(
            id ="M-01",
            namaMesin = "Flottweg-01",
            namaPabrik = "Pt. Numalos Abadi",
            pesan = "Mesin Normal",
            R.drawable.machine,
        ),
        DaftarMesinNotif(
            id ="M-02",
            namaMesin = "Flottweg-02",
            namaPabrik = "Pt. Numalos Abadi",
            pesan = "Mesin Normal",
            R.drawable.machine,
        ),
        DaftarMesinNotif(
            id ="M-03",
            namaMesin = "Flottweg-03",
            namaPabrik = "Pt. Numalos Abadi",
            pesan = "Mesin Normal",
            R.drawable.machine,
        ),
        DaftarMesinNotif(
            id ="M-04",
            namaMesin = "Flottweg-04",
            namaPabrik = "Pt. Numalos Abadi",
            pesan = "Mesin Normal",
            R.drawable.machine,
        ),
        DaftarMesinNotif(
            id ="M-05",
            namaMesin = "Flottweg-05",
            namaPabrik = "Pt. Numalos Abadi",
            pesan = "Mesin Normal",
            R.drawable.machine,
        ),
        DaftarMesinNotif(
            id ="M-06",
            namaMesin = "Flottweg-06",
            namaPabrik = "Pt. Numalos Abadi",
            pesan = "Mesin Normal",
            R.drawable.machine,
        ),
    )
}
