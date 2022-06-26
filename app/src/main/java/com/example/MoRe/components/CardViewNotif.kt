package com.example.MoRe.components

import androidx.compose.foundation.Image
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
        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
            ) {
            Surface(modifier = Modifier
                .padding(12.dp)
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
//                    inlineContent = Icon(imageVector = Icons.Filled.LocationOn,
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

