package com.worldline.quiz

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.coil3.CoilImage
import com.wordline.quiz.network.data.Question

@Composable
fun questionScreen(questions: List<Question>, onFinishButtonPushed: (Int, Int) -> Unit) {
    var questionProgress by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }
    var feedbackMessage by remember { mutableStateOf("") }
    var isAnswered by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.padding(60.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                val imageUrl = "https://raw.githubusercontent.com/alexis-bonal/country-flags/refs/heads/main/svg/${questions[questionProgress].flag}"

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
            }
        }
        Column(modifier = Modifier.selectableGroup()) {
            questions[questionProgress].answers.forEach { answer ->
                val isCorrect = answer.id == questions[questionProgress].correctAnswerId
                val answerColor = when {
                    isAnswered && isCorrect -> Color.Green
                    isAnswered && selectedAnswer == answer.id -> Color.Red
                    else -> Color.Unspecified
                }

                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        modifier = Modifier.padding(end = 16.dp),
                        selected = (selectedAnswer == answer.id),
                        onClick = { if (!isAnswered) selectedAnswer = answer.id },
                    )
                    Text(text = answer.label, color = answerColor)
                }
            }
        }

        Text(text = feedbackMessage, modifier = Modifier.padding(8.dp), fontWeight = FontWeight.Bold)

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                modifier = Modifier.padding(bottom = 20.dp),
                onClick = {
                    if (!isAnswered) {
                        if (selectedAnswer == questions[questionProgress].correctAnswerId) {
                            score++
                            feedbackMessage = "Good answer ! 🎉"
                        } else {
                            feedbackMessage = "Bad answer... 😞"
                        }
                        isAnswered = true
                    } else {
                        if (questionProgress < questions.size - 1) {
                            questionProgress++
                            selectedAnswer = -1
                            isAnswered = false
                            feedbackMessage = ""
                        } else {
                            onFinishButtonPushed(score, questions.size)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                if (!isAnswered) {
                    Text("Valider")
                } else if (questionProgress < questions.size - 1) {
                    nextOrDoneButton(Icons.AutoMirrored.Filled.ArrowForward, "Suivant")
                } else {
                    nextOrDoneButton(Icons.Filled.Done, "Terminer")
                }
            }
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth().height(20.dp),
                progress = questionProgress.div(questions.size.toFloat()).plus(1.div(questions.size.toFloat()))
            )
        }
    }
}

@Composable
fun nextOrDoneButton(iv: ImageVector, label: String) {
    Icon(
        iv,
        contentDescription = "Localized description",
        Modifier.padding(end = 15.dp)
    )
    Text(label)
}
