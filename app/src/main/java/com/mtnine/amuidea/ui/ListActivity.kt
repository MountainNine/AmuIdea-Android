package com.mtnine.amuidea.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtnine.amuidea.R
import com.mtnine.amuidea.adapter.ItemAdapter
import com.mtnine.amuidea.base.BaseActivity
import com.mtnine.amuidea.databinding.ActivityListBinding
import com.mtnine.amuidea.util.Util
import com.mtnine.amuidea.vm.ListViewModel

class ListActivity : BaseActivity<ActivityListBinding, ListViewModel>(R.layout.activity_list) {

    override fun getViewModel(): Class<ListViewModel> {
        return ListViewModel::class.java
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val recyclerIdea: RecyclerView = binding.recyclerIdea
        recyclerIdea.layoutManager = LinearLayoutManager(this)
        recyclerIdea.setHasFixedSize(true)
        val itemAdapter = ItemAdapter()
        recyclerIdea.adapter = itemAdapter
        val date = Util.getDateFormat()
        viewModel.callGetIdeas(viewModel.getLoginId(applicationContext), date).observe(this, {
            itemAdapter.setData(it)
        })
    }
}