package com.example.MoRe

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        val signColor = "Sudah punya akun ? Masuk"
        val forgroundSpannableString = SpannableString(signColor)
        forgroundSpannableString.setSpan(ForegroundColorSpan(Color.BLACK),19,24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        signUp.text =forgroundSpannableString
    }



}