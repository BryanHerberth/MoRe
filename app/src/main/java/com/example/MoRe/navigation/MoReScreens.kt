package com.example.MoRe.navigation

enum class MoReScreens {
    SplashScreen,
    LoginScreen,
    SignUpScreen,
    VerificationScreen,
    ProfileScreen,
    HomeScreen,
    NotifScreen,
    PabrikScreen,
    MemberScreen,
    SearchScreen,
    DetailScreen;

    companion object {
        fun fromRoute(route: String?): MoReScreens
                = when(route?.substringBefore("/")) {
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            SignUpScreen.name -> SignUpScreen
            VerificationScreen.name -> VerificationScreen
            HomeScreen.name -> HomeScreen
            ProfileScreen.name -> ProfileScreen
            DetailScreen.name -> DetailScreen
            NotifScreen.name -> NotifScreen
            PabrikScreen.name -> PabrikScreen
            MemberScreen.name -> MemberScreen
            SearchScreen.name -> SearchScreen
            null -> LoginScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }


}