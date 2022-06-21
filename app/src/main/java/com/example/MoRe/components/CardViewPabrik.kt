package com.example.MoRe.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.getPabrik


@Preview
@Composable
fun CardPabrik(pabrik: DaftarPabrik = getPabrik()[0],
        onItemClick : (String) -> Unit ={}
){
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(5.dp)) {
        Surface(modifier = Modifier
            .padding(12.dp)
            .wrapContentSize(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)

        ) {

            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(pabrik.id)
                    }
                    .wrapContentHeight(),
                elevation = 8.dp
            ) {
                Column() {
                    Image(painter = painterResource(id = pabrik.fotoPabrik),
                        contentDescription = "foto pabrik",
                        modifier = Modifier
                            .fillMaxWidth()
                        ,
                        contentScale = ContentScale.Fit
                    )
                    Column(modifier = Modifier
                        .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                        ,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = pabrik.namaPabrik,
                            style = MaterialTheme.typography.h6
                        )
                        Text(text = pabrik.alamatPabrik,
                            style = MaterialTheme.typography.caption
                        )
                    }
                }
            }
        }
    }
}