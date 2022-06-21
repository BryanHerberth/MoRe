package com.example.MoRe.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.example.MoRe.ui.theme.BlueApp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun StatusBarchange(){
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = MaterialTheme.colors.isLight

    SideEffect {
        systemUiController.setStatusBarColor(
            color = BlueApp,
            darkIcons = useDarkIcons
        )
    }
}