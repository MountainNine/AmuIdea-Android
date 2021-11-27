package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityMainBinding
import com.mtnine.amuidea.vm.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val words = intent.getStringArrayListExtra("words")
        binding.textWord1.text = words?.get(0)
        binding.textWord2.text = words?.get(1)
        binding.textWord3.text = words?.get(2)

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