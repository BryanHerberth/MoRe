package com.example.MoRe

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.ui.theme.BlueApp
import kotlinx.coroutines.delay

//@Preview(showBackground = true)
@Composable
fun moreSplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }))
        delay(2000L)
        navController.navigate(MoReScreens.LoginScreen.name )

    }

    Box(modifier = Modifier
        .background(BlueApp)
        .fillMaxSize()
        .scale(scale.value),
        contentAlignment = Alignment.Center) {

        Image(
            painter = painterResource(id = R.drawable.more_logo),
            contentDescription = "appLogo",
            modifier = Modifier.fillMaxSize()
        )
    }
}