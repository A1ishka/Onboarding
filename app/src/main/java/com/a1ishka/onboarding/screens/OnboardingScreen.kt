@file:OptIn(ExperimentalFoundationApi::class)

package com.a1ishka.onboarding.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.a1ishka.onboarding.SetBarColor
import com.a1ishka.onboarding.navigation.Screen
import com.a1ishka.onboarding.ui.theme.Roboto
import com.a1ishka.onboarding.viewmodel.OnboardingViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    navController: NavController,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.FirstPage,
        OnBoardingPage.SecondPage,
        OnBoardingPage.ThirdPage,
        OnBoardingPage.FourthPage
    )
    val pagerState = rememberPagerState { 4 }

    SetBarColor(color = Color(240, 207, 105))
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(
                onBoardingPage = pages[position],
                pagerState = pagerState,
                navController = navController,
                onboardingViewModel = onboardingViewModel
            )
        }
    }
}

@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage,
    pagerState: PagerState,
    navController: NavController,
    onboardingViewModel: OnboardingViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    SetBarColor(color = onBoardingPage.backgroundColor)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(onBoardingPage.backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            TitleAndDescription(onBoardingPage = onBoardingPage)
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = onBoardingPage.image),
                contentDescription = "Pager Image"
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.7f)
                        .padding(vertical = 25.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    PagerIdentificator(pagerState = pagerState)
                    SkipButton(
                        onboardingViewModel = onboardingViewModel,
                        navController = navController
                    )
                }
                ButtonNext(
                    onBoardingPage = onBoardingPage,
                    pagerState = pagerState,
                    navController = navController,
                    onboardingViewModel = onboardingViewModel,
                    coroutineScope = coroutineScope
                )
            }
        }
    }
}

@Composable
fun ButtonNext(
    onBoardingPage: OnBoardingPage,
    pagerState: PagerState,
    navController: NavController,
    onboardingViewModel: OnboardingViewModel,
    coroutineScope: CoroutineScope
) {
    Button(
        modifier = Modifier.fillMaxSize(),
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = onBoardingPage.backgroundColor
        ),
        onClick = {
            coroutineScope.launch {
                val nextPage = pagerState.currentPage + 1
                if (nextPage < pagerState.pageCount) {
                    pagerState.animateScrollToPage(nextPage)
                } else {
                    onboardingViewModel.saveOnBoardingState(completed = true)
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            }
        }
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .width(90.dp)
                .height(90.dp),
            painter = painterResource(id = onBoardingPage.loaderIcon),
            contentDescription = "Image"
        )
    }
}

@Composable
fun SkipButton(
    onboardingViewModel: OnboardingViewModel,
    navController: NavController
) {
    TextButton(
        modifier = Modifier.padding(top = 20.dp),
        onClick = {
            onboardingViewModel.saveOnBoardingState(completed = true)
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
        },
    ) {
        Text(
            fontFamily = Roboto,
            fontSize = 20.sp,
            color = Color.White,
            text = "Skip"
        )
    }
}

@Composable
fun TitleAndDescription(
    onBoardingPage: OnBoardingPage
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 35.dp),
        text = onBoardingPage.title,
        fontSize = 27.sp,
        style = TextStyle(
            fontFamily = onBoardingPage.titleFontFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = Color.White
        )
    )
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(top = 20.dp, bottom = 15.dp),
        text = onBoardingPage.description,
        fontSize = 20.sp,
        style = TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Justify,
            color = Color.White
        )
    )
}

@Composable
fun PagerIdentificator(
    pagerState: PagerState
) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth(0.7f)
            .padding(bottom = 20.dp)
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val isCurrentPage = pagerState.currentPage == iteration
            val color = if (isCurrentPage) Color(255, 255, 255, 255) else Color(
                255,
                255,
                255,
                100
            )
            val shape = if (isCurrentPage) RoundedCornerShape(8.dp) else CircleShape
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(shape)
                    .background(color)
                    .size(if (isCurrentPage) 32.dp else 16.dp, 16.dp)
            )
        }
    }
}