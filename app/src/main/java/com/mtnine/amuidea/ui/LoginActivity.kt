package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityLoginBinding
import com.mtnine.amuidea.vm.LoginViewModel


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {
     override val viewModel: LoginViewModel by lazy {
         ViewModelProvider(this).get(LoginViewModel::class.java)
     }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onAccountClick.observe(this, {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        })

        viewModel.onLoginClick.observe(this, {
            val id: String = binding.editId.text.toString()
            val pw: String = binding.editPw.text.toString()

            if (binding.editId.text.isEmpty()) {
                showToast("아이디를 입력하세요.")
            } else if (binding.editPw.text.isEmpty()) {
                showToast("비밀번호를 입력하세요.")
            } else {
                viewModel.callLogin(id, pw)!!.observe(this, { loginResponse ->
                    val msg: String = loginResponse.msg!!
                    val statusCode: String = loginResponse.statusCode!!
                    showToast(msg)
                    if (statusCode.equals("200")) {
                        val intent = Intent(this, StartActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                })
            }
        })
    }
}