package com.a1ishka.onboarding.screens

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.a1ishka.onboarding.R
import com.a1ishka.onboarding.ui.theme.Abel
import com.a1ishka.onboarding.ui.theme.Roboto

sealed class OnBoardingPage(
    val title: String,
    val titleFontFamily: androidx.compose.ui.text.font.FontFamily,
    val description: String,
    @DrawableRes
    val image: Int,
    val backgroundColor: Color,
    @DrawableRes
    val loaderIcon: Int
) {
    object FirstPage : OnBoardingPage(
        title = "Your first car without a driver's license",
        titleFontFamily = Abel,
        description = "Goes to meet people who just got their license",

        image = R.drawable.img_car1,
        backgroundColor = Color(240,207,105),
        loaderIcon = R.drawable.loader25
    )
    object SecondPage : OnBoardingPage(
        title = "Always there: more than 1000 cars in Tbilisi",
        titleFontFamily = Roboto,
        description = "Our company is a leader by the number of cars in the fleet",
        image = R.drawable.img_car2,
        backgroundColor = Color(183,171,253),
        loaderIcon = R.drawable.loader50
    )
    object ThirdPage : OnBoardingPage(
        title = "Do not pay for parking, maintenance and gasoline",
        titleFontFamily = Roboto,
        description = "We will pay for you, all expenses related to the car",
        image = R.drawable.img_car3,
        backgroundColor = Color(239,180,145),
        loaderIcon = R.drawable.loader75
    )
    object FourthPage : OnBoardingPage(
        title = "29 car models: from Skoda Octavia to Porsche 911",
        titleFontFamily = Roboto,
        description = "Choose between regular car models or exclusive ones",
        image = R.drawable.img_car4,
        backgroundColor = Color(149,182,255),
        loaderIcon = R.drawable.loader100
    )
}