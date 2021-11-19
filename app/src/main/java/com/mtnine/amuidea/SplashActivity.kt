package com.mtnine.amuidea

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_splash)
        Thread.sleep(1000)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}