package com.example.MoRe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlin.math.sign

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signColor = "Belum ada akun ? Masuk"
        val forgroundSpannableString = SpannableString(signColor)
        forgroundSpannableString.setSpan(ForegroundColorSpan(Color.BLACK),17,22,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        signUp.text =forgroundSpannableString


    }
}