package com.mtnine.amuidea.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.data.ApiInterface
import com.mtnine.amuidea.model.User
import com.mtnine.amuidea.model.LoginResponse
import com.mtnine.amuidea.data.RetrofitClient
import com.mtnine.amuidea.databinding.ActivityLoginBinding
import com.mtnine.amuidea.vm.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {
     override lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.onAccountClick.observe(this, {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        })
        val id: String = binding.editId.text.toString()
        val pw: String = binding.editPw.text.toString()

        viewModel.onLoginClick.observe(this, {
            if (binding.editId.text.isEmpty()) {
                showToast("아이디를 입력하세요.")
            } else if (binding.editPw.text.isEmpty()) {
                showToast("비밀번호를 입력하세요.")
            } else {
                viewModel.callLogin(id, pw)!!.observe(this, { loginResponse ->
                    val token: String = loginResponse.body!!
                    val statusCode: String = loginResponse.statusCode!!
                    if (statusCode.equals("200")) {
                        showToast("로그인 성공")
                        val intent = Intent(this, StartActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        showToast("로그인 실패")
                    }
                })
            }
        })
    }
}