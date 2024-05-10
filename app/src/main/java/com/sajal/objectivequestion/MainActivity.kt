package com.sajal.objectivequestion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Button
import android.widget.TextView
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var answerOptions: RadioGroup
    private lateinit var submitButton: Button

    private val questions: List<Question> =listOf(
        Question("What is the capital of France?", listOf("Berlin","Madrid","Paris","Rome"), "Paris"),
        Question("Which Planet is known as red planet?", listOf("Mars","Venus","Jupiter","Saturn"),"Jupiter"),
        Question("Who wrote Romeo and Juliet?", listOf("Charles Dickens","William Shakespeare","Jane Austin","Mark Twain"),"William Shakespeare")

    )
        private var currentQuestionIndex: Int = 0
    private var userScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionTextView)
        answerOptions = findViewById(R.id.answerOptions)
        submitButton = findViewById(R.id.submitButton)

        displayQuestion()

        submitButton.setOnClickListener {
            checkAnswer()
        }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            questionTextView.text = currentQuestion.question
            answerOptions.removeAllViews()

            for (i in currentQuestion.options.indices) {
                val radioButton = RadioButton(this)
                radioButton.text = currentQuestion.options[i]
                answerOptions.addView(radioButton)
            }
        } else {
            showScore()
        }
    }

    private fun checkAnswer() {
        val selectedRadioButtonId = answerOptions.checkedRadioButtonId
        if (selectedRadioButtonId != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
            val selectedAnswer = selectedRadioButton.text.toString()

            val currentQuestion = questions[currentQuestionIndex]
            if (selectedAnswer == currentQuestion.correctAnswer) {
                userScore++
            }

            currentQuestionIndex++
            displayQuestion()
        } else {
            // No answer selected
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showScore() {
        val resultMessage = "Your Score: $userScore / ${questions.size}"
        Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show()
    }
}