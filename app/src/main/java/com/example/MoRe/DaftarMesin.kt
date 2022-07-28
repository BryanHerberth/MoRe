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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.MoRe.ViewModel.SearchViewModel
import com.example.MoRe.components.*
import com.example.MoRe.components.SwitchAppbar
import com.example.MoRe.model.*
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

//class DaftarMesin : ComponentActivity() {
//    @OptIn(ExperimentalMaterialApi::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//
//        val searchViewModel: SearchViewModel by viewModels()
//
//
//
//        super.onCreate(savedInstanceState)
//        setContent {
//
//            val systemUiController = rememberSystemUiController()
//            val useDarkIcons = MaterialTheme.colors.isLight
//
//            SideEffect {
//                systemUiController.setSystemBarsColor(
//                    color = BlueApp,
//                    darkIcons = useDarkIcons
//                )
//            }
//            ModalBottomSheet{
//                state: ModalBottomSheetState, scope: CoroutineScope ->
//                ScaffoldListMesin(searchViewModel = SearchViewModel(), scope = scope,
//                    modalBottomSheetState = state)
//            }
//        }
//    }
//}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScaffoldListMesin(
    searchViewModel: SearchViewModel,
    listMesin: List<DaftarMesinNotif> = getDataMesin(),
    pabrik: DaftarPabrik = getPabrik()[0],
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
    navController: NavController,
    idPabrik: String?,
//                      bottomSheetScaffoldState: BottomSheetScaffoldState

) {
    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState
    Log.d("TAG", "ScaffoldHome: $idPabrik")


    Scaffold(
    topBar = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlueApp)

        ) {

            SwitchBar2(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    searchViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    searchViewModel.updateSearchTextState(newValue = "")
                    searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    Log.d("Searched text", it)
                },
                onSearchTriggered = {
                    searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                },
                navController = navController
            )
        }
    }
) {
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
                        .data(data = pabrik.fotoPabrik)
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
                            Text(
                                text = pabrik.namaPabrik,
                                style = MaterialTheme.typography.h6
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(onClick = {

                                    scope.launch {
                                        modalBottomSheetState.show()
                                    }
                                    Log.d(TAG, "ScaffoldListMesin: Scafoold where")
                                }) {
                                    Icon(
                                        imageVector = Icons.Outlined.AccountCircle,
                                        contentDescription = "User",
                                    )
                                }
                            }
                        }
                        Text(
                            text = pabrik.alamatPabrik,
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
                        items(items = listMesin)
                        {
                            CardMesin(mesin = it, navController = navController, idPabrik = idPabrik)
                        }
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
    activityContentScope: @Composable (state: ModalBottomSheetState , scope: CoroutineScope ) -> Unit
//    StatusUser: String?
    ) {

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue =ModalBottomSheetValue.Hidden
    )

    val scope = rememberCoroutineScope()

    val context = LocalContext.current
//    val newUserList = getUser().filter { pengguna ->
//        pengguna.tipeUser == StatusUser
//    }
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            LazyColumn{

                val sorted = listUser.groupBy { it.tipeUser}

                sorted.forEach { (tipeUser, idUser ) ->
                    stickyHeader {
                        Text(text = "$tipeUser",
                            style = MaterialTheme.typography.h4,
                        )
                    }
                    items(items = idUser)
                    {
                        CardUser(pengguna = it)
                    }
                }

            }
        }
    ) {
        activityContentScope(modalBottomSheetState, scope)
    }
}



@Composable
fun SwitchBar2(
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
            MesinAppBar (
                onSearchClicked = onSearchTriggered
            ,
            navController = navController)
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
fun MesinAppBar( onSearchClicked: () -> Unit,
                 navController: NavController
                 ) {
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
                navController.navigate(MoReScreens.HomeScreen.name) {
                    popUpTo(MoReScreens.HomeScreen.name)
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
        actions = {
            IconButton(onClick = {
                onSearchClicked()
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
        },
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
