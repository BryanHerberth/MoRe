package com.example.MoRe.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.getPabrik
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.res.DataPabrik
import com.google.gson.Gson


@Composable
fun CardPabrik(
//    pabrik: DaftarPabrik = getPabrik()[0],
    resPabrik: DataPabrik,
    onItemClick : (String) -> Unit ={},
    navController: NavController
){
    val idPabrik = rememberSaveable { mutableStateOf(resPabrik.id_pabrik) }

    Box(modifier = Modifier
        .fillMaxHeight()
        .wrapContentSize()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .padding(12.dp)
            .wrapContentSize(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)

        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        try{
                            SessionManager.savePabrik(resPabrik)
                            navController.navigate(MoReScreens.PabrikScreen.name+"/${idPabrik.value}")
                        } catch (e: Exception){
                            Log.e("Error nav daftarMesin",e.message.toString())
                        }

                    }
                    .wrapContentHeight(),
                elevation = 8.dp
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
//                        .data(data = pabrik.fotoPabrik)
                        .data(data = resPabrik.gambar_pabrik)
                        .crossfade(true)
                        .build(),
                        alignment = Alignment.Center,
                        contentDescription = "Foto Pabrik",
                        contentScale = ContentScale.Fit // Perlu di sesuaiikannnn
                    )
//                    Image(painter = painterResource(id = pabrik.fotoPabrik),
//                        contentDescription = "foto pabrik",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                        ,
//                        contentScale = ContentScale.Fit
//                    )
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                        Text(text = pabrik.namaPabrik,
//                            style = MaterialTheme.typography.h6
//                        )
//
//                        Text(text = pabrik.alamatPabrik,
//                            style = MaterialTheme.typography.caption
//                        )
                        Text(text = resPabrik!!.nama_pabrik,
                            style = MaterialTheme.typography.h6
                        )
                        Text(
                            text = "${resPabrik?.alamat_pabrik}, ${resPabrik.kab_kota_pabrik}, ${resPabrik.provinsi_pabrik}",
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }
        }
    }
}