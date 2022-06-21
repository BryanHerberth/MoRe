package com.example.MoRe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class HomeScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight

            SideEffect {
                systemUiController.setStatusBarColor(
                    color = BlueApp,
                    darkIcons = useDarkIcons
                )
            }
//            Scaffold {
//                SearchAppBar(
//                    text = "test",
//                    onTextChange = { /*TODO*/ },
//                    onCloseClicked = { /*TODO*/ },
//                    onSearchClicked ={ /*TODO*/ }
//                )
//                LazyColumn(content = )
//                CenterAppBar()
            }
        }
    }

//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Composable
//fun CenterAppBar(){
//    CenterAlignedTopAppBar(
//        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//            BlueApp
//        ),
//        title = {
//            Text(text = "Daftar Pabrik",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White,
//            )
//        },
//
//        navigationIcon = {
//            IconButton(onClick = {
//                /*TODO*/
//            }) {
//                Icon(
//                    imageVector = Icons.Filled.AccountCircle,
//                    contentDescription = "Back Arrow",
//                    tint = Color.White
//                )
//            }
//        },
//        actions = {
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(imageVector = Icons.Filled.Notifications,
//                    contentDescription = "notifications",
//                    tint = Color.White)
//            }
//        }
//
//    )
//}


//@Composable
//fun SearchAppBar(
//    text: String,
//    onTextChange: (String) -> Unit,
//    onCloseClicked: () -> Unit,
//    onSearchClicked: (String) -> Unit,
//) {
//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(56.dp),
//        elevation = AppBarDefaults.TopAppBarElevation,
//        color = MaterialTheme.colors.primary
//    ) {
//        TextField(modifier = Modifier
//            .fillMaxWidth(),
//            value = text,
//            onValueChange = {
//                onTextChange(it)
//            },
//            placeholder = {
//                Text(
//                    modifier = Modifier
//                        .alpha(ContentAlpha.medium),
//                    text = "Search here...",
//                    color = Color.White
//                )
//            },
//            textStyle = TextStyle(
//                fontSize = MaterialTheme.typography.subtitle1.fontSize
//            ),
//            singleLine = true,
//            leadingIcon = {
//                IconButton(
//                    modifier = Modifier
//                        .alpha(ContentAlpha.medium),
//                    onClick = {}
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Search,
//                        contentDescription = "Search Icon",
//                        tint = Color.White
//                    )
//                }
//            },
//            trailingIcon = {
//                IconButton(
//                    onClick = {
//                        if (text.isNotEmpty()) {
//                            onTextChange("")
//                        } else {
//                            onCloseClicked()
//                        }
//                    }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Close,
//                        contentDescription = "Close Icon",
//                        tint = Color.White
//                    )
//                }
//            },
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Search
//            ),
//            keyboardActions = KeyboardActions(
//                onSearch = {
//                    onSearchClicked(text)
//                }
//            ),
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Transparent,
//                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
//            ))
//    }
//}
//
//@Composable
//fun AppBar() {
//    Row(
//        Modifier
//            .padding(16.dp)
//            .height(48.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround
//    ) {
//        var text by remember { mutableStateOf("Search....") }
//        TextField(
//            value = text,
//            onValueChange = {text = it},
////            label = { Text(text = "Search....", fontSize = 12.sp) },
//            singleLine = true,
//            leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search") },
//            colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.White,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            ),
//            shape = RoundedCornerShape(8.dp),
//            modifier = Modifier
//
//                .fillMaxHeight()
//        )
//        Spacer(modifier = Modifier.width(8.dp))
//        IconButton(onClick = { }) {
//            Icon(imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "",
//                tint = Color.White)
//        }
//        IconButton(onClick = {}) {
//            Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "",
//                tint = Color.White)
//        }
//
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    CenterAppBar()
//    SearchAppBar(text = , onTextChange = , onCloseClicked = { /*TODO*/ }, onSearchClicked = )
//}