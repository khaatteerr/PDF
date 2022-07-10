package com.example.pdf

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.android.synthetic.main.activity_written_questions.*
import java.io.BufferedReader
import java.io.InputStreamReader


class WrittenQuestions : AppCompatActivity() {
    var counter: Int = 0
    var inc = 0
    var grade: Int = 0


    var data_manager = DataManger()
    var answer_manager = Compare()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_written_questions)
        parseFile()
        addCallBacks()
        parseCompare()

        Next_btn.isClickable = false
        button.isClickable = false
        button.setBackgroundColor(Color.DKGRAY)


    }

    fun translate(language: String) {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.GERMAN)
            .setTargetLanguage(language)
            .build()
        val englishGermanTranslator = Translation.getClient(options)
        var conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        englishGermanTranslator.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                // Model downloaded successfully. Okay to start translating.
                // (Set a flag, unhide the translation UI, etc.)
            }
            .addOnFailureListener { exception ->
                // Model couldn’t be downloaded or other internal error.
                // ...
            }
        val list = listOf(r1, r2, r3, r4, r5, r6, r7, r8, theQuestion)
        list.forEach {
            englishGermanTranslator.translate(it.text.toString())
                .addOnSuccessListener { translatedText ->
                    it.text = translatedText

                }
                .addOnFailureListener { exception ->
                    // Error.

                    // ...
                }
        }
    }

    private fun addCallBacks() {
        button.setOnClickListener {
            // translate()
            showAlertDialog()

        }
        Submit_btn.setOnClickListener {
            checks(inc)
            if (data_manager.matchIndex == data_manager.matchList.size - 1) {
                dialog(
                    "finish",
                    "Your grade $grade / ${data_manager.matchList.size}",
                    "Cancel",
                    "finish"
                )
            }


        }

        Next_btn.setOnClickListener {

            dialog("Your Grade is", "You get $counter / 8", "Cancel", "Next")


        }

    }

    private fun checks(inc: Int) {

        if (!r1.isChecked && !r2.isChecked && !r3.isChecked && !r4.isChecked && !r5.isChecked && !r6.isChecked && !r7.isChecked && !r8.isChecked) {
//            Toast.makeText(this, "Please enter your answer", Toast.LENGTH_LONG).show()
        } else {

            val list = listOf(r1, r2, r3, r4, r5, r6, r7, r8)


            list.forEach {
                if (it.text == answer_manager.matchList[inc].answer8
                    || it.text == answer_manager.matchList[inc].answer2
                    || it.text == answer_manager.matchList[inc].answer3
                    || it.text == answer_manager.matchList[inc].answer4
                    || it.text == answer_manager.matchList[inc].answer5
                    || it.text == answer_manager.matchList[inc].answer6
                    || it.text == answer_manager.matchList[inc].answer7
                    || it.text == answer_manager.matchList[inc].answer1
                ) {
                    it.setTextColor(ContextCompat.getColor(applicationContext, R.color.green))

                } else {
                    it.setTextColor(ContextCompat.getColor(applicationContext, R.color.red))
                    it.background =
                        ContextCompat.getDrawable(applicationContext, R.drawable.boarder)
                }
            }
            counter = 0
            list.forEach {
                if (it.isChecked && (it.text == answer_manager.matchList[inc].answer8
                            || it.text == answer_manager.matchList[inc].answer2
                            || it.text == answer_manager.matchList[inc].answer3
                            || it.text == answer_manager.matchList[inc].answer4
                            || it.text == answer_manager.matchList[inc].answer5
                            || it.text == answer_manager.matchList[inc].answer6
                            || it.text == answer_manager.matchList[inc].answer7
                            || it.text == answer_manager.matchList[inc].answer1)
                ) {
                    counter++
                }

            }


            grade += counter

            Submit_btn.isClickable = false
            Submit_btn.setBackgroundColor(Color.DKGRAY)


            Next_btn.isClickable = true
            Next_btn.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.btns))
            button.isClickable = true
            button.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.btns))


        }
    }

    private fun dialog(s1: String, s2: String, s3: String, s4: String) {
        val list = listOf(r1, r2, r3, r4, r5, r6, r7, r8)



        MaterialAlertDialogBuilder(this)
            .setTitle(s1)
            .setMessage(s2)
            .setNeutralButton(s3) { dialog, which ->

                dialog.cancel()
            }
            .setCancelable(false)


            .setPositiveButton(s4) { dialog, which ->
                if (s4 == "Exit") {
                    finish()

                } else {
                    button.isClickable = false
                    button.setBackgroundColor(Color.DKGRAY)
                    nextQuestion()
                    Next_btn.isClickable = false
                    Next_btn.setBackgroundColor(Color.DKGRAY)
                    list.forEach {
                        it.background =
                            ContextCompat.getDrawable(applicationContext, R.drawable.boarderright)
                    }
                }


            }
            .show()
    }

    private fun nextQuestion() {


        bindMatch(data_manager.getNextMatch())
        blacker()
        removeChecks()
        counter = 0
        checks(inc)
        inc++
        Submit_btn.isClickable = true
        Submit_btn.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.btns))


    }


    private fun removeChecks() {
        val list = listOf(r1, r2, r3, r4, r5, r6, r7, r8)
        list.forEach {

            it.setChecked(false)
        }
    }

    private fun blacker() {
        val list = listOf(r1, r2, r3, r4, r5, r6, r7, r8)
        list.forEach {
            it.setTextColor(Color.BLACK)
        }
    }

    private fun parseFile() {
        val Q1 = intent.extras!!.getString("Q1")

        //fa1thefirstbook
        val inputStream = assets.open("$Q1.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        buffer.forEachLine {
            val currentMatch = parser.parse(it)
            data_manager.addMatch(currentMatch)
            bindMatch(data_manager.getCurrentMatch())
        }


    }

    private fun parseCompare() {
//fa2comparematrix
        val Q1_ = intent.extras!!.getString("Q1_")

        val inputStream = assets.open("$Q1_.csv")
        val buffer = BufferedReader(InputStreamReader(inputStream))
        val parser = CsvParser()
        buffer.forEachLine {
            val currentMatch = parser.parseAnswer(it)
            answer_manager.addMatch(currentMatch)

        }


    }

    private fun bindMatch(match: Questions) {
        apply {
            theQuestion.text = match.question
            r1.text = match.answer1
            r2.text = match.answer2
            r3.text = match.answer3
            r4.text = match.answer4
            r5.text = match.answer5
            r6.text = match.answer6
            r7.text = match.answer7
            r8.text = match.answer8
        }
    }

    override fun onBackPressed() {

        dialog("Exit", "Are you sure to exit", "Cancel", "Exit")

    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("AlertDialog")
        val items = arrayOf(
          "ARABIC", "ENGLISH", "TURKISH", "FRENCH",  "RUSSIAN", "PERSIAN"
        )
        val checkedItem = 1
        alertDialog.setSingleChoiceItems(items, checkedItem,
            DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    0 -> {
                        translate(TranslateLanguage.ARABIC)
                        dialog.dismiss()
                    }
                    1 -> {
                        translate(TranslateLanguage.ENGLISH)
                        dialog.dismiss()

                    }
                    2 -> {
                        translate(TranslateLanguage.TURKISH)
                        dialog.dismiss()

                    }
                    3 -> {

                        translate(TranslateLanguage.FRENCH)
                        dialog.dismiss()
                    }
                    4 -> {
                        translate(TranslateLanguage.RUSSIAN)
                        dialog.dismiss()
                    }
                    5 -> {
                        translate(TranslateLanguage.PERSIAN)
                        dialog.dismiss()
                    }
                }
            })

        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
    }
}