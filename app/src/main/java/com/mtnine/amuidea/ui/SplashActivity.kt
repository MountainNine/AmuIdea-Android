package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mtnine.amuidea.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.BaseTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}