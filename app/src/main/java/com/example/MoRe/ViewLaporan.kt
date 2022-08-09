package com.example.MoRe

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MoRe.components.CardUser
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.ui.theme.BlueApp


@Composable
fun LaporanView(
    navController: NavController,
    idPabrik : String?,
    idMesin: String?,
    nama: String?,
    start: String?,
    stop: String?,

) {
    Log.d("TAG", "LaporanView: $idPabrik, $idMesin,$nama,$start,$stop")
    Scaffold(
        topBar = {
            CustomAppbar3(
                name = "Hasil Laporan",
                navController = navController,
                email = null,
                password = null,
                idPabrik = null,
                idMesin = null,
            )
        },
        content = {
            Column(modifier = Modifier.padding(12.dp)) {

                LazyColumn {

////                    val sorted = listMember.groupBy { it.tipeUser}
//                    val sorted = responseGetMember?.data?.anggota?.groupBy { it.status }
//                    if (sorted != null) {
//                        sorted.forEach { (status, id_pengguna ) ->
//                            stickyHeader {
//                                Text(text = "$status",
//                                    style = MaterialTheme.typography.h4,
//                                )
//                            }
//                            responseGetMember?.data?.let { it1 ->
//                                items(items = it1.anggota){
//                                    CardUser(resPengguna = it)
//                                }
                }

//                            items(items = pengguna)
//                            {
//
//                                CardUser(pengguna = it)
//                            }
            }
        },
    )
}

@Composable
fun CustomAppbar3(name:String,
                  navController: NavController,
                  email : String?,
                  password : String?,
                  idPabrik: String?,
                  idMesin: String?,
) {
    Column {
        TopAppBar(title = {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "$name",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(70.dp, 0.dp)
                )
            }
        },
            backgroundColor = BlueApp,

            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(MoReScreens.DetailScreen.name + "/${idPabrik}/${idMesin}")
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Arrow",
                        tint = Color.White
                    )
                }
            })
    }
}