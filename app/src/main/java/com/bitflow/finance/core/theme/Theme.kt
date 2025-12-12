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
    primary = PrimaryBlue,
    onPrimary = White,
    primaryContainer = PrimaryBlueDark,
    onPrimaryContainer = PrimaryBlueLight,
    secondary = AccentTeal,
    onSecondary = Black,
    secondaryContainer = SurfaceContainer,
    onSecondaryContainer = AccentTeal,
    tertiary = AccentPurple,
    onTertiary = White,
    tertiaryContainer = SurfaceVariant,
    onTertiaryContainer = AccentPurple,
    error = ErrorRed,
    onError = White,
    errorContainer = SurfaceContainer,
    onErrorContainer = AccentRose,
    background = Black,
    onBackground = TextPrimary,
    surface = Surface,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceVariant,
    onSurfaceVariant = TextSecondary,
    outline = BorderDefault,
    outlineVariant = BorderSubtle,
    scrim = Color(0xCC000000),
    inverseSurface = White,
    inverseOnSurface = Black,
    inversePrimary = PrimaryBlueDark,
    surfaceTint = PrimaryBlue
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryBlueDark,
    onPrimary = White,
    primaryContainer = PrimaryBlueLight,
    onPrimaryContainer = PrimaryBlueDark,
    secondary = AccentTeal,
    onSecondary = White,
    secondaryContainer = Color(0xFFCCFBF1),
    onSecondaryContainer = Color(0xFF134E4A),
    tertiary = AccentPurple,
    onTertiary = White,
    tertiaryContainer = Color(0xFFEDE9FE),
    onTertiaryContainer = Color(0xFF5B21B6),
    error = ErrorRed,
    onError = White,
    errorContainer = Color(0xFFFEE2E2),
    onErrorContainer = Color(0xFF991B1B),
    background = Color(0xFFFAFAFA),
    onBackground = Color(0xFF0A0A0B),
    surface = White,
    onSurface = Color(0xFF0A0A0B),
    surfaceVariant = Color(0xFFF5F5F5),
    onSurfaceVariant = Color(0xFF52525B),
    outline = Color(0xFFD4D4D8),
    outlineVariant = Color(0xFFE4E4E7),
    scrim = Color(0x80000000),
    inverseSurface = Color(0xFF18181B),
    inverseOnSurface = Color(0xFFF4F4F5),
    inversePrimary = PrimaryBlueLight,
    surfaceTint = PrimaryBlue
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
