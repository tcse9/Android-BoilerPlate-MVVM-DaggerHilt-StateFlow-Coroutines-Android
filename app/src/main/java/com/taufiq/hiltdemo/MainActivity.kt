package com.taufiq.hiltdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufiq.hiltdemo.adapter.PostAdapter
import com.taufiq.hiltdemo.databinding.ActivityMainBinding
import com.taufiq.hiltdemo.util.ApiState
import com.taufiq.hiltdemo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter:PostAdapter
    private val mainViewModel:MainViewModel by viewModels()

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initObserver()
    }



    private fun initView() {
        adapter = PostAdapter(emptyList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }


    private fun initObserver() {
        mainViewModel.getPost()
        lifecycleScope.launchWhenStarted {
            mainViewModel.postStateFlow.collect{
                when(it){
                    is ApiState.Loading -> {
                        binding.recyclerview.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ApiState.Failure -> {
                        binding.recyclerview.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        Log.d(TAG, "onCreate: ${it.msg}")
                    }

                    is ApiState.Success -> {
                        binding.recyclerview.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        adapter.setData(it.data)
                    }

                    else -> ApiState.Empty
                }
            }
        }
    }
}