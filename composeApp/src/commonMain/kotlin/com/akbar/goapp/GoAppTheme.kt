package com.akbar.goapp

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Primary = Color(0xFF006E08)
val PrimaryContainer = Color(0xFF00AA13)
val FoodRed = Color(0xFFE32326)
val SurfaceBg = Color(0xFFFCF8F8)
val SurfaceLow = Color(0xFFF6F3F2)
val SurfaceLine = Color(0xFFE5E2E1)
val TextDark = Color(0xFF1C1B1B)
val TextMuted = Color(0xFF667085)
val Blue = Color(0xFF1E63F2)
val Orange = Color(0xFFD97706)

@Composable
fun GoAppTheme(
    content: @Composable () -> Unit
) {
    androidx.compose.material3.MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Primary,
            primaryContainer = PrimaryContainer,
            secondary = FoodRed,
            background = SurfaceBg,
            surface = Color.White,
            onSurface = TextDark
        ),
        content = content
    )
}
