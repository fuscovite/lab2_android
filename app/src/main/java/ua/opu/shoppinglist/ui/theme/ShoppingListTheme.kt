package ua.opu.shoppinglist.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ShoppingColors = lightColorScheme(
    primary = Color(0xFF006C4C),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF89F8C6),
    secondaryContainer = Color(0xFFC2F0D8),
    background = Color(0xFFFFFBFF),
    surface = Color(0xFFFFFBFF),
)

@Composable
fun ShoppingListTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = ShoppingColors, content = content)
}
