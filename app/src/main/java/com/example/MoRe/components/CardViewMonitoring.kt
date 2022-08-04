package com.example.MoRe.components

import androidx.compose.foundation.BorderStroke
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


//@Preview(showBackground = true)
@Composable
fun CardMonitoring(
    resMonitor : Monitor,
    monitoring : DaftarMonitoring = getDataMonitor()[0],
    onItemClick : (String) -> Unit ={},
//    navController: NavController,
){

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(100.dp)
        .clickable {
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
//                text = monitoring.jenisMonitoring,
                text = resMonitor.nama,
                style = MaterialTheme.typography.h5,

            )
            if (resMonitor.satuan=="run/stop"){
                if(resMonitor.value == 1){
                    Text(
//                text = monitoring.valueMonitoring,
                        text = "run",
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp)

                    )
                }else{
                    Text(
//                text = monitoring.valueMonitoring,
                        text = "stop",
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 20.dp)

                    )
                }
            } else if(resMonitor.satuan=="hour"){
                val tempHour= resMonitor.value/60
                val tempMin = resMonitor.value % 60
                Text(
                    text = "${tempHour}:${tempMin}",
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp)

                )
            }else{
                Text(
//                text = monitoring.valueMonitoring,
                    text = resMonitor.value.toString(),
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp)

                )
            }

            Text(
//                text = monitoring.satuanMonitoring,
                text = resMonitor.satuan,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}


