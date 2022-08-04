package com.example.MoRe

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MoRe.components.CardUser
import com.example.MoRe.model.DaftarUser
import com.example.MoRe.model.getUser
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.res.getmember.ResGetMember
import com.example.MoRe.network.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScaffoldMember(
    listMember: List<DaftarUser> = getUser(),
    navController: NavController,
    idPabrik: String?,

){
    Log.d("idPabrik DaftarMember : ", idPabrik.toString())
//    val idPabrik = rememberSaveable { mutableStateOf("2") }
    // API START
    var responseGetMember by remember{
        mutableStateOf<ResGetMember?>(null)
    }

    suspend fun getMember(idPabrik: String?){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                try{
                    val response = repository.getMember(idPabrik ?: "")
                    launch(Dispatchers.Main) {
                        responseGetMember = Resource.Success(response).data?.body()
                        Log.d("Response get Member : ", responseGetMember.toString())
                    }
                } catch (e: Exception){
                    Log.e("Error getMember on DaftarMember.kt : ", e.message.toString())
                }
            }
        }
    }

    LaunchedEffect(Unit){
        getMember(idPabrik = idPabrik)
    }

    // API STOP

    Scaffold(
        topBar = {
            CustomAppbar2(name = "Member",
                navController = navController,
                email = null ,
                password = null,
                idPabrik = null)
        },
        content = {
            Column(modifier = Modifier.padding(12.dp)) {

                LazyColumn{

//                    val sorted = listMember.groupBy { it.tipeUser}
                    val sorted = responseGetMember?.data?.anggota?.groupBy { it.status }
                    if (sorted != null) {
                        sorted.forEach { (status, id_pengguna ) ->
                            stickyHeader {
                                Text(text = "$status",
                                    style = MaterialTheme.typography.h4,
                                )
                            }
                            responseGetMember?.data?.let { it1 ->
                                items(items = it1.anggota){
                                    CardUser(resPengguna = it)
                                }
                            }

//                            items(items = pengguna)
//                            {
//
//                                CardUser(pengguna = it)
//                            }
                        }
                    }
                }
            }
        }
    )
}