package com.worldline.quiz

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wordline.quiz.QuizViewModel


@Composable
fun App(
    navController: NavHostController = rememberNavController(),
    quizViewModel: QuizViewModel = QuizViewModel()
) {

    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = "/welcome",
        ) {


            composable(route = "/welcome") {
                welcomeScreen(
                    onStartButtonPushed = {
                        navController.navigate(route = "/quiz")
                    }
                )
            }
            composable(route = "/quiz") {
                val questions by quizViewModel.questionState.collectAsState()
                questionScreen(
                    questions = questions,
                    onFinishButtonPushed = {
                            score: Int, questionSize: Int -> navController.navigate(route = "/score/$score/$questionSize")
                    }
                )
            }
            composable(route = "/score/{score}/{total}") {
                scoreScreen(
                    score = it.arguments?.getString("score")?.toInt() ?:-1,
                    total = it.arguments?.getString("total")?.toInt() ?:-1,
                    onResetButtonPushed = {
                        navController.navigate(route = "/quiz")
                    },
                    onNewQuizButtonPushed = {
                        quizViewModel.refreshQuiz()
                        navController.navigate(route = "/quiz")
                    }
                )
            }

        }
    }
}
