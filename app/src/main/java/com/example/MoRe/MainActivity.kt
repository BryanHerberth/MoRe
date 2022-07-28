package com.example.MoRe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.MoRe.ViewModel.SearchViewModel
import com.example.MoRe.components.StatusBarchange
import com.example.MoRe.navigation.MoReNavHost
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
                    ScaffoldListMesin(
                        searchViewModel = SearchViewModel(), scope = scope,
                        modalBottomSheetState = state, navController = navController, idPabrik = null
                    )
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

