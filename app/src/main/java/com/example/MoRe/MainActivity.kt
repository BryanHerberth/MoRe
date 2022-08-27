package com.example.MoRe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.MoRe.components.StatusBarchange
import com.example.MoRe.dao.SessionManager
import com.example.MoRe.navigation.MoReNavHost
import com.example.MoRe.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SessionManager.init(this)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()


                val scope = rememberCoroutineScope()
                StatusBarchange()
                MoReApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoReApp(
) {
    
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                MoReNavHost()
            }
        }
    )
    
}

