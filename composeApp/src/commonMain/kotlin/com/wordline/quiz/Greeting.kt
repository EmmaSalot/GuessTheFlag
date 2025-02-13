package com.wordline.quiz

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Bonjour, ${platform.name}!"
    }
}