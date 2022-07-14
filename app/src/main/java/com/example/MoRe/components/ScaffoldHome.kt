package com.example.MoRe.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.MoRe.SearchBar
import com.example.MoRe.ViewModel.SearchViewModel
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.SearchWidgetState
import com.example.MoRe.model.getPabrik
import com.example.MoRe.ui.theme.BlueApp

@Composable
fun ScaffoldHome(
    listPabrik : List<DaftarPabrik> = getPabrik(),
    searchViewModel: SearchViewModel
) {

    val searchWidgetState by searchViewModel.searchWidgetState
    val searchTextState by searchViewModel.searchTextState
    Scaffold(

        topBar = {
            SwitchAppbar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                               searchViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                                 searchViewModel.updateSearchTextState(newValue = "")
                                searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked ={
                                 Log.d("Searched text", it)
                },
                onSearchTriggered = {
                    searchViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                }
            )
        },
        content = {
            Surface(modifier =
            Modifier
                .background(BlueApp)
                .fillMaxWidth()
                .fillMaxHeight()) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(BlueApp),
                    hint = "telusuri"
                )
                Card() {
                    LazyColumn{
                        items(items = listPabrik)
                        {
                            CardPabrik(pabrik = it)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(235.dp))
            }
        },
    )
}

@Composable
fun SwitchAppbar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
){
    when(searchWidgetState){
        SearchWidgetState.CLOSED -> {
            AppBarCompose (
                onSearchClicked = onSearchTriggered
                    )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }
    }
}

@Composable
fun AppBarCompose( onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                Text(
                    text = "Daftar Pabrik",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                /*TODO*/
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Akun",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onSearchClicked()
            }){
                Icon(imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White)
            }
            IconButton(onClick = { /*TODO*/ }) {

                Icon(
                    imageVector = Icons.Filled.Notifications,
                    contentDescription = "notifications",
                    tint = Color.White
                )
            }
        },
        backgroundColor = BlueApp
    )
}

