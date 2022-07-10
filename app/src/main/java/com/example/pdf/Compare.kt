package com.example.pdf

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

class Compare:AppCompatActivity() {



    val matchList= mutableListOf<Answer>()
    val matches:List<Answer>
        get() = matchList

    var matchIndex = 0

    fun addMatch(match:Answer){
        matchList.add(match)
    }
    fun getCurrentMatch():Answer = matchList[matchIndex]


    fun getNextMatch():Answer{
        matchIndex++
        if (matchIndex == matchList.size){
            matchIndex= 0
        }

        return matchList[matchIndex]
    }

    fun getPreviousMatch():Answer{
        matchIndex--
        if (matchIndex == -1 ){
            matchIndex= matchList.size-1
        }
        return matchList[matchIndex]

    }

}