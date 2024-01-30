package com.example.galleryapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.databinding.ActivityRandomBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RandomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRandomBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var dataList: ArrayList<URLS>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataList = arrayListOf()
        initRecyclerView(dataList)

        val viewModel: MainViewModel by viewModels {
            MainViewModel.Factory
        }

        viewModel.getRandom()

        binding.backBtn.setOnClickListener {
            finish()
        }

        lifecycleScope.launch {
            viewModel.randomList.collectLatest { list ->
                list.forEach {
                    dataList.add(it.urls.raw)
                }
                mainAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initRecyclerView(list: ArrayList<URLS>) {
        mainAdapter = MainAdapter(list)
        binding.gridRv.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(this@RandomActivity, 2, RecyclerView.VERTICAL, false)
        }
    }
}