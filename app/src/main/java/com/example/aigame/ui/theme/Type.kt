package com.example.aigame.ui.theme

import android.content.Context
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.NativePaint
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat

// Set of Material typography styles to start with
val Typography = Typography(
        bodyLarge = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp
        )
        /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

fun getNativePaint(context: Context, textSize: Float, stroke: Boolean): android.graphics.Paint {
        val customTypeface = ResourcesCompat.getFont(context, com.example.aigame.R.font.buddychampion)

        return Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                this.textSize = textSize
                typeface = customTypeface
                if (stroke) {
                        color = android.graphics.Color.BLACK
                        style = android.graphics.Paint.Style.STROKE
                        strokeWidth = 4f
                        strokeMiter= 1f
                } else {
                        style = android.graphics.Paint.Style.FILL
                        color = android.graphics.Color.WHITE
                }
        }
}