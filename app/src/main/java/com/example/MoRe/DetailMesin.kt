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
import com.example.MoRe.components.CardMesin
import com.example.MoRe.components.CardNotif
import com.example.MoRe.components.CardViewLaporan
import com.example.MoRe.components.StatusBarchange
import com.example.MoRe.model.DaftarLaporan
import com.example.MoRe.model.getDataLaporan
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme

@ExperimentalMaterialApi
class DetailMesin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                StatusBarchange()
                Surface(color = MaterialTheme.colors.background) {
                    ScaffoldDetailMesin()
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun ScaffoldDetailMesin()
{
    Column() {

        TopAppBar(
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start

                ) {
                    Text(
                        text = "Detil Mesin",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(65.dp,0.dp)
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    /*TODO*/
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
            CardMesin()
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                TextButton(onClick = {
                    Log.d(TAG, "Clicked")
                },
                modifier = Modifier.padding(2.dp)
                    ) {
                    Text(text = "Pemantauan",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(2.dp)
                    )
                }

                TextButton(onClick = {
                    Log.d(TAG, "Clicked")
                },
                    modifier = Modifier.padding(2.dp)
                ) {
                    Text(text = "Laporan",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(2.dp)
                    )
                }
                TextButton(onClick = {
                    Log.d(TAG, "Dokumen")
                },
                    modifier = Modifier.padding(2.dp)
                ) {
                    Text(text = "Dokumen",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(2.dp)
                    )
                }
//                Text(text = "Laporan",
//                    style = MaterialTheme.typography.h5,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(6.dp)
//                )
//                Text(text = "Dokumen",
//                    style = MaterialTheme.typography.h5,
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(6.dp)
//                )
            }
            Spacer(modifier = Modifier.height(20.dp))
//            Pemantauan()
//            DokumenLayout()
            LaporanLayouts()
        }
    }
}

@Composable
fun Pemantauan() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .width(150.dp)
                    .wrapContentHeight(),
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
                    .wrapContentHeight(),
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

//@Preview(showBackground = true)
@Composable
fun DokumenLayout() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedButton(onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(4.dp)
                .width(300.dp),
//            border = BorderStroke(1.dp, color = Color.Black),
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
//                color = Color.White,
            )
        }
    }
    if (tampilkanClickedState.value){
        HasilTampilkan()
    } else{
        Box() {}
    }
}

@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
fun HasilTampilkan(laporan: List<DaftarLaporan> = getDataLaporan()){
        Card(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),

        ) {
            Surface(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .fillMaxHeight(),

            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                    ) {
                        
                        Text(text = "Nomor",
                            style = MaterialTheme.typography.h6,
                            fontWeight = Bold
                        )
                        Spacer(modifier = Modifier.weight(0.1f))
                        Text(text = "Waktu Pencatatan",
                            style = MaterialTheme.typography.h6,
                            fontWeight = Bold
                            )
                        Spacer(modifier = Modifier.weight(0.1f))
                        Text(text = "Hasil",
                            style = MaterialTheme.typography.h6,
                            fontWeight = Bold
                        )
                    }

                    LazyColumn{
                        val laporanSort = laporan.groupBy { it.Nomor}

                        laporanSort.forEach { (Nomor, Tanggal, ) ->
                            stickyHeader {
                            }
                            items(items = Tanggal){
                                CardViewLaporan(daftarLaporan = it)
                            }
                        }
                    }
                }
        }
    }
}



@Composable
fun DateStartPickerLayout(){

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val context = LocalContext.current

    var date =  remember {
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(
        context, { d, year1 , month1 , day1 ->
            val month = month1 + 1
            date.value = "$day1/$month1/$year1"
        }, year, month, day
    )
    TextField(
        value = "${date.value}",
        onValueChange = {   },
        label ={ Text(text = "Start Date")} ,
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = {datePickerDialog.show()}) {
                Image(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "DropDownArrow")
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Black,
            trailingIconColor = Color.Black
        ),
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        modifier = Modifier.border(BorderStroke(1.dp, Color.LightGray)
        )
    )
}

@Composable
fun DateEndPickerLayout() {

    val eCalendar = Calendar.getInstance()
    val eYear = eCalendar.get(Calendar.YEAR)
    val eMonth = eCalendar.get(Calendar.MONTH)
    val eDay = eCalendar.get(Calendar.DAY_OF_MONTH)

    val context = LocalContext.current

    var eDate = remember {
        mutableStateOf("")
    }

    val dateEndPickerDialog = DatePickerDialog(
        context, { d, year2, month2, day2 ->
            val month = month2 + 1
            eDate.value = "$day2/$month2/$year2"
        }, eYear, eMonth, eDay
    )
    TextField(
        value = "${eDate.value}",
        onValueChange = { },
        label = { Text(text = "End Date") },
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { dateEndPickerDialog.show() }) {
                Image(
                    imageVector = Icons.Outlined.ArrowDropDown,
                    contentDescription = "DropDownArrow"
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Black,
            trailingIconColor = Color.Black
        ),
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        modifier = Modifier.border(
            BorderStroke(1.dp, Color.LightGray)
        )
    )
}
