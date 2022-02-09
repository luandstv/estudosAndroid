package com.luan.mvvmestudo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.luan.mvvmestudo.adapters.MainAdapter
import com.luan.mvvmestudo.databinding.ActivityMainBinding
import com.luan.mvvmestudo.repositories.MainRepository
import com.luan.mvvmestudo.rest.RetrofitService
import com.luan.mvvmestudo.viewmodel.main.MainViewModel
import com.luan.mvvmestudo.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstace()

    private val adapter = MainAdapter { live ->
        openLink(live.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        binding.recyclerview.adapter = adapter  // chamando adapter da reciclerView
    }

    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer { lives ->
            Log.i("Lord", "onStart")
            adapter.setLiveList(lives)
        })

        viewModel.errorMessage.observe(this, Observer { messageError ->
            Toast.makeText(this, messageError, Toast.LENGTH_LONG).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllLives()
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)

    }
}