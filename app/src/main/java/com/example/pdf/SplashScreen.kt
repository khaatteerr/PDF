package com.example.pdf


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import java.util.*


@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val sharedPreferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
        val check = sharedPreferences.getBoolean("didPay", false)

        val shePreferencesCode = getSharedPreferences("myCode", Context.MODE_PRIVATE)

        val code = shePreferencesCode.getBoolean("code", false)
        val codeText = shePreferencesCode.getString("codeText", "")
        val checkMonthCode = shePreferencesCode.getInt("month", 1)
        val checkYearCode = shePreferencesCode.getInt("year",1)

        val c = Calendar.getInstance()

        val shePreferencesDate = getSharedPreferences("myDate", Context.MODE_PRIVATE)


        val checkMonth = shePreferencesDate.getInt("month", 1)
        val checkYear = shePreferencesDate.getInt("year", 1)


        val shePreferencesAmount = getSharedPreferences("myAmount", Context.MODE_PRIVATE)
        val MonthAmount = shePreferencesAmount.getString("MonthAmount", " ")

        Log.d("akbva", checkMonthCode.toString())


        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            if (check || code) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                if ((checkMonth in 1..9 && c.get(Calendar.MONTH) == checkMonth + 3 && MonthAmount == "60.0"
                            || checkMonth in 1..11 && c.get(Calendar.MONTH) == checkMonth + 1 && MonthAmount == "25.0")
                    || (checkMonth in 1..6 && c.get(Calendar.MONTH) == checkMonth + 6 && MonthAmount == "75.0")
                    || (checkMonth == c.get(Calendar.MONTH) && checkYear == c.get(Calendar.YEAR) + 1) && MonthAmount == "99.0"
                ) {

                    val editorDate = sharedPreferences.edit()
                    editorDate.putBoolean("didPay", false)
                    editorDate.apply()

                }
                if (checkMonth == 10 && c.get(Calendar.MONTH) == 1 && MonthAmount == "60.0"
                    || (checkMonth == 11 && c.get(Calendar.MONTH) == 2 && MonthAmount == "60.0")
                    || (checkMonth == 12 && c.get(Calendar.MONTH) == 3 && MonthAmount == "60.0")
                ) {
                    val editorDate = sharedPreferences.edit()
                    editorDate.putBoolean("didPay", false)
                    editorDate.apply()
                }
                if (checkMonth == 7 && c.get(Calendar.MONTH) == 1 && MonthAmount == "75.0"
                    || (checkMonth == 8 && c.get(Calendar.MONTH) == 2 && MonthAmount == "75.0")
                    || (checkMonth == 9 && c.get(Calendar.MONTH) == 3 && MonthAmount == "75.0")
                    || checkMonth == 10 && c.get(Calendar.MONTH) == 4 && MonthAmount == "75.0"
                    || (checkMonth == 11 && c.get(Calendar.MONTH) == 5 && MonthAmount == "75.0")
                    || (checkMonth == 12 && c.get(Calendar.MONTH) == 6 && MonthAmount == "75.0")
                ) {
                    val editorDate = sharedPreferences.edit()
                    editorDate.putBoolean("didPay", false)
                    editorDate.apply()
                }
                if (checkMonth == 12 && c.get(Calendar.MONTH) == 1 && MonthAmount == "25.0") {
                    val editorDate = sharedPreferences.edit()
                    editorDate.putBoolean("didPay", false)
                    editorDate.apply()
                }
                if ((codeText == "5484316" && checkMonthCode in 1..11 && c.get(Calendar.MONTH) == checkMonthCode + 1) ||
                    (checkMonthCode == 12 && c.get(Calendar.MONTH) == 1 && codeText == "5484316")
                    || (codeText == "3548949" && checkMonthCode in 1..9 && c.get(Calendar.MONTH) == checkMonthCode + 3) ||
                    (codeText == "3548949" && checkMonthCode == 10 && c.get(Calendar.MONTH) == 1) ||
                    (codeText == "3548949" && checkMonthCode == 11 && c.get(Calendar.MONTH) == 2) ||
                    (codeText == "3548949" && checkMonthCode == 12 && c.get(Calendar.MONTH) == 3)
                    || (codeText == "9587654" && checkMonthCode in 1..6 && c.get(Calendar.MONTH) == checkMonthCode + 6) ||
                    (codeText == "9587654" && checkMonthCode == 7 && c.get(Calendar.MONTH) == 1) ||
                    (codeText == "9587654" && checkMonthCode == 8 && c.get(Calendar.MONTH) == 2) ||
                    (codeText == "9587654" && checkMonthCode == 9 && c.get(Calendar.MONTH) == 3) ||
                    (codeText == "9587654" && checkMonthCode == 10 && c.get(Calendar.MONTH) == 4) ||
                    (codeText == "9587654" && checkMonthCode == 11 && c.get(Calendar.MONTH) == 5) ||
                    (codeText == "9587654" && checkMonthCode == 12 && c.get(Calendar.MONTH) == 6)||
                    (codeText == "3659754" && checkMonthCode  == c.get(Calendar.MONTH) && checkYearCode == c.get(Calendar.YEAR) + 1)
                ) {
                    val editorDate = sharedPreferences.edit()
                    editorDate.putBoolean("code", false)
                    editorDate.apply()
                }
//                if (checkMonthCode == 12 && c.get(Calendar.MONTH) == 1 &&codeText=="5484316"){
//                    val editorDate = sharedPreferences.edit()
//                    editorDate.putBoolean("code", false)
//                    editorDate.apply()
//                }


            } else {

                startActivity(Intent(this, Pay::class.java))
                finish()
            }

        }, 3000) // 3000 is the delayed time in milliseconds.


    }


}