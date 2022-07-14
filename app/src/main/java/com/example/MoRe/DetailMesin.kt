package com.example.MoRe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.MoRe.components.CardMesin
import com.example.MoRe.components.CardNotif
import com.example.MoRe.components.StatusBarchange
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme

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
            .fillMaxWidth(),
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

                Text(text = "Pemantaun",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(6.dp)
                    )
                Text(text = "Laporan",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(6.dp)
                )
                Text(text = "Dokumen",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(6.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Card(modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                        .wrapContentHeight(),
                        elevation = 6.dp,
//                        border = BorderStroke(4.dp, color = Color.LightGray),
                        shape = RoundedCornerShape(corner = CornerSize(16.dp))
                    ) {
                        Column(modifier = Modifier.padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "90", style = MaterialTheme.typography.h3)
                            Text(text = "(%)", style = MaterialTheme.typography.h4)
                            Text(text = "Kecepatan Mesin",
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.padding(6.dp,0.dp)
                            )
                        }
                    }
                    Card(modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                        .wrapContentHeight(),
                        elevation = 6.dp,
//                        border = BorderStroke(4.dp, color = Color.LightGray),
                        shape = RoundedCornerShape(corner = CornerSize(16.dp))
                    ) {
                        Column(modifier = Modifier.padding(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(text = "124", style = MaterialTheme.typography.h3)
                            Text(text = " I/ Min", style = MaterialTheme.typography.h4)
                            Text(text = "Hasil Produksi",
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.padding(6.dp,0.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
