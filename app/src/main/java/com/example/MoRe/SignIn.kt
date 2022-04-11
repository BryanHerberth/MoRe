package com.example.MoRe

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlin.math.sign

class SignIn : AppCompatActivity() {
    private fun myToast(Pesan: String,Waktu: Int= Toast.LENGTH_SHORT)
            = Toast.makeText(this,Pesan,Waktu)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_in)

        val signColor = "Belum ada akun ? Daftar"
        val forgroundSpannableString = SpannableString(signColor)
        forgroundSpannableString.setSpan(ForegroundColorSpan(Color.GRAY),0,17,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        signUp.text =forgroundSpannableString

    }

    fun goToRegis(view: View){
        myToast(Pesan = "Daftar Sekarang").show()
        var intentRegis = Intent(this,SignUp::class.java)
        startActivity(intentRegis)
    }
}