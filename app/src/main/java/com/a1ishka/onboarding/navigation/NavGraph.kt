package com.a1ishka.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.a1ishka.onboarding.screens.HomeScreen
import com.a1ishka.onboarding.screens.OnboardingScreen

@Composable
fun NavGraph(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navHostController, startDestination = startDestination){
        composable(route = Screen.Onboarding.route){
            OnboardingScreen(navController = navHostController)
        }
        composable(route = Screen.Home.route){
            HomeScreen()
        }
    }
}