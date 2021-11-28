package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivitySplashBinding
import com.mtnine.amuidea.vm.SplashViewModel
import util.StringUtil.IS_LAST_ACTIVITY_SPLASH
import java.text.SimpleDateFormat
import java.util.*

class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash) {
    override val viewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.BaseTheme)
        super.onCreate(savedInstanceState)
        lateinit var intent: Intent
        if (!viewModel.getLoginState(applicationContext)) {
            intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            viewModel.getCurrentState(
                applicationContext,
                SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.KOREAN
                ).format(System.currentTimeMillis())
            ).observe(this, {
                when (it.msg) {
                    "0" -> intent = Intent(this, StartActivity::class.java)
                    "1" -> intent = Intent(this, MainActivity::class.java)
                    "2" -> intent = Intent(this, ListActivity::class.java)
                }
                intent.putExtra(IS_LAST_ACTIVITY_SPLASH, true)
                startActivity(intent)
                finish()
            })

        }

    }
}