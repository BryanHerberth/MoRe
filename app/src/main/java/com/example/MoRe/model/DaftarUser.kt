package com.example.MoRe.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.MoRe.R

data class DaftarUser(
    val idUser: String,
    val namaUser: String,
    val emailUser : String,
    val tipeUser : String,
    val fotoUser : ImageVector
)


fun getUser(): List<DaftarUser> {
    return listOf(
        DaftarUser(
            idUser = "001",
            namaUser = "Erico",
            emailUser = "erico@mail.com",
            tipeUser = "Pemilik",
            fotoUser = Icons.Outlined.Person
        ),
        DaftarUser(
            idUser = "002",
            namaUser = "Enrico",
            emailUser = "enrico@mail.com",
            tipeUser = "Admin",
            fotoUser = Icons.Outlined.Person
        ),
        DaftarUser(
            idUser = "003",
            namaUser = "Carmen",
            emailUser = "carmen@mail.com",
            tipeUser = "Anggota",
            fotoUser = Icons.Outlined.Person
        ),
        DaftarUser(
            idUser = "004",
            namaUser = "Calli",
            emailUser = "calli@mail.com",
            tipeUser = "Anggota",
            fotoUser = Icons.Outlined.Person
        ),
    )
}
