package com.mtnine.amuidea.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityAccountBinding
import com.mtnine.amuidea.vm.AccountViewModel

class AccountActivity : BaseActivity<ActivityAccountBinding, AccountViewModel>(R.layout.activity_account) {
    override val viewModel: AccountViewModel by lazy {
        ViewModelProvider(this).get(AccountViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onAccountClick.observe(this, {
            val id: String = binding.editId.text.toString()
            val pw: String = binding.editPw.text.toString()
            val nick: String = binding.editNickname.text.toString()

            if (id.isBlank()) {
                showToast("아이디를 입력하세요.")
            } else if (pw.isBlank()) {
                showToast("비밀번호를 입력하세요.")
            } else if (nick.isBlank()) {
                showToast("닉네임을 입력하세요.")
            } else {
                viewModel.callAccount(id,pw,nick)!!.observe(this, {userResponse ->
                    val msg: String = userResponse.msg!!
                    val statusCode: String = userResponse.statusCode!!
                    showToast(msg)
                    if(statusCode.equals("200")) {
                        finish()
                    }
                })
            }
        })
    }
}