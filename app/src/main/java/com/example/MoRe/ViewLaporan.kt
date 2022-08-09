package com.example.MoRe

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MoRe.components.CardUser
import com.example.MoRe.components.HasilTampilkan
import com.example.MoRe.model.DaftarLaporan
import com.example.MoRe.model.getDataLaporan
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.req.ReqLaporan
import com.example.MoRe.network.model.res.laporan.ResLaporanByName
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LaporanView(
    navController: NavController,
    idPabrik : String?,
    idMesin: String?,
    nama: String?,
    start: String?,
    stop: String?,
    laporan: List<DaftarLaporan> = getDataLaporan(),
) {
    val tampilkanClickedState = remember {
        mutableStateOf(false)
    }
    // API START
    var responseLaporan by remember{
        mutableStateOf<ResLaporanByName?>(null)
    }

    suspend fun postLaporanByName(idPabrik: String, idMesin: String, nama: String, start: String, stop: String) {
        val repository = Repository()
        try{
            Log.d("ReqLaporan", ReqLaporan(nama, start, stop).toString())
            val response = repository.postLaporan(idPabrik, idMesin, ReqLaporan(nama, start, stop))
            responseLaporan = Resource.Success(response).data?.body()
            Log.d("Respon laporan : ", responseLaporan?.data?.laporan.toString())
        } catch (e: Exception){
            Log.e("Error DetailMesin Laporan : ", e.message.toString())
        }
    }
    LaunchedEffect(Unit){
//        postLaporanByName(idPabrik!!, idMesin!!, nama!!, start!!, stop!!)
        Log.d("LaunchedEffect", "error repet..................")
        postLaporanByName(idPabrik!!, idMesin!!, nama!!, start!!, stop!!)
        tampilkanClickedState.value = !tampilkanClickedState.value
    }
    val composableScope = rememberCoroutineScope()
     // API STOP
//    Log.d("TAG", "LaporanView: $idPabrik, $idMesin, $nama, $start, $stop")
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
            Column(modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .border(BorderStroke(2.dp, Color.LightGray))
                    .padding(12.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = "Variable : $nama",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(6.dp)
                    )
                    Text(text = "Start : $start",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(7.dp)
                    )
                    Text(text = "Stop : $stop",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(7.dp)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (tampilkanClickedState.value){
                    HasilTampilkan(resLaporan = responseLaporan?.data?.laporan!!)
                } else{
//        Box() {}
                }
//                composableScope.launch {
//                    postLaporanByName(idPabrik!!, idMesin!!, nama!!, start!!, stop!!)
//                    tampilkanClickedState.value = !tampilkanClickedState.value
//                }


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