package com.mtnine.amuidea.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityAccountBinding
import com.mtnine.amuidea.util.StringUtil
import com.mtnine.amuidea.vm.AccountViewModel

class AccountActivity :
    BaseActivity<ActivityAccountBinding, AccountViewModel>(R.layout.activity_account) {
    lateinit var id: String
    lateinit var pw: String
    lateinit var nick: String

    override fun getViewModel(): Class<AccountViewModel> {
        return AccountViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onAccountClick.observe(this, {
            id = binding.editId.text.toString()
            pw = binding.editPw.text.toString()
            nick = binding.editNickname.text.toString()

            when {
                id.isBlank() -> {
                    showToast(R.string.please_input_id)
                }
                pw.isBlank() -> {
                    showToast(R.string.please_input_pw)
                }
                nick.isBlank() -> {
                    showToast(R.string.please_input_name)
                }
                else -> {
                    viewModel.callAccount(id, pw, nick)?.observe(this, { userResponse ->
                        val msg: String? = userResponse.msg
                        val statusCode: String? = userResponse.statusCode
                        if (msg != null) {
                            showToast(msg)
                        }
                        if (statusCode.equals(StringUtil.OK)) {
                            finish()
                        }
                    })
                }
            }
        })
    }
}