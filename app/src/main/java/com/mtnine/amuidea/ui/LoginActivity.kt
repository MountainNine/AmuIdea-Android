package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityLoginBinding
import com.mtnine.amuidea.vm.LoginViewModel
import java.text.SimpleDateFormat
import java.util.*


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

            if (binding.editId.text.isBlank()) {
                showToast("아이디를 입력하세요.")
            } else if (binding.editPw.text.isBlank()) {
                showToast("비밀번호를 입력하세요.")
            } else {
                viewModel.callLogin(id, pw)!!.observe(this, { loginResponse ->
                    val msg: String = loginResponse.msg!!
                    val statusCode: String = loginResponse.statusCode!!
                    showToast(msg)
                    if (statusCode.equals("200")) {
                        viewModel.checkAutoLogin(
                            applicationContext,
                            binding.checkAutologin.isChecked
                        )
                        viewModel.putLoginId(applicationContext, id)
                        viewModel.getCurrentState(
                            id,
                            SimpleDateFormat(
                                "yyyy-MM-dd",
                                Locale.KOREAN
                            ).format(System.currentTimeMillis())
                        ).observe(this, {
                            lateinit var intent: Intent
                            when (it.msg) {
                                "0" -> intent = Intent(this, StartActivity::class.java)
                                "1" -> intent = Intent(this, MainActivity::class.java)
                                "2" -> intent = Intent(this, ListActivity::class.java)
                            }
                            startActivity(intent)
                            finish()
                        })
                    }
                })
            }
        })
    }
}