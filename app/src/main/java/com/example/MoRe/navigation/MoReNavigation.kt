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
        composable(MoReScreens.PabrikScreen.name){
            ScaffoldListMesin(searchViewModel = SearchViewModel(), navController = navController, scope = scope, modalBottomSheetState = modalBottomSheetState)
        }
        composable(MoReScreens.DetailScreen.name){
            ScaffoldDetailMesin(navController = navController)
        }
        composable(MoReScreens.VerificationScreen.name){
            moreVerifScreen(navController = navController)
        }

        val Homeroute = MoReScreens.HomeScreen.name
        composable("$Homeroute/{email}/{pass}",
            arguments = listOf(
                navArgument(name = "email"){
                    type = NavType.StringType
                },
                navArgument(name = "pass"){
                    type = NavType.StringType
                }
            )) { navBack ->
            ScaffoldHome(
                searchViewModel = SearchViewModel(),
                navController = navController,
                email = navBack.arguments!!.getString("email"),
                pass = navBack.arguments!!.getString("pass")
            )
        }
//            navBack.arguments?.getString("email").let { email->
//
//                val emailkey = email
//                val passkey = pass
//                ScaffoldHome(
//                    searchViewModel = SearchViewModel(), navController = navController,
//                    email = emailkey,
//
//                )
//            }
//           navBack.arguments?.getString("pass").let { pass->
//               ScaffoldHome(searchViewModel = SearchViewModel(), navController = navController,
//                   pass = pass
//               )
//           }

        composable(MoReScreens.ProfileScreen.name){
            Scaffoldlayout(navController = navController)
        }
        composable(MoReScreens.NotifScreen.name){
            ScaffoldNotif(navController = navController)
        }
    }
    
}








