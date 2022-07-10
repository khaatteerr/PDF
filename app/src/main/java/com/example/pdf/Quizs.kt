package com.example.pdf

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_quizs.*

class Quizs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizs)



        Q1.setOnClickListener { selectQuiz(0) }
        Q2.setOnClickListener { selectQuiz(2) }
        Q3.setOnClickListener { selectQuiz(4) }
        Q4.setOnClickListener { selectQuiz(6) }
        Q5.setOnClickListener { selectQuiz(8) }
        Q6.setOnClickListener { selectQuiz(10) }
        Q7.setOnClickListener { selectQuiz(12) }
        Q8.setOnClickListener { selectQuiz(14) }
        Q9.setOnClickListener { selectQuiz(16) }
        Q10.setOnClickListener { selectQuiz(18) }
        Q11.setOnClickListener { selectQuiz(20) }
        Q12.setOnClickListener { selectQuiz(22) }


    }

    private fun selectQuiz(questions: Int) {

        val list = listOf(
            "thefirstbook", "comparematrix",
            "partoneq2", "partonea2",
            "threequestions", "threeanswer",
            "quiznumberfour", "answerquizfour",
            "fivequestions", "fiveanswer",
            "sixquestions", "sixanswers",
            "sevenquestion", "sevenanswer",
            "eightquestion", "eightanswer",
            "ninequestion", "nineanswer",
            "q10", "answer10",
            "question11", "answer11",
            "com", "answer12"
        )

        val intent = Intent(this, WrittenQuestions::class.java)
        intent.putExtra("Q1", list[questions])
        intent.putExtra("Q1_", list[questions + 1])
        startActivity(intent)
    }

}