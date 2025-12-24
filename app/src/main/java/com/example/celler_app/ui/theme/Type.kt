package com.example.celler_app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.celler_app.R

val CellerAppFonts: FontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val AppTypography = Typography(

    headlineLarge = TextStyle(
        fontFamily = CellerAppFonts,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = CellerAppFonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    ),

    bodyMedium = TextStyle(
        fontFamily = CellerAppFonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    labelLarge = TextStyle(
        fontFamily = CellerAppFonts,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)