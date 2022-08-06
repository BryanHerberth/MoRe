package com.example.MoRe.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
//import com.example.MoRe.SearchBar
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.getPabrik
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.res.DataPabrik
import com.example.MoRe.network.model.res.ResGetUser
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrik
import com.example.MoRe.network.model.res.getuser.User
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun ScaffoldHome(
    listPabrik: List<DaftarPabrik> = getPabrik(),
    navController: NavController,
    email: String?,
    pass: String?,

) {
    // API START
    var responseGetPabrik by remember {
        mutableStateOf<ResGetPabrik?>(null)
    }
    var listPabrik by remember {
        mutableStateOf<ArrayList<DataPabrik>?>(null)
    }
    suspend fun getPabrik(){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO){
                val response = repository.getPabrik()
                launch(Dispatchers.Main){
                    responseGetPabrik = Resource.Success(response).data?.body()
                    listPabrik = responseGetPabrik?.data?.pabrik
                    Log.d("Response Pabrik : ", responseGetPabrik?.data?.pabrik.toString())
                }
            }
        }
    }
    var responsegetUser by remember {
        mutableStateOf<ResGetUser?>(null)
    }
    var activeUser by remember {
        mutableStateOf<User?>(User("", "", "", "", "", true, ""))
    }

    suspend fun getUser(){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                try{
                    val response =  repository.getUser()
                    launch(Dispatchers.Main) {
                        responsegetUser = Resource.Success(response).data?.body()
                        SessionManager.saveUser(responsegetUser?.data?.user!!)
                        Log.d("User from shared reference : ", SessionManager.getUserData().toString())
                    }
                } catch (e: Exception){
                    Log.e("error get User on UserProfile.kt : ", e.message.toString())
                }
            }
        }
    }

    LaunchedEffect(Unit){
        getPabrik()
        getUser()
    }

    // API STOP

    Log.d("TAG", "ScaffoldHome: $email")
    Log.d("TAG", "ScaffoldHome: $pass")
    Scaffold(
        topBar = {
                 AppBarCompose(
//                     onSearchClicked = {  },
                     navController = navController,
                     idPabrik = null,
                     idMesin = null,
                     resPabrik = listPabrik
                 )
        },
        content = {
            Surface(modifier =
            Modifier
                .background(BlueApp)
                .fillMaxWidth()
                .fillMaxHeight()
                ) {
//                SearchBar(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp)
//                        .background(BlueApp),
//                    hint = "telusuri"
//                )
                Card() {
//                    val searchedPabrik = searchTextState
//                    var filteredPabrik: ArrayList<String>
//                    val listPabrik = responseGetPabrik?.data?.pabrik.toString()
                    LazyColumn {
//                        if (searchedPabrik.isEmpty()) {
                        responseGetPabrik?.data?.let { it1 ->
                            items(items = it1.pabrik) {
                                Log.d("pabrik ke - :", it.toString())
                                CardPabrik(
                                    resPabrik = it,
                                    navController = navController
                                )
                            }
                        }
                    }
//                            }
//                            Log.d("TAG", "$listPabrik: ")
//                        } else {
//                            val resultList = ArrayList<String>()
////                            if (listPabrik != null) {
//                                for (nama_pabrik in listPabrik ) {
//                                    if (searchTextState.lowercase(Locale.getDefault())
//                                            .contains(searchTextState.lowercase(Locale.getDefault()))
//                                    ) {
//                                        resultList.add(nama_pabrik.toString())
////                                    listPabrik.add(listPabrik[0])
//                                    }
//                                }
////                            }
//
//                            resultList
//                            Log.d("pabrik ke - :", "{${resultList}}" )
//
//                        }
////                        responseGetPabrik?.data?.let { it1 ->
////                            items(items = it1.pabrik){
////                                Log.d("pabrik ke - :", it.toString())
////                                CardPabrik(resPabrik = it, navController = navController)
////                            }
////                        }
////                    }
//                    LazyColumn{
//                        items(items = listPabrik)
//                        {
//                            CardPabrik(pabrik = it, navController = navController)
//                        }
//                    }

//                Spacer(modifier = Modifier.height(235.dp))
            }
        }


}
    )
}






@Composable
fun AppBarCompose(
//    onSearchClicked: () -> Unit,
                   navController: NavController,
                   idPabrik: String?,
                   idMesin: String?,
                   resPabrik: ArrayList<DataPabrik>?
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "Daftar Pabrik",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(MoReScreens.ProfileScreen.name)
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Akun",
                    tint = Color.White
                )
            }
        },
        actions = {
//            IconButton(onClick = {
//                navController.navigate(MoReScreens.SearchScreen.name)
//            }){
//                Icon(imageVector = Icons.Filled.Search,
//                    contentDescription = "Search",
//                    tint = Color.White)
//            }
            IconButton(onClick = {
                navController.navigate(MoReScreens.NotifScreen.name +"/$idPabrik/${idMesin}")
            }) {
                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "notifications",
                    tint = Color.White
                )
            }
        },
        backgroundColor = BlueApp
    )
}

