package com.example.MoRe

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.example.MoRe.navigation.MoReScreens
import com.example.MoRe.network.model.ResStart
import com.example.MoRe.network.model.base.BaseResponse
import com.example.MoRe.network.model.base.Resource
import com.example.MoRe.network.repository.Repository
import com.example.MoRe.network.viewModel.start.StartViewModel
//import com.example.MoRe.network.viewModel.start.StartViewModelFactory
import com.example.MoRe.ui.theme.BlueApp
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.Response
import retrofit2.HttpException

//private lateinit var startViewModel: StartViewModel

//@Preview(showBackground = true)




@Composable
fun moreSplashScreen(
    navController: NavController
) {
//    API START
    var res by remember {
        mutableStateOf<ResStart?>(null)
    }
    suspend fun getStart(){

        val repository = Repository()
        coroutineScope{
            launch(Dispatchers.IO){
                val response = repository.getStart()
                launch(Dispatchers.Main){
//                startResponseVM.value = Resource.Success(response)
                    res = Resource.Success(response).data?.body()!!
                    Log.e("Masuk view model", res.toString())
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        getStart()
    }
//     API STOP

    val scale = remember {
        Animatable(0f)
    }
    val email = rememberSaveable { mutableStateOf("Erico") }
    val password = rememberSaveable { mutableStateOf("1234") }
    val loggedIn = false
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(durationMillis = 500,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                })
        )
        delay(1200L)
        if (loggedIn) {
            navController.navigate(MoReScreens.HomeScreen.name + "/${email.value}/${password.value}")
        } else {
            navController.navigate(MoReScreens.LoginScreen.name)
        }
    }

    Box(
        modifier = Modifier
            .background(BlueApp)
            .fillMaxSize()
            .scale(scale.value),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.more_logo),
            contentDescription = "appLogo",
            modifier = Modifier.fillMaxSize()
        )
    }
}

