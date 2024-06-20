package com.a1ishka.onboarding.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.a1ishka.onboarding.SetBarColor

@Composable
fun HomeScreen() {
    SetBarColor(color = Color(149,182,255))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(149,182,255)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "You are a clever person!",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}
