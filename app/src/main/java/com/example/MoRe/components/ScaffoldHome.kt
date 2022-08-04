package com.example.MoRe.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MoRe.SearchBar
import com.example.MoRe.ViewModel.SearchViewModel
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.SearchWidgetState
import com.example.MoRe.model.getPabrik
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.res.ResGetUser
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrik
import com.example.MoRe.network.model.res.getuser.User
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScaffoldHome(
    listPabrik: List<DaftarPabrik> = getPabrik(),
    searchViewModel: SearchViewModel,
    navController: NavController,
    email: String?,
    pass: String?,

) {
    // API START
    var responseGetPabrik by remember {
        mutableStateOf<ResGetPabrik?>(null)
    }
    suspend fun getPabrik(){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO){
                val response = repository.getPabrik()
                launch(Dispatchers.Main){
                    responseGetPabrik = Resource.Success(response).data?.body()
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
    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState
    Scaffold(
        topBar = {
            SwitchAppbar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                               searchViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                                 searchViewModel.updateSearchTextState(newValue = "")
                                searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked ={
                                 Log.d("Searched text", it)
                    searchViewModel.searchPabrikList(it)
                },
                onSearchTriggered = {
                    searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                },
                navController = navController
            )
        },
        content = {
            Surface(modifier =
            Modifier
                .background(BlueApp)
                .fillMaxWidth()
                .fillMaxHeight()
                ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(BlueApp),
                    hint = "telusuri"
                )
                Card() {
                    LazyColumn{
                        responseGetPabrik?.data?.let { it1 ->
                            items(items = it1.pabrik){
                                Log.d("pabrik ke - :", it.toString())
                                CardPabrik(resPabrik = it, navController = navController)
                            }
                        }
                    }
//                    LazyColumn{
//                        items(items = listPabrik)
//                        {
//                            CardPabrik(pabrik = it, navController = navController)
//                        }
//                    }
                }
                Spacer(modifier = Modifier.height(235.dp))
            }
        },
    )
}

@Composable
fun SwitchAppbar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit,
    navController: NavController
){
    when(searchWidgetState){
        SearchWidgetState.CLOSED -> {
            AppBarCompose (
                onSearchClicked = onSearchTriggered,
                navController = navController,
                idPabrik = null,
                idMesin = null)
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun AppBarCompose( onSearchClicked: () -> Unit,
                   navController: NavController,
                   idPabrik: String?,
                   idMesin: String?) {
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
                navController.navigate(MoReScreens.ProfileScreen.name){
//                    popUpTo(MoReScreens.HomeScreen.name)
//                    {
//                        inclusive = true
//                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Akun",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onSearchClicked()
            }){
                Icon(imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White)
            }
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

