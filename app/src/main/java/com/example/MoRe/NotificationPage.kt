package com.example.MoRe

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MoRe.components.CardNotif
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.res.notifikasi.ResGetNotifikasi
import com.example.MoRe.network.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun ScaffoldNotif(
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


