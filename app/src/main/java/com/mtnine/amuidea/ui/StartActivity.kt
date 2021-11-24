package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mtnine.amuidea.R
import com.mtnine.amuidea.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityStartBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_start)
        binding.btnStart.setOnClickListener {
/*
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
*/
        }
    }
}