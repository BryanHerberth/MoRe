package com.example.MoRe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MoRe.components.CardNotif
import com.example.MoRe.components.StatusBarchange
import com.example.MoRe.model.DaftarMesinNotif
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.getDataMesin
import com.example.MoRe.model.getPabrik
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

//class NotificationPage : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val systemUiController = rememberSystemUiController()
//            val useDarkIcons = MaterialTheme.colors.isLight
//
//            SideEffect {
//                systemUiController.setStatusBarColor(
//                    color = BlueApp,
//                    darkIcons = useDarkIcons
//                )
//            }
//            ScaffoldNotif()
//        }
//    }
//}


@Composable
fun ScaffoldNotif(
    listMesin: List<DaftarMesinNotif> = getDataMesin(),
    navController: NavController,
    idPabrik: String?,
    idMesin: String?
){
    val idMesin = rememberSaveable { mutableStateOf("2") }
    Scaffold(
        topBar = {
            CustomAppbar(name = "Notifikasi", navController = navController)
        },
        content = {
            Column(modifier = Modifier.padding(12.dp)) {

                LazyColumn{
                    items(items = listMesin)
                    {
                        CardNotif(mesin = it, navController = navController)
                    }
                }
            }
        }
    )
}


//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2(nav) {
//    ScaffoldNotif(navController = )
//}