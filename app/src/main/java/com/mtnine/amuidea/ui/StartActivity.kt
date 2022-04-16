package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityStartBinding
import com.mtnine.amuidea.util.StringUtil.IS_LAST_ACTIVITY_SPLASH
import com.mtnine.amuidea.util.Util
import com.mtnine.amuidea.vm.StartViewModel

class StartActivity : BaseActivity<ActivityStartBinding, StartViewModel>(R.layout.activity_start) {

    override fun getViewModel(): Class<StartViewModel> {
        return StartViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnStart.setOnClickListener {
            val id = viewModel.getLoginId(applicationContext)
            val date = Util.getDateFormat()

            viewModel.callAddWord(id, date)?.observe(this, {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(IS_LAST_ACTIVITY_SPLASH, false)
                startActivity(intent)
                finish()
            })
        }
    }
}