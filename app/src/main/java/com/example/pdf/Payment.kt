package com.example.pdf

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.activity_written_questions.*
import java.math.BigDecimal
import java.util.*

class Payment : AppCompatActivity() {

    var config:PayPalConfiguration?=null

    lateinit var amountMonth :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        config=PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId(Constants.Pay.client_id)

        val i = Intent(this,PayPalService::class.java)
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
        startService(i)

        val shePreferencesAmount =getSharedPreferences("myAmount", Context.MODE_PRIVATE)
        val editorAmount=shePreferencesAmount.edit()
        code.setOnClickListener {
            dialog ()
        }
        paypal_btn_one.setOnClickListener {
            amountMonth = total_tv_one.text.toString()
            calcAmount(amountMonth.toDouble())
            editorAmount.putString("MonthAmount",amountMonth)
            editorAmount.apply()
        }
        paypal_btn_three.setOnClickListener {
            amountMonth = total_tv_three.text.toString()
            calcAmount(amountMonth.toDouble())
            editorAmount.putString("MonthAmount",amountMonth)
            editorAmount.apply()

        }
        paypal_btn_sex.setOnClickListener {
            amountMonth =total_tv_sex.text.toString()
            calcAmount(amountMonth.toDouble())
            editorAmount.putString("MonthAmount",amountMonth)
            editorAmount.apply()
        }
        paypal_btn_year.setOnClickListener {
            amountMonth = total_tv_year.text.toString()
            calcAmount(amountMonth.toDouble())
            editorAmount.putString("MonthAmount",amountMonth)

            editorAmount.apply()
        }


    }
    private fun calcAmount(amountQuantaty:Double){
       // amount=total_tv_three.text.toString().toDouble()
        val payment=PayPalPayment(BigDecimal.valueOf(amountQuantaty),"USD" ,"Subscription",PayPalPayment.PAYMENT_INTENT_SALE)
        val intent =Intent(this,PaymentActivity::class.java)
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config)
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment)
        startActivityForResult(intent,123)
    }
    private fun dialog (){
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
            if (m_Text=="5484316"){
                startActivity(Intent(this,MainActivity::class.java))
            }
            else{
                Toast.makeText(this,"NOt valid code",Toast.LENGTH_LONG).show()
            }
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==123){
            if (resultCode== Activity.RESULT_OK){
                val obj =Intent(this,MainActivity::class.java)
                startActivity(obj)

                val shePreferences =getSharedPreferences("myData", Context.MODE_PRIVATE)
                val editor=shePreferences.edit()
                editor.putBoolean("didPay",true)
                editor.apply()

                val c = Calendar.getInstance()
                val threeMonth = c.get(Calendar.MONTH)
                val sexMonth = c.get(Calendar.MONTH)
                val year = c.get(Calendar.YEAR)
                val shePreferencesDate =getSharedPreferences("myDate", Context.MODE_PRIVATE)
                val editorDate=shePreferencesDate.edit()
                editorDate.putInt("month",threeMonth)
                editorDate.putInt("sexMonth",sexMonth)
                editorDate.putInt("year",year)
                editorDate.apply()

            }
        }
    }

    override fun onDestroy() {
        stopService(Intent(this,PayPalService::class.java))
        super.onDestroy()
    }
}