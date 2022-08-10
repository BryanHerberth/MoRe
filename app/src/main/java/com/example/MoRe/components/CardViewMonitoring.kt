package com.example.MoRe.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.MoRe.model.DaftarMonitoring
import com.example.MoRe.model.getDataMonitor
import com.example.MoRe.network.model.res.monitor.Monitor
import com.example.MoRe.network.model.res.monitor.ResGetMonitor


@Composable
fun CardMonitoring(
    resMonitor : Monitor,
    onItemClick : (String) -> Unit ={},
) {

    var myBorder = BorderStroke(1.dp, color = Color.LightGray)
    var myBackgroundColor = Color.White
    var alarmColor = Color.Black

    if(resMonitor.alarm && resMonitor.enableAlarm){
        myBorder = BorderStroke(2.dp, color = Color.Red)
        myBackgroundColor = Color.Red
        alarmColor = Color.White
    }

    if (resMonitor.nama == "Status" && resMonitor.value == 2 || resMonitor.value == 3){
        alarmColor = Color.Black
        myBackgroundColor = Color(0xFFFFD700)
        myBorder = BorderStroke(2.dp, color = Color.Yellow)

    } else if (resMonitor.nama =="Status" && resMonitor.value == 1) {
        alarmColor = Color.Black
        myBackgroundColor = Color.Green
        myBorder = BorderStroke(2.dp, color = Color.Green)
    } else {
        if(resMonitor.alarm && resMonitor.enableAlarm){
            myBorder = BorderStroke(2.dp, color = Color.Red)
            myBackgroundColor = Color.Red
            alarmColor = Color.White
        }
    }


    Box(contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                },
            shape = RoundedCornerShape(corner = CornerSize(16.dp)),
            elevation = 6.dp,
            border = myBorder,
            backgroundColor = myBackgroundColor
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = resMonitor.nama,
                    color = alarmColor,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (resMonitor.satuan == "run/stop") {
                    if (resMonitor.value == 1) {
                        Text(
                            text = "RUN",
                            style = MaterialTheme.typography.h3,
                            color = alarmColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 20.dp)

                        )
                    } else if (resMonitor.value == 0){
                        Text(
                            text = "STOP",
                            style = MaterialTheme.typography.h3,
                            color = alarmColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 20.dp)

                        )
                    } else if ( resMonitor.value == 2 ){
                        Text(
                            text = "STARTUP",
                            style = MaterialTheme.typography.h5,
                            color = alarmColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 20.dp)

                        )
                    } else if ( resMonitor.value == 3 ){
                        Text(
                            text = "COOLDOWN",
                            style = MaterialTheme.typography.h5,
                            color = alarmColor,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )
                    }
                } else if (resMonitor.satuan == "hour") {
                    val tempHour = resMonitor.value / 60
                    val tempMin = resMonitor.value % 60
                    Text(
                        text = "${"%02d".format(tempHour)}:${"%02d".format(tempMin)}",
                        style = MaterialTheme.typography.h4,
                        color = alarmColor,

                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp)

                    )
                } else {
                    Text(
//                text = monitoring.valueMonitoring,
                        text = resMonitor.value.toString(),
                        style = MaterialTheme.typography.h3,
                        color = alarmColor,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp)


                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = resMonitor.satuan,
                    style = MaterialTheme.typography.h5,
                    color = alarmColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
    }
}

