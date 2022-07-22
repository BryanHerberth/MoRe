package com.example.MoRe.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.MoRe.model.DaftarMesinNotif
import com.example.MoRe.model.getDataMesin

@Preview(showBackground = true)
@Composable
fun CardNotif(mesin : DaftarMesinNotif = getDataMesin()[0],
              onItemClick : (String) -> Unit ={}
              ){
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            onItemClick(mesin.id)
            onItemClick(Log.d("Clicked","Clicked").toString())
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(2.dp, color = Color.LightGray)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
            ) {
            Surface(modifier = Modifier
                .padding(10.dp)
                .size(100.dp),
                shape = RoundedCornerShape(CornerSize(16.dp)),
                elevation = 5.dp
            ) {
                Image(painter = painterResource(id = mesin.fotoMesin),
                    contentDescription = "Foto Mesin")
            }
            Column(modifier = Modifier
                .padding(4.dp)
            ) {
                Text(text = mesin.namaMesin,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = mesin.namaPabrik,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,

                )
                Text(text = mesin.pesan,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

