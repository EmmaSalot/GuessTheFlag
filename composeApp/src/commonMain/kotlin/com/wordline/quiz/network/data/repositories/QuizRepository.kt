package com.wordline.quiz.network.data.repositories

import com.wordline.quiz.network.data.Answer
import com.wordline.quiz.network.data.Question
import com.wordline.quiz.network.data.QuizApiDatasource
import com.wordline.quiz.network.data.datasources.MockDataSource
import kotlinx.atomicfu.atomic

class QuizRepository {
    private val mockDataSource = MockDataSource()
    private val quizApiDatasource = QuizApiDatasource()

    private val answerIdCounter = atomic(0)

    suspend fun fetchQuiz(): List<Question> {
        val allCountries = quizApiDatasource.getAllCountries().countries.shuffled().take(40)
        val questions = mutableListOf<Question>()

        for (i in 0 until 5) {
            val country = allCountries[i]
            val correctAnswer = Answer(id = answerIdCounter.incrementAndGet(), label = country.name)

            val incorrectAnswers = allCountries
                .filter { it.name != country.name }
                .shuffled()
                .take(3)
                .map { c -> Answer(id = answerIdCounter.incrementAndGet(), label = c.name) }

            val answers = (incorrectAnswers + correctAnswer).shuffled()
            val correctAnswerId = correctAnswer.id

            questions.add(
                Question(
                    id = i,
                    flag = country.flag,
                    correctAnswerId = correctAnswerId,
                    answers = answers
                )
            )
        }
        return questions
    }


    suspend fun updateQuiz(): List<Question> {
        try {
            return fetchQuiz()
        } catch (e: Exception) {
            e.printStackTrace()
            return mockDataSource.generateDummyQuestionsList()
        }
    }
}
