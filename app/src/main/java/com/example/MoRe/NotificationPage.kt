package com.example.MoRe

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MoRe.components.CardNotif
import com.example.MoRe.components.StatusBarchange
import com.example.MoRe.model.DaftarMesinNotif
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.getDataMesin
import com.example.MoRe.model.getPabrik
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.res.notifikasi.ResGetNotifikasi
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch



@Composable
fun ScaffoldNotif(
    listMesin: List<DaftarMesinNotif> = getDataMesin(),
    navController: NavController,
    idPabrik: String?,
    idMesin: String?
){
    val idMesin = rememberSaveable { mutableStateOf("2") }
    // API STRAT
    var responseNotifikasi by remember{
        mutableStateOf<ResGetNotifikasi?>(null)
    }

    suspend fun getNotifikasi() {
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                val response = repository.getNotifikasi()
                launch(Dispatchers.Main){
                    responseNotifikasi = Resource.Success(response).data?.body()
                    Log.d("Response Get Notifikasi : ", responseNotifikasi.toString())
                }
            }
        }
    }
    LaunchedEffect(Unit){
        getNotifikasi()
    }

    // API STOP


    Scaffold(
        topBar = {
            CustomAppbar(name = "Notifikasi", navController = navController, email = null , password = null, idPabrik = null)
        },
        content = {
            Column(modifier = Modifier.padding(12.dp)) {

                LazyColumn{
                    responseNotifikasi?.data?.let{it1->
                        items(items = it1.notifikasi){
                            CardNotif(resNotif =it,navController = navController)
                        }
                    }
//                    items(items = listMesin)
//                    {
//                        CardNotif(mesin = it, navController = navController)
//                    }
                }
            }
        }
    )
}


