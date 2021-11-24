package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mtnine.amuidea.R
import com.mtnine.amuidea.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btnAccount.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            if(binding.editId.text.isEmpty()) {
                Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show()
            } else if(binding.editPw.text.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                //TODO: 서버 연동, 자동 로그인 설정
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}