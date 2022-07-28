package com.example.MoRe.components

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.MoRe.model.DaftarLaporan
import com.example.MoRe.model.getDataLaporan

@ExperimentalFoundationApi
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
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(0.1f))
                    Text(text = "Waktu Pencatatan",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(0.1f))
                    Text(text = "Hasil",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Bold
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
            date.value = "$day1/$month/$year1"
        }, year, month, day
    )
    TextField(
        value = "${date.value}",
        onValueChange = {   },
        label ={ Text(text = "Start Date") } ,
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
        modifier = Modifier.border(
            BorderStroke(1.dp, Color.LightGray)
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
            val endMonth = month2 + 1
            eDate.value = "$day2/$endMonth/$year2"
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