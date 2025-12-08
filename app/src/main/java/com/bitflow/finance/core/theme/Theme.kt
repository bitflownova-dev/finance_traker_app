package com.bitflow.finance.core.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = White,
    onPrimary = Black,
    primaryContainer = Zinc800,
    onPrimaryContainer = White,
    secondary = ElectricCyan,
    onSecondary = Black,
    secondaryContainer = Zinc700,
    onSecondaryContainer = ElectricCyan,
    tertiary = ElectricLime,
    onTertiary = Black,
    tertiaryContainer = Zinc700,
    onTertiaryContainer = ElectricLime,
    error = ElectricSalmon,
    onError = Black,
    errorContainer = Zinc800,
    onErrorContainer = ElectricSalmon,
    background = Black,
    onBackground = White,
    surface = Zinc900,
    onSurface = White,
    surfaceVariant = Zinc800,
    onSurfaceVariant = MutedGrey,
    outline = Zinc700,
    outlineVariant = Zinc800,
    scrim = Black,
    inverseSurface = White,
    inverseOnSurface = Black,
    inversePrimary = Black,
    surfaceTint = ElectricCyan
)

private val LightColorScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    primaryContainer = Zinc800,
    onPrimaryContainer = White,
    secondary = ElectricCyan,
    onSecondary = Black,
    secondaryContainer = Zinc700,
    onSecondaryContainer = ElectricCyan,
    tertiary = ElectricLime,
    onTertiary = Black,
    tertiaryContainer = Zinc700,
    onTertiaryContainer = ElectricLime,
    error = ElectricSalmon,
    onError = White,
    errorContainer = Zinc800,
    onErrorContainer = ElectricSalmon,
    background = White,
    onBackground = Black,
    surface = Color(0xFFF4F4F5), // Zinc 100
    onSurface = Black,
    surfaceVariant = Color(0xFFE4E4E7), // Zinc 200
    onSurfaceVariant = Zinc700,
    outline = Zinc700,
    outlineVariant = Zinc800,
    scrim = Black,
    inverseSurface = Black,
    inverseOnSurface = White,
    inversePrimary = White,
    surfaceTint = ElectricCyan
)

@Composable
fun FinanceAppTheme(
    darkTheme: Boolean = true, // Default to Dark Mode as requested
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Disable dynamic color to enforce the custom theme
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb() // Match background for OLED look
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
