package com.example.MoRe.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.model.DaftarMesinNotif
import com.example.MoRe.model.getDataMesin
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.res.getmesin.Mesin
import com.example.MoRe.network.model.res.monitor.Monitor

@Composable
fun CardMesin(
    resMesin : Mesin = Mesin("", "", "", "", "", ""),
    onItemClick : (String) -> Unit ={},
    navController: NavController,
    idPabrik :String?,
    clickable: Boolean = true,
    showSts: Boolean = false,
    statusMesin: Boolean = false
){
    val idMesin = rememberSaveable { mutableStateOf(resMesin.id_mesin) }
    SessionManager.saveMesin(resMesin)
    var tempModifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            navController.navigate(MoReScreens.DetailScreen.name + "/$idPabrik/${idMesin.value}")
        }

    var online = statusMesin

    if(!clickable){
        tempModifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    }
    Card(modifier = tempModifier,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(2.dp, color = Color.LightGray)
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
                    .data(data = resMesin.gambar_mesin)
                    .crossfade(true)
                    .build(),
                    contentDescription = "Foto Mesin",
                    contentScale = ContentScale.Fit // Perlu di sesuaiikannnn
                )
            }
            Column(modifier = Modifier
                .padding(4.dp)
            ) {
                Text(
                    text = resMesin.nama_mesin,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = resMesin.tipe_mesin,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,

                    )
                Text(
                    text = resMesin.merek_mesin,
                    style = MaterialTheme.typography.caption
                )
                if(showSts){
                    if (online) {
                        Text(
                            text = "Online",
                            color = Color.Green,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.subtitle1
                        )
                    } else{
                        Text(

                            text = "OFFLINE",
                            color = Color.Red,
                            style = MaterialTheme.typography.caption
                        )
                    }
                }

            }
        }
    }
}

