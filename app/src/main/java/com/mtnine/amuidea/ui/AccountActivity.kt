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

    }
}