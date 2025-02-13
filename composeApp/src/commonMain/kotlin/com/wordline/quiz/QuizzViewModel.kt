package com.wordline.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wordline.quiz.network.data.Question
import com.wordline.quiz.network.data.repositories.QuizRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    private var quizRepository: QuizRepository = QuizRepository()
    private var _questionState = MutableStateFlow(listOf<Question>())
    var questionState: StateFlow<List<Question>> = _questionState



    init {
        getQuestionQuiz()
    }

    private fun getQuestionQuiz() {
        viewModelScope.launch(Dispatchers.Default) {
            _questionState.update {
                quizRepository.updateQuiz()
            }
        }
    }
}
