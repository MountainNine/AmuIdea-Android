package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityStartBinding
import com.mtnine.amuidea.vm.StartViewModel
import util.StringUtil.IS_LAST_ACTIVITY_SPLASH
import java.util.*

class StartActivity : BaseActivity<ActivityStartBinding, StartViewModel>(R.layout.activity_start) {
    override val viewModel: StartViewModel by lazy {
        ViewModelProvider(this).get(StartViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.putCurrentState(applicationContext)

        viewModel.onStartClick.observe(this, {
            viewModel.callGetWord()!!.observe(this, { wordResponse ->
                val msg: List<String> = wordResponse.msg!!
                val intent = Intent(this, MainActivity::class.java)
                intent.putStringArrayListExtra("words", msg as ArrayList<String>)
                viewModel.putWords(applicationContext, msg)
                intent.putExtra(IS_LAST_ACTIVITY_SPLASH, false)
                startActivity(intent)
                finish()
            })
        })
    }
}