package com.example.galleryapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryapp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


typealias URLS = String

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var dataList: ArrayList<URLS>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataList = arrayListOf()
        initRecyclerView(dataList)

        val viewModel: MainViewModel by viewModels {
            MainViewModel.Factory
        }
        viewModel.getPhotos()

        lifecycleScope.launch {
            viewModel.photosList.collectLatest { list ->
                list.forEach {
                    dataList.add(it.urls.raw)
                }
                mainAdapter.notifyDataSetChanged()
            }
        }

        binding.randomBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, RandomActivity::class.java))
        }
    }

    private fun initRecyclerView(list: ArrayList<URLS>) {
        mainAdapter = MainAdapter(list)
        binding.gridRv.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2, RecyclerView.VERTICAL, false)
        }
    }
}