package com.example.pdf

class CsvParser {
    fun parse(line :String):Questions{
        val tokens  = line.split(",")
        return Questions(
            question = tokens[Constants.ColumnIndex.QUESTION],
            answer1 = tokens[Constants.ColumnIndex.ANSWER1],
            answer2 = tokens[Constants.ColumnIndex.ANSWER2],
            answer3 = tokens[Constants.ColumnIndex.ANSWER3],
            answer4 = tokens[Constants.ColumnIndex.ANSWER4],
            answer5 = tokens[Constants.ColumnIndex.ANSWER5],
            answer6 = tokens[Constants.ColumnIndex.ANSWER6],
            answer7 = tokens[Constants.ColumnIndex.ANSWER7],
            answer8 = tokens[Constants.ColumnIndex.ANSWER8],
        )
    }
    fun parseAnswer(line :String):Answer{
        val tokens  = line.split(",")
        return Answer(
            question = tokens[Constants.AnswerIndex.QUESTION],
            answer1 = tokens[Constants.AnswerIndex.ANSWER1],
            answer2 = tokens[Constants.AnswerIndex.ANSWER2],
            answer3 = tokens[Constants.AnswerIndex.ANSWER3],
            answer4 = tokens[Constants.AnswerIndex.ANSWER4],
            answer5 = tokens[Constants.AnswerIndex.ANSWER5],
            answer6 = tokens[Constants.AnswerIndex.ANSWER6],
            answer7 = tokens[Constants.AnswerIndex.ANSWER7],
            answer8 = tokens[Constants.AnswerIndex.ANSWER8],
        )
    }
}