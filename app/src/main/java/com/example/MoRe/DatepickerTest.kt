package com.example.MoRe

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.MoRe.ui.theme.MyApplicationTheme

class DatepickerTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DatepickerTrial()
                }
            }
        }
    }
}


@Composable
fun DatepickerTrial(){

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

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "DatePicker")
            },)
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                TextField(
                    value = "${date.value}",
                    onValueChange = {   },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = {datePickerDialog.show()}) {
                            Image(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "DropDownArrow")
                        }
                    },
                    modifier = Modifier.wrapContentWidth()

                    )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview69() {
    MyApplicationTheme {
        DatepickerTrial()
    }
}