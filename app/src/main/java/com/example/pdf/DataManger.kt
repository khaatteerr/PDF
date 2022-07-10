package com.example.pdf

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DataManger: AppCompatActivity()  {
     private lateinit var context: Context

     val matchList= mutableListOf<Questions>()
     val matches:List<Questions>
        get() = matchList

      var matchIndex = 0

    fun addMatch(match:Questions){
        matchList.add(match)
    }
    fun getCurrentMatch():Questions = matchList[matchIndex]


    fun getNextMatch( ):Questions{
        matchIndex++
        if (matchIndex == matchList.size){

            val intent = Intent(this,Quizs::class.java)
            startActivity(intent)
        }

        return matchList[matchIndex]
    }

    fun getPreviousMatch():Questions{
        matchIndex--
        if (matchIndex == -1 ){
            matchIndex= matchList.size-1
        }
        return matchList[matchIndex]

    }

      fun dialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("finish")
            .setMessage("You finished the first quiz")
            .setNeutralButton("Cancel") { dialog, which ->

                dialog.cancel()
            }

            .setPositiveButton("Next") { dialog, which ->
                Toast.makeText(this,"Finished",Toast.LENGTH_LONG).show()
            }
            .show()
    }
}