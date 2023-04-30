package com.lego.myladder.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lego.myladder.presentation.theme.MyLadderTheme

@Composable
fun Step(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    cornerRadius: Dp = 8.dp,
    padding: Dp = 4.dp,
    strokeWidth: Dp = 2.dp,
    strokeColor: Color = Color.Black,
    isSelected: Boolean = false,
    content: @Composable () -> Unit
) {
    if (isSelected) {
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(cornerRadius))
                .background(color)
                .drawBehind {
                    drawRoundRect(
                        color = strokeColor,
                        cornerRadius = CornerRadius(cornerRadius.toPx()),
                        topLeft = Offset.Zero,
                        size = this.size,
                        style = Stroke(
                            strokeWidth.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        )
                    )
                }
        ) {
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(cornerRadius))
                    .background(Color.Cyan)
            ) {
                content()
            }
        }
    } else {
        Box(
            modifier = modifier
                .padding(padding)
                .clip(RoundedCornerShape(cornerRadius))
                .fillMaxHeight()
                .background(Color.Blue)
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShapePreview() {
    MyLadderTheme {
        Step(
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
            cornerRadius = 8.dp,
            padding = 4.dp,
            strokeWidth = 2.dp,
            strokeColor = Color.Black,
            false
        ) {
            Text(text = "1")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShapeIsSelectedPreview() {
    MyLadderTheme {
        Step(
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
            cornerRadius = 8.dp,
            padding = 4.dp,
            strokeWidth = 2.dp,
            strokeColor = Color.Black,
            true
        ) {
            Text(text = "1")
        }
    }
}