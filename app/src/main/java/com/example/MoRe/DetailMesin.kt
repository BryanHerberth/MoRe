package com.example.MoRe

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TableRow
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.MoRe.components.*
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.model.DaftarLaporan
import com.example.MoRe.model.DaftarMonitoring
import com.example.MoRe.model.getDataLaporan
import com.example.MoRe.model.getDataMonitor
import com.example.MoRe.navigation.MoReNavHost
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.model.req.ReqLaporan
import com.example.MoRe.network.model.res.dokumen.ResGetDokumen
import com.example.MoRe.network.model.res.getmesin.Mesin
import com.example.MoRe.network.model.res.laporan.ResGetVarLaporan
import com.example.MoRe.network.model.res.laporan.ResLaporanByName
import com.example.MoRe.network.model.res.monitor.ResGetMonitor
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.*


@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterialApi
@Composable
fun ScaffoldDetailMesin(
    navController: NavController,
    idPabrik : String?,
    idMesin : String?
) {
    Log.d("TAG", "DetailPabrik: $idPabrik")
    Log.d("TAG", "DetailMesin: $idMesin")
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val myMesin = SessionManager.getMesinData()
    var activeMesin by remember {
        mutableStateOf<Mesin?>(null)
    }
    activeMesin = myMesin

    // START API
    var responseVarLaporan by remember{
        mutableStateOf<ResGetVarLaporan?>(null)
    }

    suspend fun getLaporanVariabel (idPabrik: String?, idMesin: String?){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                try{
                    val response = repository.getLaporanVariabel(idPabrik!!, idMesin!!)
                    launch(Dispatchers.Main) {
                        responseVarLaporan = Resource.Success(response).data?.body()
                        Log.d("response get variabel laporan : ", responseVarLaporan.toString())
                    }
                } catch (e: Exception){
                    Log.e("Alarm Get Variabel Laporan on DetailMesin.kt : ", e.message.toString())
                }
            }
        }
    }

    LaunchedEffect(Unit){
        getLaporanVariabel(idPabrik, idMesin)
    }

    // STOP API

    Scaffold() {

    }
    Column(modifier = Modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                Column(
                    modifier = Modifier.width(270.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                    Text(
                        text = "Detil Mesin",
                        fontSize = 24.sp,
                        fontWeight = Bold,
                        color = Color.White,
                        modifier = Modifier.padding(40.dp,0.dp)
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(MoReScreens.PabrikScreen.name +"/{$idPabrik}") {
//                        popUpTo(MoReScreens.PabrikScreen.name+"/{$idPabrik}")
//                        {
//                            inclusive = true
//                        }
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            backgroundColor = BlueApp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            try {
//                CardMesin(resMesin = myMesin!! ,navController = navController, idPabrik = idPabrik)
//            } catch (e: Exception){
//                Log.e("Error CardMesin Detail Mesin : ", e.message.toString())
//            }
            CardMesin(clickable =false , resMesin = activeMesin!! ,navController = navController, idPabrik = idPabrik)

        }
        Spacer(modifier = Modifier.height(20.dp))
        Tabs(pagerState = pagerState, scope = coroutineScope)
        Spacer(modifier = Modifier.height(10.dp))
        responseVarLaporan?.data?.let { TabsContent(pagerState = pagerState, idPabrik = idPabrik, idMesin= idMesin, variabel = it.variabel) }
        }
    }

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun TabsContent(
    pagerState: PagerState,
    idPabrik : String?,
    idMesin: String?,
    variabel: List<String>
) {
    HorizontalPager(count = 3,
        state = pagerState,
    ) { page ->
        when (page){
            0 -> {
                PemantauanLayout(idPabrik = idPabrik, idMesin= idMesin)
            }

            1 -> {
                LaporanLayouts(idPabrik = idPabrik, idMesin= idMesin, variabel = variabel)
            }

            2 -> {
                DokumenLayout(idPabrik = idPabrik, idMesin= idMesin)
            }
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(pagerState: PagerState,
         scope: CoroutineScope
         ) {
    val list = listOf(
        "Pemantauan",
        "Laporan",
        "Dokumen"
    )
    val coroutineScope = rememberCoroutineScope()
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
        ) {

        ScrollableTabRow(selectedTabIndex = pagerState.currentPage,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            backgroundColor = Color.Transparent,
            divider = { TabRowDefaults.Divider(color = Color.Transparent) },
            indicator = {
                    tabPositions ->
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState,tabPositions)
                )
            }
        ) {
            list.forEachIndexed { index, s ->
                val selected = pagerState.currentPage == index

                Tab(selected = selected, onClick = {

                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                    enabled = true
                ) {
                    Text(text = list[index],
                        style = MaterialTheme.typography.h6,
                        fontWeight = Bold,
                        color = Color.Black,
                        )
                }

            }
        }
    }
}



@Composable
fun PemantauanLayout(
    monitoring: List<DaftarMonitoring> = getDataMonitor(),
    idPabrik : String?,
    idMesin: String?

) {

    // API START
    var responseGetMonitor by remember{
        mutableStateOf<ResGetMonitor?>(null)
    }

    suspend fun getMonitor(idPabrik: String?, idMesin: String?){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                try{
                    val response = repository.getMonitor(idPabrik ?: "", idMesin ?: "")
                    launch(Dispatchers.Main) {
                        responseGetMonitor = Resource.Success(response).data?.body()
                        Log.d("Rsponse get Monitor : ", responseGetMonitor.toString())
                    }
                }catch (e: Exception){
                    Log.e("Error Get Monitor on DetailMesin.kt : ", e.message.toString())
                }
            }
        }
    }

    LaunchedEffect(Unit){
        while (true) {
            getMonitor(idPabrik, idMesin)
            delay (2000)
        }
    }

    // API STOP

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn{
                responseGetMonitor?.data?.let { it1 ->
                    items(items = it1.monitor){
                        CardMonitoring(resMonitor = it)
                    }
                }
//                    items(items = monitoring)
//                    {
//                        CardMonitoring(monitoring = it)
//                    }
                }
            }
        }
    }

