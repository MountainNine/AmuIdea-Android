package com.mtnine.amuidea.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.data.InitApi
import com.mtnine.amuidea.model.LoginRequest
import com.mtnine.amuidea.model.LoginResponse
import com.mtnine.amuidea.data.RetrofitClient
import com.mtnine.amuidea.databinding.ActivityLoginBinding
import com.mtnine.amuidea.vm.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {
    override val viewModel: LoginViewModel by viewModel()

    var retrofitClient: RetrofitClient? = null
    var initApi: InitApi? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!getPreferenceString("autoLoginId").equals("") &&
                !getPreferenceString("autoLoginPw").equals("")) {
            binding.checkAutologin.isChecked = true
            checkAutoLogin(getPreferenceString("autoLoginId")!!)
        }
        viewModel.onLoginClick.observe(this, {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        })

        viewModel.onLoginClick.observe(this, {
            if (binding.editId.text.isEmpty()) {
                showToast("아이디를 입력하세요.")
            } else if (binding.editPw.text.isEmpty()) {
                showToast("비밀번호를 입력하세요.")
            } else {
                loginResponse(this, binding)
                //TODO: 서버 연동, 자동 로그인 설정
            }
        })
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
                    val token: String = result.body!!
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
                        showToast("로그인 실패")
                    }

                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                showToast("예기치 못한 오류가 발생했습니다.")
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