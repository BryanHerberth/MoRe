package com.example.MoRe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.MoRe.ViewModel.SearchViewModel
import com.example.MoRe.components.StatusBarchange
import com.example.MoRe.navigation.MoReNavHost
import com.example.MoRe.ui.theme.BlueApp
import com.example.MoRe.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                StatusBarchange()
                ModalBottomSheet{
                        state: ModalBottomSheetState, scope: CoroutineScope ->
                    ScaffoldListMesin(searchViewModel = SearchViewModel(), scope = scope,
                        modalBottomSheetState = state, navController = navController)
                    MoReApp(scope = scope, modalBottomSheetState = state)
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoReApp(scope: CoroutineScope,
            modalBottomSheetState: ModalBottomSheetState) {
    
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                MoReNavHost(scope = scope, modalBottomSheetState = modalBottomSheetState)
            }
        }
    )
    
}

