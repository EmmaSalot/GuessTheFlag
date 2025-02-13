package com.wordline.quiz.network.data

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Question(val id:Int, val flag:String, @SerialName("correct_answer_id") val correctAnswerId:Int, val answers:List<Answer>)
