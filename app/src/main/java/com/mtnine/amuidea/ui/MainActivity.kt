package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityMainBinding
import com.mtnine.amuidea.model.Post
import com.mtnine.amuidea.vm.MainViewModel
import util.StringUtil.IS_LAST_ACTIVITY_SPLASH
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.putCurrentState(applicationContext)
        lateinit var words: List<String?>
        val isLastActivitySplash = intent.getBooleanExtra(IS_LAST_ACTIVITY_SPLASH, false)
        viewModel.callGetWord(
            viewModel.getLoginId(applicationContext),
            SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(System.currentTimeMillis())
        )!!.observe(this, { wordResponse ->
            words = wordResponse.msg!!
            binding.textWord1.text = words[0]
            binding.textWord2.text = words[1]
            binding.textWord3.text = words[2]
        })

        viewModel.onButtonClick.observe(this, {
            if (binding.editCombi.text!!.isBlank()) {
                showToast("아이디어를 입력해주세요.")
            } else {
                val date =
                    SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN).format(System.currentTimeMillis())
                val strWords = words.joinToString()
                val idea = binding.editCombi.text.toString()
                val post = Post(viewModel.getLoginId(applicationContext), date, strWords, idea)
                viewModel.callAddIdea(post)!!
                    .observe(this, { response ->
                        val statusCode: String = response.statusCode!!
                        if (statusCode.equals("200")) {
                            val intent = Intent(this, ListActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    })
                val intent = Intent(this, ListActivity::class.java)
                intent.putExtra("sentence", binding.editCombi.text)
                startActivity(intent)
            }

        })
    }
}