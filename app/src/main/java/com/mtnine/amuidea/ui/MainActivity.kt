package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityMainBinding
import com.mtnine.amuidea.vm.MainViewModel
import util.StringUtil.IS_LAST_ACTIVITY_SPLASH

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.putCurrentState(applicationContext)
        val words = intent.getStringArrayListExtra("words")
        val isLastActivitySplash = intent.getBooleanExtra(IS_LAST_ACTIVITY_SPLASH, false)
        binding.textWord1.text = if (isLastActivitySplash) {
            viewModel.getExistWord(applicationContext, 1)
        } else {
            words?.get(0)
        }
        binding.textWord2.text = if (isLastActivitySplash) {
            viewModel.getExistWord(applicationContext, 2)
        } else {
            words?.get(0)
        }
        binding.textWord3.text = if (isLastActivitySplash) {
            viewModel.getExistWord(applicationContext, 3)
        } else {
            words?.get(0)
        }

        viewModel.onButtonClick.observe(this, {
            if (binding.editCombi.text!!.isBlank()) {
                showToast("아이디어를 입력해주세요.")
            } else {
                val intent: Intent = Intent(this, ListActivity::class.java)
                intent.putExtra("sentence", binding.editCombi.text)
                startActivity(intent)
            }

        })
    }
}