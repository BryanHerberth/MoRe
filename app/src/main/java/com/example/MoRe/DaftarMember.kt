package com.example.MoRe

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MoRe.components.CardUser
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.res.getmember.ResGetMember
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScaffoldMember(
    navController: NavController,
    idPabrik: String?,

){
    Log.d("idPabrik DaftarMember : ", idPabrik.toString())
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
                idPabrik = idPabrik,
                modifier = Modifier)
        },
        content = {
            Column(modifier = Modifier.padding(12.dp)) {

                LazyColumn{

//                    val sorted = listMember.groupBy { it.tipeUser}
                    val sorted = responseGetMember?.data?.anggota?.groupBy { it.status }
                    Log.d("Soetir Anggota :: ", sorted.toString())
                    if (sorted != null) {
                        sorted.forEach { (status, initial ) ->
                            Log.d("sorted : ", initial[0].toString())
                            stickyHeader {
                                Text(text = "$status",
                                    style = MaterialTheme.typography.h4,
                                )
                            }
                            initial.let { it1 ->
                                items(items = it1){
                                    CardUser(resPengguna = it)
                                }
                            }


                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CustomAppbar2(name:String,
                  navController: NavController,
                  email : String?,
                  password : String?,
                  idPabrik: String?,
                  modifier: Modifier
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
                    modifier = Modifier.padding(90.dp, 0.dp)
                )
            }
        }, backgroundColor = BlueApp, navigationIcon = {
            IconButton(onClick = {
                navController.navigate(MoReScreens.PabrikScreen.name + "/${idPabrik}") {}
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