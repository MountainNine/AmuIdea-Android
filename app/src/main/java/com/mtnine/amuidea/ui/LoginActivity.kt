package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mtnine.amuidea.R
import com.mtnine.amuidea.data.InitApi
import com.mtnine.amuidea.data.LoginRequest
import com.mtnine.amuidea.data.LoginResponse
import com.mtnine.amuidea.data.RetrofitClient
import com.mtnine.amuidea.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    var retrofitClient: RetrofitClient? = null
    var initApi: InitApi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.btnAccount.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            if (binding.editId.text.isEmpty()) {
                Toast.makeText(this, "아이디를 입력하세요.", Toast.LENGTH_SHORT).show()
            } else if (binding.editPw.text.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
            } else {
                loginResponse(binding)
                //TODO: 서버 연동, 자동 로그인 설정
                val intent = Intent(this, StartActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun loginResponse(binding: ActivityLoginBinding) {
        val id: String = binding.editId.text.toString()
        val pw: String = binding.editPw.text.toString()

        val loginRequest = LoginRequest(id, pw)

        retrofitClient = RetrofitClient.getInstance()
        initApi = RetrofitClient.getRetrofitInterface()
        initApi?.getLoginResponse(loginRequest)?.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("retrofit", "Data fetch Success")
                if(response.isSuccessful && response.body() != null) {
                    val result: LoginResponse = response.body()!!
                    val statusCode: String = result.statusCode!!



                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}