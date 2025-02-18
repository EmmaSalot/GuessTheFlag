package com.wordline.quiz

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.worldline.quiz.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "quiz",
    ) {
        App()
    }
}