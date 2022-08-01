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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.MoRe.R
import com.example.MoRe.model.DaftarUser
import com.example.MoRe.model.getUser

@Preview(showBackground = true)
@Composable
fun CardUser (
    pengguna : DaftarUser = getUser()[0],
//    onItemClick : (String) -> Unit ={}
){

    var expanded by  remember{ mutableStateOf(false)}
//    val imageUri = rememberSaveable { mutableStateOf("") }
//    val painter = rememberAsyncImagePainter(
//        if (imageUri.value.isEmpty())
//            pengguna.fotoUser
//        else
//            imageUri.value
//    )
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri: Uri? ->
//        uri?.let { imageUri.value = it.toString() }
//    }
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
                Image(
                    imageVector = pengguna.fotoUser,
                    contentDescription = null,
                    modifier = Modifier
                        .size(10.dp)
                        .padding(2.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier
                .padding(4.dp)
            ) {
                Text(text = pengguna.namaUser,
                    style = MaterialTheme.typography.h6
                )
                Text(text = pengguna.emailUser,
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

//@Composable
//fun PopupMenu(
//    menuItems: List<String>,
//    onClickCallbacks: List<() -> Unit>,
//    showMenu: Boolean,
//    onDismiss: () -> Unit,
//    toggle: @Composable () -> Unit,
//) {
//    DropdownMenu(
//        toggle = toggle,
//        expanded = showMenu,
//        onDismissRequest = { onDismiss() },
//    ) {
//        menuItems.forEachIndexed { index, item ->
//            DropdownMenuItem(onClick = {
//                onDismiss()
//                onClickCallbacks[index]
//            }) {
//                Text(text = item)
//            }
//        }
//    }
//}
//
//@Preview
//@Composable
//fun Toggle() {
//    var showMenu by remember { mutableStateOf(false) }
//
//    PopupMenu(
//        menuItems = listOf("Delete"),
//        onClickCallbacks = listOf { println("Deleted") },
//        showMenu = showMenu,
//        onDismiss = { showMenu = false }) {
//        Text(
//            modifier = Modifier.clickable(onClick = {}, onLongClick = {
//                showMenu = true
//            }),
//            text = "Long click here",
//        )
//    }
//}