package com.example.MoRe.navigation

import android.window.SplashScreen
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.MoRe.*
import com.example.MoRe.ViewModel.SearchViewModel
import com.example.MoRe.components.ScaffoldHome
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoReNavHost(scope: CoroutineScope,
                modalBottomSheetState: ModalBottomSheetState,) {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = MoReScreens.SplashScreen.name
        ){
        composable(MoReScreens.SplashScreen.name){
            moreSplashScreen(navController = navController)
        }

        composable(MoReScreens.LoginScreen.name){
            moreLogInScreen(navController = navController)
        }

        composable(MoReScreens.SignUpScreen.name){
            moreSignUpScreen(navController = navController)
        }
        composable(MoReScreens.PabrikScreen.name){
            ScaffoldListMesin(searchViewModel = SearchViewModel(), navController = navController, scope = scope, modalBottomSheetState = modalBottomSheetState)
        }
        composable(MoReScreens.DetailScreen.name){
            ScaffoldDetailMesin(navController = navController)
        }
        composable(MoReScreens.VerificationScreen.name){
            moreVerifScreen(navController = navController)
        }

        composable(MoReScreens.HomeScreen.name){
            ScaffoldHome(searchViewModel = SearchViewModel(), navController = navController)
        }
        composable(MoReScreens.ProfileScreen.name){
            Scaffoldlayout(navController = navController)
        }
        composable(MoReScreens.NotifScreen.name){
            ScaffoldNotif(navController = navController)
        }
    }
    
}








