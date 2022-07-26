package com.example.MoRe

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.icu.util.Calendar
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
import com.example.MoRe.model.DaftarLaporan
import com.example.MoRe.model.getDataLaporan
import com.example.MoRe.navigation.MoReNavHost
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//@ExperimentalMaterialApi
//class DetailMesin : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContent {
//            MyApplicationTheme {
//                StatusBarchange()
//                Surface(color = MaterialTheme.colors.background) {
//                    ScaffoldDetailMesin()
//                }
//            }
//        }
//    }
//}


@OptIn(ExperimentalPagerApi::class)
@ExperimentalMaterialApi
//@Preview(showBackground = true)
@Composable
fun ScaffoldDetailMesin(navController: NavController)
{
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

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
                    navController.navigate(MoReScreens.PabrikScreen.name) {
                        popUpTo(MoReScreens.PabrikScreen.name)
                        {
                            inclusive = true
                        }
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
            CardMesin(navController = navController)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Tabs(pagerState = pagerState, scope = coroutineScope)
        Spacer(modifier = Modifier.height(10.dp))
        TabsContent(pagerState = pagerState)
        }
    }

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(count = 3,
        state = pagerState,
    ) { page ->
        when (page){
            0 -> {
                PemantauanLayout()
            }

            1 -> {
                LaporanLayouts()
            }

            2 -> {
                DokumenLayout()
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
                .fillMaxWidth(),
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
fun PemantauanLayout() {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                        ,
                    elevation = 6.dp,
//                        border = BorderStroke(4.dp, color = Color.LightGray),
                    shape = RoundedCornerShape(corner = CornerSize(16.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "90", style = MaterialTheme.typography.h3)
                        Text(text = "(%)", style = MaterialTheme.typography.h4)
                        Text(
                            text = "Kecepatan Mesin",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(6.dp, 0.dp)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                        ,
                    elevation = 6.dp,
//                        border = BorderStroke(4.dp, color = Color.LightGray),
                    shape = RoundedCornerShape(corner = CornerSize(16.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "124", style = MaterialTheme.typography.h3)
                        Text(text = " I/ Min", style = MaterialTheme.typography.h4)
                        Text(
                            text = "Hasil Produksi",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(6.dp, 0.dp)
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                    ,
                    elevation = 6.dp,
//                        border = BorderStroke(4.dp, color = Color.LightGray),
                    shape = RoundedCornerShape(corner = CornerSize(16.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "90", style = MaterialTheme.typography.h3)
                        Text(text = "(%)", style = MaterialTheme.typography.h4)
                        Text(
                            text = "Kecepatan Mesin",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(6.dp, 0.dp)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                    ,
                    elevation = 6.dp,
//                        border = BorderStroke(4.dp, color = Color.LightGray),
                    shape = RoundedCornerShape(corner = CornerSize(16.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(text = "124", style = MaterialTheme.typography.h3)
                        Text(text = " I/ Min", style = MaterialTheme.typography.h4)
                        Text(
                            text = "Hasil Produksi",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.padding(6.dp, 0.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun DokumenLayout() {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(4.dp)
                    .width(300.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 4.dp,
                    disabledElevation = 0.dp
                ),
            ) {
                Text(text = "Lembaran Data",
                    color = Color.Black
                )
            }
            OutlinedButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(4.dp)
                    .width(300.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 4.dp,
                    disabledElevation = 0.dp
                ),
            ) {
                Text(text = "Panduan",
                    color = Color.Black
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterialApi
@Composable
fun LaporanLayouts(){
    val options = (listOf("Kecepatan Mesin", "Hasil Mesin"))
    var optionText by remember{ mutableStateOf("")}
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    val tampilkanClickedState = remember {
        mutableStateOf(false)
    }

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
                OutlinedButton(onClick = {
                    tampilkanClickedState.value = !tampilkanClickedState.value
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
            HasilTampilkan()
        } else{
            Box() {}
        }
    }
}


