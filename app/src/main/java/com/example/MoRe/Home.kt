package com.example.MoRe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.MoRe.components.CardPabrik
import com.example.MoRe.components.StatusBarchange
import com.example.MoRe.model.DaftarPabrik
import com.example.MoRe.model.getPabrik
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StatusBarchange()
            ScaffoldHome()
        }
    }
}

@Composable
fun ScaffoldHome(
    listPabrik : List<DaftarPabrik> = getPabrik()
) {
    Scaffold(

        topBar = {
            AppBarCompose()
                 },

        content = {
            Surface(modifier =
                    Modifier.background(BlueApp)
                        .fillMaxWidth()
                        .fillMaxHeight()) {
//                Box(modifier = Modifier
//                    .background(BlueApp)
//                    .fillMaxWidth(),
//                contentAlignment = Alignment.Center) {
//                }
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
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier,
        contentAlignment = Alignment.Center) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            shape = RoundedCornerShape(30.dp),
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black , textAlign = TextAlign.Left),
            modifier = Modifier
//                .border(BorderStroke(2.dp , color = Color.LightGray))
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true
                },
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {

                }
                Icon(imageVector = Icons.Default.Search , contentDescription = "Search")
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent)

        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun AppBarCompose() {
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
                        contentDescription = "Back Arrow",
                        tint = Color.White
                    )
                }
            },
            actions = {
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    MyApplicationTheme {
        ScaffoldHome()
    }
    
}