package com.example.MoRe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.MoRe.components.StatusBarchange
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.navigation.MoReNavHost
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SessionManager.init(this)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                val modalBottomSheetState = rememberModalBottomSheetState(
                    initialValue =ModalBottomSheetValue.Hidden
                )

                val scope = rememberCoroutineScope()
                StatusBarchange()
                MoReApp(scope, modalBottomSheetState)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoReApp(
    scope: CoroutineScope,
    modalBottomSheetState: ModalBottomSheetState,
) {
    
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                MoReNavHost(scope = scope, modalBottomSheetState = modalBottomSheetState,
                )
            }
        }
    )
    
}

