package com.example.MoRe.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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

        val pabrikScreen  = MoReScreens.PabrikScreen.name
        composable("$pabrikScreen/{idPabrik}",
                arguments = listOf(
                navArgument(name = "idPabrik"){
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable = true
                })){navPabrik ->
            ScaffoldListMesin(searchViewModel = SearchViewModel(),
                navController = navController,
                scope = scope,
                modalBottomSheetState = modalBottomSheetState,
                idPabrik = navPabrik.arguments?.getString("idPabrik")
                )
        }
        val detailScreen =MoReScreens.DetailScreen.name
        composable("$detailScreen/{idPabrik}/{idMesin}",
            arguments = listOf(
                navArgument(name = "idPabrik"){
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable = true
                },
                navArgument(name = "idMesin"){
                    type = NavType.StringType
                    defaultValue ="1"
                    nullable = true
                }
            )){navDetails ->
            ScaffoldDetailMesin(navController = navController,
                idPabrik = navDetails.arguments?.getString("idPabrik"),
                idMesin = navDetails.arguments?.getString("idMesin"))
        }
        composable(MoReScreens.VerificationScreen.name){
            moreVerifScreen(navController = navController)
        }

        val Homeroute = MoReScreens.HomeScreen.name
        composable("$Homeroute/{email}/{pass}",
            arguments = listOf(
                navArgument(name = "email"){
                    type = NavType.StringType
                    defaultValue = "Erico"
                    nullable = true
                },
                navArgument(name = "pass"){
                    type = NavType.StringType
                    defaultValue ="12345"
                    nullable = true

                }
            )) { navBack ->
            ScaffoldHome(
                searchViewModel = SearchViewModel(),
                navController = navController,
                email = navBack.arguments?.getString("email"),
                pass = navBack.arguments?.getString("pass"),
            )
        }

        composable(MoReScreens.ProfileScreen.name){
            Scaffoldlayout(navController = navController)
        }

        val notifScreen = MoReScreens.NotifScreen.name
        composable("$notifScreen/{idPabrik}/{idMesin}",
                arguments = listOf(
                navArgument(name = "idPabrik"){
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable = true
                },
            navArgument(name = "idMesin"){
                type = NavType.StringType
                defaultValue ="1"
                nullable = true
            })){navNotif ->
            ScaffoldNotif(navController = navController,
                idPabrik = navNotif.arguments?.getString("idPabrik"),
                idMesin = navNotif.arguments?.getString("idMesin"))
        }
    }

}








