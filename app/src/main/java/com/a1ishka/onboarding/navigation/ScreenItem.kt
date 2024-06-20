package com.a1ishka.onboarding.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home")
    object Onboarding : Screen(route = "onboarding")
}