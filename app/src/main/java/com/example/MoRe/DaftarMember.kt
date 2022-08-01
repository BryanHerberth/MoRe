package com.example.MoRe

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.MoRe.components.CardNotif
import com.example.MoRe.components.CardUser
import com.example.MoRe.model.DaftarMesinNotif
import com.example.MoRe.model.DaftarUser
import com.example.MoRe.model.getDataMesin
import com.example.MoRe.model.getUser
import com.example.MoRe.navigation.MoReScreens

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScaffoldMember(
    listMember: List<DaftarUser> = getUser(),
    navController: NavController,
    idPabrik: String?,

){
    val idPabrik = rememberSaveable { mutableStateOf("2") }
    Scaffold(
        topBar = {
            CustomAppbar2(name = "Member",
                navController = navController,
                email = null ,
                password = null,
                idPabrik = null)
        },
        content = {
            Column(modifier = Modifier.padding(12.dp)) {

                LazyColumn{

                    val sorted = listMember.groupBy { it.tipeUser}

                    sorted.forEach { (tipeUser, idUser ) ->
                        stickyHeader {
                            Text(text = "$tipeUser",
                                style = MaterialTheme.typography.h4,
                            )
                        }
                        items(items = idUser)
                        {
                            CardUser(pengguna = it)
                        }
                    }
                }
            }
        }
    )
}