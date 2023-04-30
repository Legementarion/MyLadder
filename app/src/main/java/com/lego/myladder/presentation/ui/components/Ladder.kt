package com.lego.myladder.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lego.myladder.presentation.theme.MyLadderTheme

@Composable
fun LadderView(label: String, currentIndex: Int, value: IntArray) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var prevValue = 0
        var prevHeight: Int = value.first()
        val values = value.reversed()
        val columnHeight = values.mapIndexed { index, value ->
            if (value == prevValue) {
                prevHeight
            } else {
                prevValue = value
                prevHeight = (index * 20) + 40
                prevHeight
            }
        }

        Row {
            for (i in 0 until values.count()) {
                Step(
                    Modifier
                        .height(columnHeight[i].dp)
                        .align(Alignment.Bottom)
                        .padding(4.dp, 0.dp),
                    isSelected = currentIndex == i
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxHeight()
                            .align(Alignment.CenterVertically),
                        textAlign = TextAlign.Center,
                        text = values.elementAt(i).toString(),
                        color = Color.Yellow,
                    )
                }
            }
        }
        Text(text = label)
    }
}

@Preview(showBackground = true)
@Composable
fun LadderPreview() {
    MyLadderTheme {
        LadderView(label = "ololo", 2, value = intArrayOf(5, 4, 3, 3, 2))
    }
}