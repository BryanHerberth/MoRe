package com.example.MoRe.model

data class DaftarLaporan(
    val Nomor: String,
    val Laporan : String ,
    val TanggalMulai :String,
    val DataLaporan : String,

    ){

}

fun getDataLaporan() : List<DaftarLaporan>{
    return listOf(
        DaftarLaporan(
            Nomor = "1",
            Laporan = "Kecepatan Mesin",
            TanggalMulai = "20/7/2022",
            DataLaporan = "90%"
        ),
        DaftarLaporan(
            Nomor = "2",
            Laporan = "Kecepatan Mesin",
            TanggalMulai = "21/7/2022",
            DataLaporan = "85%"
        ),
        DaftarLaporan(
            Nomor = "3",
            Laporan = "Hasil Produksi",
            TanggalMulai = "20/7/2022",
            DataLaporan = "124 "
        ),
        DaftarLaporan(
            Nomor = "4",
            Laporan = "Hasil Produksi",
            TanggalMulai = "21/7/2022",
            DataLaporan = "109"
        ),

    )
}


