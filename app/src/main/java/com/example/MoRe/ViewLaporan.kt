package com.example.MoRe

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MoRe.components.HasilTampilkan
import com.example.MoRe.model.DaftarLaporan
import com.example.MoRe.model.getDataLaporan
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.req.ReqLaporan
import com.example.MoRe.network.model.res.laporan.ResLaporanByName
import com.example.MoRe.network.model.res.laporan.ResLaporanChart
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp


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
    var responseLaporanChart by remember{
        mutableStateOf<ResLaporanChart?>(null)
    }

    suspend fun postLaporanByName(idPabrik: String, idMesin: String, nama: String, start: String, stop: String) {
        val repository = Repository()
        try{
            Log.d("ReqLaporan", ReqLaporan(nama, start, stop).toString())
            val response = repository.postLaporan(idPabrik, idMesin, ReqLaporan(nama, start, stop))
            responseLaporan = Resource.Success(response).data?.body()
//            Log.d("Respon laporan : ", responseLaporan?.data?.laporan.toString())
        } catch (e: Exception){
            Log.e("Error DetailMesin Laporan : ", e.message.toString())
        }
    }
    suspend fun postLaporanChart(idPabrik: String, idMesin: String, nama: String, start: String, stop: String){
        val repository = Repository()
        try{
            val response = repository.postLaporanChart(idPabrik, idMesin, ReqLaporan(nama, start, stop))
            responseLaporanChart = Resource.Success(response).data?.body()
        } catch (e: Exception){
        }
    }
    LaunchedEffect(Unit){
        postLaporanByName(idPabrik!!, idMesin!!, nama!!, start!!, stop!!)
        postLaporanChart(idPabrik!!, idMesin!!, nama!!, start!!, stop!!)
        tampilkanClickedState.value = !tampilkanClickedState.value
    }
    val composableScope = rememberCoroutineScope()
     // API STOP
    Scaffold(
        topBar = {
            CustomAppbar3(
                name = "Hasil Laporan",
                navController = navController,
                email = null,
                password = null,
                idPabrik = idPabrik,
                idMesin = idMesin,
            )
        },
        content = {
            Column(modifier = Modifier
                .padding(16.dp)
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
                    HasilTampilkan(resLaporan = responseLaporan?.data?.laporan!!, resChart= responseLaporanChart?.data?.laporan!!)
                } else{
                }
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
                    Log.d("idyg dilempar", "${idPabrik.toString()}, ${idMesin.toString()} ")
                    navController.navigate(MoReScreens.DetailScreen.name + "/${idPabrik}/${idMesin}"){

                    }

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