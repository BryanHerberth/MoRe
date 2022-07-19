package com.example.MoRe.components

import android.app.DatePickerDialog
import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.util.Pair
import com.google.android.material.datepicker.MaterialDatePicker

//@Composable
//fun DatepickerTest(
//
//){
//
//    val calendar = Calendar.getInstance()
//    val year = calendar.get(Calendar.YEAR)
//    val month = calendar.get(Calendar.MONTH)
//    val day = calendar.get(Calendar.DAY_OF_MONTH)
//
//    val context = LocalContext.current
//
//    var date =  remember {
//        mutableStateOf("")
//    }
//
//
//
//    val datePickerDialog = DatePickerDialog(
//        context, { d, year1 , month1 , day1 ->
//            val month = month1 + 1
//            date.value = "$day1/$month/$year1"
//        }, year, month, day
//    )
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    )
//    {
//
//        TextField(value = "${date.value}",
//            onValueChange = {},
//            modifier = Modifier.clickable {
//                datePickerDialog.show()
//            }
//        )
//    }
//}


