package com.worldline.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun welcomeScreen(onStartButtonPushed: () -> Unit) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(10.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val imageUrl = "https://api.triviacreator.com/v1/imgs/quiz/cover-cc1ca356-51e2-4bf3-b816-1d88a83ec4f4.png"

                CoilImage(
                    imageModel = { imageUrl },
                    modifier = Modifier.padding(16.dp),
                    loading = {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    },
                    failure = {
                        Text(text = "Failed to load image")
                    }
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Guess the flag",
                        fontSize = 30.sp,
                        modifier = Modifier.padding(all = 10.dp)
                    )
                    Text(
                        modifier = Modifier.padding(all = 10.dp),
                        text = "Test Your Knowledge of World Flags!",
                    )
                    Button(
                        modifier = Modifier.padding(all = 10.dp),
                        onClick = {
                            onStartButtonPushed()
                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Blue,
                            contentColor = Color.White
                        )
                    )
                    {
                        Text("Start the Quiz")
                    }
                }
            }
        }
    }
}