@Composable
fun DokumenLayout(
    idPabrik : String?,
    idMesin: String?
) {
    val context = LocalContext.current

    // API START
    var responseDokumen by remember{
        mutableStateOf<ResGetDokumen?>(null)
    }

    suspend fun getDokumen(idPabrik: String?, idMesin: String?){
        val repository = Repository()
        coroutineScope {
            launch(Dispatchers.IO) {
                try{
                    val response = repository.getDokumen(idPabrik!!, idMesin!!)
                    launch(Dispatchers.Main) {
                        responseDokumen = Resource.Success(response).data?.body()
                        Log.d("Response get Dokumen", responseDokumen.toString())
                    }
                } catch (e: Exception){
                    Log.e("Error Get Dokumen on DetailMesin.kt : ", e.message.toString())
                }
            }
        }
    }

    LaunchedEffect(Unit){
            getDokumen(idPabrik, idMesin)
    }

    // API STOP
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn {
                responseDokumen?.data?.let{it1->
                    items(items = it1.dokumen){
                        OutlinedButton(
                            onClick = {
                                val intent = Intent(Intent.ACTION_VIEW).apply {
                                    data = Uri.parse(it.dokumen)
                                }
                                context.startActivity(intent)
                            },
                            modifier = Modifier
                                .padding(4.dp)
                                .width(300.dp),
                            elevation = ButtonDefaults.elevation(
                                defaultElevation = 2.dp,
                                pressedElevation = 4.dp,
                                disabledElevation = 0.dp
                            ),
                        ) {
                            Text(
                                text = it.nama,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
//            OutlinedButton(onClick = { /*TODO*/ },
//                modifier = Modifier
//                    .padding(4.dp)
//                    .width(300.dp),
//                elevation = ButtonDefaults.elevation(
//                    defaultElevation = 2.dp,
//                    pressedElevation = 4.dp,
//                    disabledElevation = 0.dp
//                ),
//            ) {
//                Text(text = "Lembaran Data",
//                    color = Color.Black
//                )
//            }
//            OutlinedButton(onClick = { /*TODO*/ },
//                modifier = Modifier
//                    .padding(4.dp)
//                    .width(300.dp),
//                elevation = ButtonDefaults.elevation(
//                    defaultElevation = 2.dp,
//                    pressedElevation = 4.dp,
//                    disabledElevation = 0.dp
//                ),
//            ) {
//                Text(text = "Panduan",
//                    color = Color.Black
//                )
//            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
fun LaporanLayouts(
    idPabrik : String?,
    idMesin: String?,
    variabel: List<String>
){
    val options = variabel
    var optionText by remember{ mutableStateOf("")}
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    val tampilkanClickedState = remember {
        mutableStateOf(false)
    }

    // START API
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

    // STOP API
    val composableScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExposedDropdownMenuBox(expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }) {
                TextField(value = selectedOptionText,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text(text = "Variable")},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        textColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        focusedTrailingIconColor = Color.Black
                    ),
                    shape = RoundedCornerShape(corner = CornerSize(6.dp)),
                    modifier = Modifier.border(BorderStroke(1.dp, Color.LightGray)
                    )
                )
                ExposedDropdownMenu(expanded = expanded,
                    onDismissRequest = {expanded = false}) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false

                            }) {
                            Text(text = selectionOption)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            DateStartPickerLayout()
            Spacer(modifier = Modifier.height(10.dp))
            DateEndPickerLayout()
            Spacer(modifier = Modifier.height(10.dp))

            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                OutlinedButton(
                    onClick = {

                        val start = SessionManager.getStartDate
                        val stop = SessionManager.getStopDate
                        composableScope.launch{
                            postLaporanByName(idPabrik!!, idMesin!!, selectedOptionText, start, stop)
                            tampilkanClickedState.value = !tampilkanClickedState.value
                        }
                    },
                    modifier = Modifier
                        .padding(4.dp)
                        .width(250.dp),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 2.dp,
                        pressedElevation = 4.dp,
                        disabledElevation = 0.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = BlueApp,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Tampilkan",
                    )
                }
            }
        }
        if (tampilkanClickedState.value){
            HasilTampilkan(resLaporan = responseLaporan?.data?.laporan!!)
        } else{
            Box() {}
        }
    }
}


