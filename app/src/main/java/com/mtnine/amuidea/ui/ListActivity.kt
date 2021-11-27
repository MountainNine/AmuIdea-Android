package com.mtnine.amuidea.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mtnine.amuidea.R
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityListBinding
import com.mtnine.amuidea.vm.ListViewModel

class ListActivity : BaseActivity<ActivityListBinding, ListViewModel>(R.layout.activity_list) {
    override val viewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}