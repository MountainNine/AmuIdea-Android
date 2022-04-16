package com.mtnine.amuidea.ui

import android.content.Intent
import android.os.Bundle
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityMainBinding
import com.mtnine.amuidea.model.Post
import com.mtnine.amuidea.util.StringUtil
import com.mtnine.amuidea.util.Util
import com.mtnine.amuidea.vm.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var words: List<String?>
        viewModel.callGetWord(
            viewModel.getLoginId(applicationContext),
            Util.getDateFormat()
        )?.observe(this, { wordResponse ->
            words = wordResponse.msg
            binding.textWord1.text = words[0]
            binding.textWord2.text = words[1]
            binding.textWord3.text = words[2]

        })

        binding.btnStart.setOnClickListener {
            if (binding.editCombi.text?.isBlank() == true) {
                showToast(R.string.please_input_idea)
            } else {
                val date =
                    Util.getDateFormat()
                val strWords = words.joinToString()
                val idea = binding.editCombi.text.toString()
                val post = Post(viewModel.getLoginId(applicationContext), date, strWords, idea)
                viewModel.callAddIdea(post)
                    ?.observe(this, { response ->
                        val statusCode: String? = response.statusCode
                        if (statusCode.equals(StringUtil.OK)) {
                            val intent = Intent(this, ListActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    })
            }
        }
    }
}