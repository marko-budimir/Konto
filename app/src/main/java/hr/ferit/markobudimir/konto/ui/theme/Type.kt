package hr.ferit.markobudimir.konto.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import hr.ferit.markobudimir.konto.R

val latoFamily = FontFamily(
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_light, FontWeight.Light),
    Font(R.font.lato_black, FontWeight.Black)
)

val robotoFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_black, FontWeight.Black)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 51.sp
    ),
    displayMedium = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Black,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Black,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Black,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),
    bodySmall = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Light,
        fontSize = 8.sp
    ),
    labelLarge = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = robotoFamily,
        fontWeight = FontWeight.Light,
        fontSize = 8.sp
    )
)
