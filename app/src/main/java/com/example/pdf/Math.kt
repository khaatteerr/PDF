package com.example.pdf

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_math.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Math : AppCompatActivity() {
    var a = 0

    val q = arrayOf("https://i.im.ge/2022/07/01/ua1PYP.png", "https://i.ibb.co/QJzPWJb/q2.png","https://i.im.ge/2022/07/01/uDneN9.png"
    ,"https://i.im.ge/2022/07/01/uD478m.png","https://i.im.ge/2022/07/01/uD4w9a.png"
    ,"https://i.im.ge/2022/07/01/uD4Kn4.png","https://i.im.ge/2022/07/01/uD4zkp.png"
    ,"https://i.im.ge/2022/07/01/uD4BUr.png","https://i.im.ge/2022/07/02/uITvjc.png"
    ,"https://i.im.ge/2022/07/02/uIT4TG.png","https://i.im.ge/2022/07/02/uIoozz.png"
    ,"https://i.im.ge/2022/07/02/uIormF.png","https://i.im.ge/2022/07/02/uIouWK.png"
    ,"https://i.im.ge/2022/07/02/uIoOo9.png","https://i.im.ge/2022/07/02/uIo1hX.png"
    ,"https://i.im.ge/2022/07/02/uIo2Y8.png","https://i.im.ge/2022/07/02/uIoSBh.png"
    ,"https://i.im.ge/2022/07/02/uIoqaM.png","https://i.im.ge/2022/07/02/uIosyY.png"
    ,"https://i.im.ge/2022/07/02/uIoaHD.png","https://i.im.ge/2022/07/02/uIoDO4.png"
    ,"https://i.im.ge/2022/07/02/uIoLZq.png","https://i.im.ge/2022/07/02/uIohop.png"
    ,"https://i.im.ge/2022/07/02/uIoihP.png","https://i.im.ge/2022/07/02/uIo0b1.png"
    ,"https://i.im.ge/2022/07/02/uIo5Bf.png","https://i.im.ge/2022/07/02/uIojam.png"
    ,"https://i.im.ge/2022/07/02/uIomAr.png","https://i.im.ge/2022/07/02/uIowHW.png"
    ,"https://i.im.ge/2022/07/02/uIoN10.png","https://i.im.ge/2022/07/02/uIoPwT.png"
    ,"https://i.im.ge/2022/07/02/uIoChG.png","https://i.im.ge/2022/07/02/uIoEbx.png"
    ,"https://i.im.ge/2022/07/02/uIXCV4.png","https://i.im.ge/2022/07/02/uIXxrC.png"
    ,"https://i.im.ge/2022/07/02/uIXVf0.png"
    ,"https://i.im.ge/2022/07/02/uIXz5c.png","https://i.im.ge/2022/07/02/uIXJIx.png"
    ,"https://i.im.ge/2022/07/02/uIXnxa.png","https://i.im.ge/2022/07/02/uIX4JJ.png"
    ,"https://i.im.ge/2022/07/02/uIXePS.png","https://i.im.ge/2022/07/02/uIlTu6.png"
    ,"https://i.im.ge/2022/07/02/uIlo9F.png","https://i.im.ge/2022/07/02/uIlrQ9.png"
    ,"https://i.im.ge/2022/07/02/uIlFx8.png","https://i.im.ge/2022/07/02/uIlOnh.png"
    ,"https://i.im.ge/2022/07/02/uIl2qM.png","https://i.im.ge/2022/07/02/uIldkD.png"
    ,"https://i.im.ge/2022/07/02/uIlsu4.png","https://i.im.ge/2022/07/02/uIl7Rq.png"
    ,"https://i.im.ge/2022/07/02/uIlLLP.png","https://i.im.ge/2022/07/02/uIlU31.png"
    ,"https://i.im.ge/2022/07/02/uIlhnf.png","https://i.im.ge/2022/07/02/uIl5tr.png"
    ,"https://i.im.ge/2022/07/02/uIlmF0.png","https://i.im.ge/2022/07/02/uIlwjT.png"
    ,"https://i.im.ge/2022/07/02/uIlPTL.png","https://i.im.ge/2022/07/02/uIly3x.png"
    ,"https://i.im.ge/2022/07/02/uIlA4a.png","https://i.im.ge/2022/07/02/uIlEqJ.png"
    ,"https://i.im.ge/2022/07/02/uIl3zS.png","https://i.im.ge/2022/07/02/uIlbFz.png"
    ,"https://i.im.ge/2022/07/02/uIlcj6.png","https://i.im.ge/2022/07/02/uIlWU9.png"
    ,"https://i.im.ge/2022/07/02/uIlZYX.png","https://i.im.ge/2022/07/02/uIlK48.png"
    ,"https://i.im.ge/2022/07/02/uIlfsh.png","https://i.im.ge/2022/07/02/uIlkyM.png"
    ,"https://i.im.ge/2022/07/02/uIlzzY.png","https://i.im.ge/2022/07/02/uIlpOD.png"
    ,"https://i.im.ge/2022/07/02/uIlvj4.png","https://i.im.ge/2022/07/02/uIlJWC.png"
    ,"https://i.im.ge/2022/07/02/uIlBUp.png","https://i.im.ge/2022/07/02/uIlGYP.png"
    ,"https://i.im.ge/2022/07/02/uIrQsf.png","https://i.im.ge/2022/07/02/uIrTym.png"
    ,"https://i.im.ge/2022/07/02/uIroHr.png","https://i.im.ge/2022/07/02/uIrlOW.png"
    ,"https://i.im.ge/2022/07/02/uIrrm0.png","https://i.im.ge/2022/07/02/uIruZT.png"
    ,"https://i.im.ge/2022/07/02/uIrOoc.png","https://i.im.ge/2022/07/02/uIr1hL.png"
    ,"https://i.im.ge/2022/07/02/uIrsyJ.png","https://i.im.ge/2022/07/02/uIraHy.png"
    ,"https://i.im.ge/2022/07/02/uIrImz.png","https://i.im.ge/2022/07/02/uIrLZ6.png"
    ,"https://i.im.ge/2022/07/02/uIrhXF.png","https://i.im.ge/2022/07/02/uIr0b9.png"
    ,"https://i.im.ge/2022/07/02/uIr5GX.png","https://i.im.ge/2022/07/02/uIrja8.png"
    ,"https://i.im.ge/2022/07/02/uIrwpM.png","https://i.im.ge/2022/07/02/uIrN1Y.png"
    ,"https://i.im.ge/2022/07/02/uIrPwD.png","https://i.im.ge/2022/07/02/uIrtZ4.png"
    ,"https://i.im.ge/2022/07/02/uIrAXC.png","https://i.im.ge/2022/07/02/uIrCiq.png"
    ,"https://i.im.ge/2022/07/02/uIrEbp.png") // q60


    val photos = arrayOf("https://i.ibb.co/zf8VsGL/a2.png", "https://i.ibb.co/QrRMR3C/a22.png","https://i.im.ge/2022/07/01/uD4su1.png"
    ,"https://i.im.ge/2022/07/01/uD4LIW.png","https://i.im.ge/2022/07/01/uD4RTM.png"
    ,"https://i.im.ge/2022/07/01/uD4fqC.png","https://i.im.ge/2022/07/01/uD4JRf.png"
    ,"https://i.im.ge/2022/07/01/uDBQsT.png","https://i.im.ge/2022/07/02/uITBUx.png"
    ,"https://i.im.ge/2022/07/02/uITGYa.png","https://i.im.ge/2022/07/02/uIoYaJ.png"
    ,"https://i.im.ge/2022/07/02/uIobAy.png","https://i.im.ge/2022/07/02/uIocpS.png"
    ,"https://i.im.ge/2022/07/02/uIo81z.png","https://i.im.ge/2022/07/02/uIoRw6.png"
    ,"https://i.im.ge/2022/07/02/uIoWKF.png","https://i.im.ge/2022/07/02/uIoVi9.png"
    ,"https://i.im.ge/2022/07/02/uIokG8.png","https://i.im.ge/2022/07/02/uIoH7h.png"
    ,"https://i.im.ge/2022/07/02/uIopCM.png","https://i.im.ge/2022/07/02/uIon2D.png"
    ,"https://i.im.ge/2022/07/02/uIXNEh.png","https://i.im.ge/2022/07/02/uIoBKC.png"
    ,"https://i.im.ge/2022/07/02/uIoelq.png","https://i.im.ge/2022/07/02/uIXMip.png"
    ,"https://i.im.ge/2022/07/02/uIXTe1.png","https://i.im.ge/2022/07/02/uIXX7f.png"
    ,"https://i.im.ge/2022/07/02/uIXrvr.png","https://i.im.ge/2022/07/02/uIXF2W.png"
    ,"https://i.im.ge/2022/07/02/uIXO60.png","https://i.im.ge/2022/07/02/uIX1VT.png"
    ,"https://i.im.ge/2022/07/02/uIXd0L.png","https://i.im.ge/2022/07/02/uIXsex.png"
    ,"https://i.im.ge/2022/07/02/uIXRJm.png","https://i.im.ge/2022/07/02/uIXZdr.png"
    ,"https://i.im.ge/2022/07/02/uIu1VD.png"
    ,"https://i.im.ge/2022/07/02/uIuSl4.png","https://i.im.ge/2022/07/02/uIuDE1.png"
    ,"https://i.im.ge/2022/07/02/uIuIvf.png","https://i.im.ge/2022/07/02/uIuiVW.png"
    ,"https://i.im.ge/2022/07/02/uIu5r0.png","https://i.im.ge/2022/07/02/uIujgc.png"
    ,"https://i.im.ge/2022/07/02/uIu6DG.png","https://i.im.ge/2022/07/02/uIuPJa.png"
    ,"https://i.im.ge/2022/07/02/uIuANy.png","https://i.im.ge/2022/07/02/uIuxrz.png"
    ,"https://i.im.ge/2022/07/02/uIu356.png","https://i.im.ge/2022/07/02/uIuY8F.png"
    ,"https://i.im.ge/2022/07/02/uIucMK.png","https://i.im.ge/2022/07/02/uIugI9.png"
    ,"https://i.im.ge/2022/07/02/uIu8xX.png","https://i.im.ge/2022/07/02/uIuRJ8.png"
    ,"https://i.im.ge/2022/07/02/uIuZdh.png","https://i.im.ge/2022/07/02/uIuKPM.png"
    ,"https://i.im.ge/2022/07/02/uIukuD.png","https://i.im.ge/2022/07/02/uIuz54.png"
    ,"https://i.im.ge/2022/07/02/uIuH8C.png","https://i.im.ge/2022/07/02/uIuvQq.png"
    ,"https://i.im.ge/2022/07/02/uIuJIp.png","https://i.im.ge/2022/07/02/uIunxP.png"
    ,"https://i.im.ge/2022/07/02/uIu4n1.png","https://i.im.ge/2022/07/02/uIuGdf.png"
    ,"https://i.im.ge/2022/07/02/uIuePm.png","https://i.im.ge/2022/07/02/uIFMkr.png"
    ,"https://i.im.ge/2022/07/02/uIFLLK.png","https://i.im.ge/2022/07/02/uIFmFY.png"
    ,"https://i.im.ge/2022/07/02/uIF6R4.png","https://i.im.ge/2022/07/02/uIFxtf.png"
    ,"https://i.im.ge/2022/07/02/uIFRoT.png","https://i.im.ge/2022/07/02/uIFZYL.png"
    ,"https://i.im.ge/2022/07/02/uIFK4G.png","https://i.im.ge/2022/07/02/uIOQaX.png"
    ,"https://i.im.ge/2022/07/02/uIOTy8.png","https://i.im.ge/2022/07/02/uIOSBp.png"
    ,"https://i.im.ge/2022/07/02/uIOaHf.png","https://i.im.ge/2022/07/02/uIOiiT.png"
    ,"https://i.im.ge/2022/07/02/uIO0bc.png","https://i.im.ge/2022/07/02/uIOVi4.png"
    ,"https://i.im.ge/2022/07/02/uIOelW.png","https://i.im.ge/2022/07/02/uI1M00.png"
    ,"https://i.im.ge/2022/07/02/uI1FSa.png","https://i.im.ge/2022/07/02/uI1jg4.png"
    ,"https://i.im.ge/2022/07/02/uI1Cfm.png","https://i.im.ge/2022/07/02/uI18xL.png"
    ,"https://i.im.ge/2022/07/02/uI1RJG.png","https://i.im.ge/2022/07/02/uI1JLF.png"
    ,"https://i.im.ge/2022/07/02/uI14n9.png","https://i.im.ge/2022/07/02/uI1GqX.png"
    ,"https://i.im.ge/2022/07/02/uI1eP8.png","https://i.im.ge/2022/07/02/uI2Mkh.png"
    ,"https://i.im.ge/2022/07/02/uI2TFM.png","https://i.im.ge/2022/07/02/uI2o9Y.png"
    ,"https://i.im.ge/2022/07/02/uI2XRD.png","https://i.im.ge/2022/07/02/uI2rQ4.png"
    ,"https://i.im.ge/2022/07/02/uI2uLC.png","https://i.im.ge/2022/07/02/uI2Onp.png"
    ,"https://i.im.ge/2022/07/02/uI2St1.png")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)


        math(a)
        ques(a)
        imageView.visibility = View.INVISIBLE

        show_answer.setOnClickListener {
            imageView.visibility = View.VISIBLE
        }
        Next_btn_math.setOnClickListener {
            if (a == photos.size - 1) {

                Toast.makeText(this, "Sie sind in der letzten Frage", Toast.LENGTH_LONG).show()

            } else {

                a++
                math(a)
                ques(a)
                imageView.visibility = View.INVISIBLE
            }
            Next_btn_math.isClickable = false
            Next_btn_math.setBackgroundColor(
                Color.DKGRAY
            )
            Handler().postDelayed({

                Next_btn_math.isClickable = true
                Next_btn_math.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.btns
                    )
                )
            }, 8000)

        }
        pre.setOnClickListener {
            if (a == 0) {

                Toast.makeText(this, "Sie sind in der ersten Frage", Toast.LENGTH_LONG).show()

            } else {
                a--
                math(a)
                ques(a)
                imageView.visibility = View.INVISIBLE
            }
            pre.isClickable = false
            pre.setBackgroundColor(
                Color.DKGRAY
            )
            Handler().postDelayed({

                pre.isClickable = true
                pre.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.btns))
            }, 8000)
        }
    }

    fun math(inc: Int) {
        Glide.with(this)
            .load(photos[inc])
            .into(imageView)
    }

    fun ques(inc: Int){
        Glide.with(this)
            .load(q[inc])
            .into(theQuestion)
    }


}