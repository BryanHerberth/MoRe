package com.example.MoRe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.MoRe.components.*
//import com.example.MoRe.components.SwitchAppbar
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.model.*
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.res.DataPabrik
import com.example.MoRe.network.model.res.getmember.ResGetMember
import com.example.MoRe.network.model.res.getmesin.ResGetMesin
import com.example.MoRe.network.model.res.getpabrik.ResGetPabrikById
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScaffoldListMesin(
    listMesin: List<DaftarMesinNotif> = getDataMesin(),
    pabrik: DaftarPabrik = getPabrik()[0],
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    navController: NavController,
    idPabrik: String?,

) {
    Log.d("idPabrik On Daftar Mesin : ", idPabrik.toString())
      val myPabrik = SessionManager.getPabrikData()

//    Log.e("myPabrik := ", myPabrik.toString())
//    val activePabrik = rememberSaveable { mutableStateOf(SessionManager.getPabrikData())}
//    Log.d("TAG", "ScaffoldHome: $idPabrik")
//    Log.d("Session Pabrik : ", activePabrik.toString())

    // API START
    var responseGetPabrikById by remember{
        mutableStateOf<ResGetPabrikById?>(null)
    }
    var responseGetMesin by remember{
        mutableStateOf<ResGetMesin?>(null)
    }

    suspend fun getPabrikById(idPabrik: String?){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                Log.e("error : ", "Sebelum response $idPabrik")
                try{
                    val response = repository.getParikById(idPabrik ?: "")
                    Log.e("error : ", "setelah response")
                    launch(Dispatchers.Main){
                        responseGetPabrikById = Resource.Success(response).data?.body()
                        Log.d("ResponseGetPabrikById : ", response.body().toString())
                    }
                } catch (e: Exception){
                    Log.e("Error getPabrikById : ", e.message.toString())
                }

            }
        }
    }

    suspend fun getMesin(idPabrik: String?) {
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                val response = repository.getMesin(idPabrik ?: "")
                launch (Dispatchers.Main){
                    responseGetMesin = Resource.Success(response).data?.body()
                    Log.d("Response Mesin : ", responseGetMesin.toString())
                }
            }
        }
    }

    LaunchedEffect(Unit){
//        getPabrikById(idPabrik)
        getMesin(idPabrik)
    }

    // API STOP




    Scaffold(
    topBar = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueApp)

        ) {
            MesinAppBar(onSearchClicked = { /*TODO*/ },
                navController = navController,
                email = null,
                password = null  )
        }
    })
    {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                shape = MaterialTheme.shapes.small,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {},
                elevation = 8.dp
            ) {
                Column() {
                    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
//                        .data(data = pabrik.fotoPabrik)
                        .data(data = myPabrik?.gambar_pabrik)
                        .crossfade(true)
                        .build(),
                        contentDescription = "Foto Pabrik",
                        contentScale = ContentScale.Fit)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row() {
                            myPabrik?.nama_pabrik?.let { it1 ->
                                Text(
                                    text = it1,
                                    style = MaterialTheme.typography.h6
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(onClick = {
                                    navController.navigate(MoReScreens.MemberScreen.name +"/$idPabrik")

                                }) {
                                    Icon(
                                        imageVector = Icons.Outlined.AccountCircle,
                                        contentDescription = "User",
                                    )
                                }
                            }
                        }
                        Text(
//                            text = pabrik.alamatPabrik,
                            text = "${myPabrik?.alamat_pabrik}, ${myPabrik?.kab_kota_pabrik}, ${myPabrik?.provinsi_pabrik}",
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Daftar Mesin",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    LazyColumn {
                        responseGetMesin?.data?.let { it1 ->
                            items(items = it1.mesin){
                                CardMesin(resMesin=it, navController = navController, idPabrik = idPabrik)
                            }
                        }
//                        items(items = listMesin)
//                        {
//                            CardMesin(mesin = it, navController = navController, idPabrik = idPabrik)
//                        }
                    }
                }
            }
        }
    }
}
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
@ExperimentalMaterialApi
fun ModalBottomSheet(
    listUser: List<DaftarUser> = getUser(),
//    activityContentScope: @Composable (state: ModalBottomSheetState , scope: CoroutineScope ) -> Unit
//    StatusUser: String?
    ) {
    Log.e("Button Sheat", "Active")

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue =ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()

    val context = LocalContext.current
//    val newUserList = getUser().filter { pengguna ->
//        pengguna.tipeUser == StatusUser
//    }
    val ActivePabrik = SessionManager.getPabrikData()
    // START API ----------------------------------
    var responseGetMember by remember {
        mutableStateOf<ResGetMember?>(null)
    }
    suspend fun getMember(){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO){
                val response = repository.getMember(idPabrik = ActivePabrik?.id_pabrik!!)
                launch(Dispatchers.Main){
                    responseGetMember = Resource.Success(response).data?.body()
                    Log.d("Response member :-> ", responseGetMember?.data?.anggota.toString())
                }
            }
        }
    }

    LaunchedEffect(Unit){
        getMember()
    }
    // STOP API -----------------------------------
    ModalBottomSheetLayout(

        sheetState = modalBottomSheetState,
        sheetContent = {

        }
    ) {
//        activityContentScope(modalBottomSheetState, scope)
    }
}




@Composable
fun MesinAppBar( onSearchClicked: () -> Unit,
                 navController: NavController,
                 email: String?,
                 password: String?) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(
                text = "",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(10.dp, 0.dp)
            )

        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(MoReScreens.HomeScreen.name +"/${email}/${password}") {
                    popUpTo(MoReScreens.HomeScreen.name +"/${email}/${password}")
                    {
                        inclusive = true
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Arrow",
                    tint = Color.White
                )
            }
        },
//        actions = {
//            IconButton(onClick = {
//                onSearchClicked()
//            }) {
//                Icon(
//                    imageVector = Icons.Filled.Search,
//                    contentDescription = "Search",
//                    tint = Color.White
//                )
//            }
//        },
        backgroundColor = BlueApp
    )
}

//@OptIn(ExperimentalMaterialApi::class)
//@Preview(showBackground = true)
//@Composable
//fun DaftarMesinPreview() {
//    MesinAppBar (onSearchClicked ={} )
//    ModalBottomSheet{
//            state: ModalBottomSheetState, scope: CoroutineScope ->
//        ScaffoldListMesin(searchViewModel = SearchViewModel(), scope = scope,
//            modalBottomSheetState = state)
//    }
//}
