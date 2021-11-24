package com.mtnine.amuidea.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
        if(!getPreferenceString("autoLoginId").equals("") &&
                !getPreferenceString("autoLoginPw").equals("")) {
            binding.checkAutologin.isChecked = true
            checkAutoLogin(getPreferenceString("autoLoginId")!!)
        }
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
                loginResponse(this, binding)
                //TODO: 서버 연동, 자동 로그인 설정
            }
        }
    }

    fun loginResponse(context: Context, binding: ActivityLoginBinding) {
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
                    val token: String = result.token!!
                    if(statusCode.equals("200")) {
                        setPreference("token",token)
                        if(binding.checkAutologin.isChecked) {
                            setPreference("autoLoginId", id)
                            setPreference("autoLoginPw", pw)
                        } else {
                            setPreference("autoLoginId", "")
                            setPreference("autoLoginPw", "")
                        }

                        val intent = Intent(context, StartActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(context, "예기치 못한 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun setPreference(key: String, value: String) {
        val pref : SharedPreferences = getSharedPreferences("DATA_STORE", MODE_PRIVATE)
        val editor : SharedPreferences.Editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getPreferenceString(key: String): String? {
        val pref : SharedPreferences = getSharedPreferences("DATA_STORE", MODE_PRIVATE)
        return pref.getString(key, "")
    }

    fun checkAutoLogin(id: String) {
        val intent : Intent = Intent(this, StartActivity::class.java)
        startActivity(intent)
        finish()
    }
}