package com.example.MoRe.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.MoRe.R
import com.example.MoRe.model.DaftarUser
import com.example.MoRe.model.getUser
import com.example.MoRe.network.model.res.getmember.Anggota

@Composable
fun CardUser (
    resPengguna: Anggota

){

    var expanded by  remember{ mutableStateOf(false)}
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {

        }
        .padding(6.dp),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        border = BorderStroke(2.dp, color = Color.LightGray),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
            ) {
            Card(
                shape = CircleShape,
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp),
                border = BorderStroke(2.dp, color = Color.Black)
            ) {

                AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                    .data(data = resPengguna.foto_profil)
                    .crossfade(true)
                    .build(),
                    modifier = Modifier
                        .size(10.dp)
                        .padding(2.dp),
                    contentDescription = "Foto User",
                    contentScale = ContentScale.Fit
                )
            }
            Column(modifier = Modifier
                .padding(4.dp)
            ) {
                Text(
                    text = resPengguna.nama_pengguna,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = resPengguna.email,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }

    DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {

        DropdownMenuItem(onClick = { /*TODO*/ }) {
            Text(text = "Ubah")
        }
        DropdownMenuItem(onClick = { /*TODO*/ }) {
            Text(text = "Hapus")
        }
    }
}

