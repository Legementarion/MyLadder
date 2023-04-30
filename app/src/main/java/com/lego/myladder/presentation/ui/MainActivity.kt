package com.lego.myladder.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lego.myladder.R
import com.lego.myladder.domain.models.Ladder
import com.lego.myladder.presentation.theme.MyLadderTheme
import com.lego.myladder.presentation.ui.components.ConfirmActionDialog
import com.lego.myladder.presentation.ui.components.LadderView
import com.lego.myladder.presentation.ui.components.MainAnimation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLadderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val showDialog = remember { mutableStateOf(false) }
                    val dialogMessage =
                        remember { mutableStateOf("Вы уверены, что хотите выполнить это действие?") }

                    if (showDialog.value) {
                        ConfirmActionDialog(
                            message = dialogMessage.value,
                            onConfirm = {
                                mainViewModel.hardReset()
                            },
                            onDismiss = {
                                showDialog.value = false
                            }
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .absolutePadding(16.dp, 32.dp, 16.dp, 16.dp)
                    ) {

                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(modifier = Modifier
                                .size(24.dp)
                                .background(Color.LightGray, CircleShape),
                                onClick = { showDialog.value = true }) {
                                Icon(
                                    Icons.Filled.Clear,
                                    "reset all icon",
                                    tint = Color.Black
                                )
                            }
                        }
                        MainAnimation(
                            Modifier
                                .fillMaxWidth()
                                .height(320.dp)
                        )

                        val pullUps = remember {
                            mutableStateOf(Ladder(0, sequenceOf(1)))
                        }
                        val pullDowns = remember {
                            mutableStateOf(Ladder(0, sequenceOf(1)))
                        }

                        val pullUpIndex = remember {
                            mutableStateOf(0)
                        }
                        val pullDownIndex = remember {
                            mutableStateOf(0)
                        }

                        mainViewModel.ladder.observe(this@MainActivity) {
                            pullUps.value = it.up
                            pullDowns.value = it.down
                        }

                        mainViewModel.upIndex.observe(this@MainActivity) {
                            pullUpIndex.value = it
                        }
                        mainViewModel.downIndex.observe(this@MainActivity) {
                            pullDownIndex.value = it
                        }

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f, true),
                            ) {
                                LadderBlock(
                                    getString(R.string.pull_up_bars),
                                    pullUpIndex.value,
                                    pullUps.value
                                )
                            }
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .weight(1f, true),
                            ) {
                                LadderBlock(
                                    getString(R.string.pull_down_bars),
                                    pullDownIndex.value,
                                    pullDowns.value
                                )
                            }
                        }

                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(onClick = { mainViewModel.resetToday() }) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    text = stringResource(R.string.hold)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun LadderBlock(label: String, index: Int, pulls: Ladder) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LadderView(label, index, pulls.sequence)

            Button(modifier = Modifier
                .wrapContentHeight(),
                onClick = { mainViewModel.next(pulls) }) {
                Text(textAlign = TextAlign.Center, text = stringResource(R.string.next))
            }
            Button(modifier = Modifier
                .wrapContentHeight(),
                onClick = { mainViewModel.increment() }) {
                Text(
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    text = stringResource(R.string.increment)
                )
            }
        }
    }
}