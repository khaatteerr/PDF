package com.example.pdf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addCallBacks()


    }

    private fun addCallBacks(){

        first_questions.setOnClickListener {
            startActivity(Intent(this,Quizs::class.java))
        }
        second_questions.setOnClickListener {
            startActivity(Intent(this,MathQuestions::class.java))
        }
        third_questions.setOnClickListener {

            val intent = Intent(this, WrittenQuestions::class.java)
            intent.putExtra("Q1","part2question")
            intent.putExtra("Q1_","part2answer")
            startActivity(intent)
        }
    }





}