package com.example.MoRe.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.MoRe.model.DaftarLaporan
import com.example.MoRe.model.getDataLaporan
import com.example.MoRe.network.model.res.laporan.LaporanByName

@Composable
fun  CardViewLaporan(
    resLaporan: LaporanByName,
) {
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(6.dp)),
        border = BorderStroke(2.dp, color = Color.LightGray)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Row(modifier = Modifier
                .padding(4.dp)
            ) {
                Text(text = resLaporan.nomor,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(4.dp)
                        .weight(0.01f)
                )
                Text(
                    text = resLaporan.timestamp,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(4.dp)
                        .weight(0.02f)
                    )
                Text(text = resLaporan.value,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(4.dp)
//                        .weight(0.1f)
                )
            }
        }
    }
}