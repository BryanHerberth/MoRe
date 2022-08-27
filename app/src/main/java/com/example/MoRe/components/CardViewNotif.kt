package com.example.MoRe.components

import android.app.Notification
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.MoRe.model.DaftarMesinNotif
import com.example.MoRe.model.getDataMesin
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.res.notifikasi.Notifikasi

@Composable
fun CardNotif(
    mesin : DaftarMesinNotif = getDataMesin()[0],
    resNotif: Notifikasi,
    onItemClick : (String) -> Unit ={},
    navController: NavController,
) {
    val idPabrik = rememberSaveable { mutableStateOf(resNotif.id_pabrik) }
    val idMesin = rememberSaveable { mutableStateOf(resNotif.id_mesin) }
    var myBorder = BorderStroke(2.dp, color = Color.LightGray)

    if(!resNotif.baca){
        myBorder = BorderStroke(2.dp, color = Color.Red)
    }
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(mesin.id)
                onItemClick(Log.d("Clicked","Clicked").toString())
                navController.navigate(MoReScreens.DetailScreen.name +"/${idPabrik.value}/${idMesin.value}")
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = myBorder
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
            ) {
            Surface(modifier = Modifier
                .padding(10.dp)
                .size(100.dp),
                shape = RoundedCornerShape(CornerSize(16.dp)),
                elevation = 5.dp
            ) {
                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(data = resNotif.gambar_mesin)
                    .crossfade(true)
                    .build(),
                    contentDescription = "Foto Pabrik",
                    contentScale = ContentScale.Fit
                )
            }
            Column(modifier = Modifier
                .padding(4.dp)
            ) {
                Text(
                    text = resNotif.nama_mesin,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = resNotif.nama_pabrik,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,

                )
                Text(
                    text = resNotif.text,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

