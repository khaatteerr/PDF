package com.example.pdf

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_pay.*
import kotlinx.android.synthetic.main.activity_payment.*
import java.math.BigDecimal
import java.util.*


class Pay : AppCompatActivity() {
    lateinit var amountMonth: String
    var config: PayPalConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
        codeEscape.setOnClickListener {
            dialog()
        }
        config = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId(Constants.Pay.client_id)

        val i = Intent(this, PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(i)

        val shePreferencesAmount = getSharedPreferences("myAmount", Context.MODE_PRIVATE)
        val editorAmount = shePreferencesAmount.edit()
        paypal_btn_one2.setOnClickListener {
            amountMonth = total_tv_one2.text.toString()
            calcAmount(amountMonth.toDouble())
            editorAmount.putString("MonthAmount", amountMonth)
            editorAmount.apply()
        }
        addCallBacks()

    }

    private fun calcAmount(amountQuantaty: Double) {
        // amount=total_tv_three.text.toString().toDouble()
        val payment =
            PayPalPayment(
                BigDecimal.valueOf(amountQuantaty),
                "EUR",
                "Subscription",
                PayPalPayment.PAYMENT_INTENT_SALE
            )
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
        startActivityForResult(intent, 123)
    }

    private fun addCallBacks() {
        next.setOnClickListener {

            when (monat.text) {
                "1 Monat" -> {
                    monat.text = "3 monate"
                    tage.text = "90 Tage"
                    monat2.text = "3 monate"
                    total_tv_one2.text = "60.0"
                }
                "3 monate" -> {
                    monat.text = "6 monate"
                    tage.text = "180 Tage"
                    monat2.text = "6 monate"
                    total_tv_one2.text = "75.0"
                }
                "6 monate" -> {
                    monat.text = "1 jahr"
                    tage.text = "365 Tage"
                    monat2.text = "1 jahr"
                    total_tv_one2.text = "99.0"
                }
                "1 jahr" -> {
                    monat.text = "1 Monat"
                    tage.text = "30 Tage"
                    monat2.text = "1 Monat"
                    total_tv_one2.text = "25.0"
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK) {
                val obj = Intent(this, MainActivity::class.java)
                startActivity(obj)

                val shePreferences = getSharedPreferences("myData", Context.MODE_PRIVATE)
                val editor = shePreferences.edit()
                editor.putBoolean("didPay", true)
                editor.apply()

                val c = Calendar.getInstance()
                val threeMonth = c.get(Calendar.MONTH)
                val sexMonth = c.get(Calendar.MONTH)
                val year = c.get(Calendar.YEAR)
                val shePreferencesDate = getSharedPreferences("myDate", Context.MODE_PRIVATE)
                val editorDate = shePreferencesDate.edit()
                editorDate.putInt("month", threeMonth)
                editorDate.putInt("sexMonth", sexMonth)
                editorDate.putInt("year", year)
                editorDate.apply()

            }
        }
        if (requestCode == 122) {
            if (resultCode == Activity.RESULT_OK) {
                val obj = Intent(this, MainActivity::class.java)
                startActivity(obj)
                val shePreferencesDate2 = getSharedPreferences("myCode2", Context.MODE_PRIVATE)
                val text = shePreferencesDate2.getString("code2", "")
                var m_Text = text
                val c = Calendar.getInstance()
                val Month = c.get(Calendar.MONTH)
                val year = c.get(Calendar.YEAR)

                val shePreferencesDate = getSharedPreferences("myCode", Context.MODE_PRIVATE)
                val editorDate = shePreferencesDate.edit()
                editorDate.putBoolean("code", true)
                editorDate.putString("codeText", m_Text)
                editorDate.putInt("month", Month)
                editorDate.putInt("year", year)
                editorDate.apply()

            }
        }
    }

    private fun dialog() {
        val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Title")

// Set up the input
        val input = EditText(this)
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setHint("Enter Text")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

// Set up the buttons
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            // Here you get get input text from the Edittext
            var m_Text = input.text.toString()
            if (m_Text == "5484316" || m_Text == "3548949" || m_Text == "9587654" || m_Text == "3659754"
                || m_Text == "5854559" || m_Text == "9625484" || m_Text == "9954259" || m_Text == "6354551"
            ) {


                when(m_Text){
                    "5484316"->codePay(12.5)
                    "3548949"-> codePay(30.0)
                    "9587654"-> codePay(37.5)
                    "3659754"-> codePay(50.0)
                    "5854559"-> codePay(18.75)
                    "9625484"-> codePay(45.0)
                    "9954259"-> {
                        codePay(56.25)
                    }
                    "6354551"->{codePay(75.0)}
                    else->Toast.makeText(this,"Invalid",Toast.LENGTH_LONG).show()
                }


                val shePreferencesDate = getSharedPreferences("myCode2", Context.MODE_PRIVATE)
                val editorDate = shePreferencesDate.edit()
                editorDate.putString("code2", m_Text)
                editorDate.apply()


            } else {
                Toast.makeText(this, "NOt valid code", Toast.LENGTH_LONG).show()
            }
        })
        builder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    fun codePay(money: Double) {
        val config = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId(Constants.Pay.client_id)
        val i = Intent(this, PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        startService(i)

        val payment =
            PayPalPayment(
                BigDecimal.valueOf(money),
                "EUR",
                "Subscription",
                PayPalPayment.PAYMENT_INTENT_SALE
            )
        val intent = Intent(this, PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)
        startActivityForResult(intent, 122)

    }

    override fun onDestroy() {
        stopService(Intent(this, PayPalService::class.java))
        super.onDestroy()
    }
}