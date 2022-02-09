package com.luan.mvvmestudo.viewmodel.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luan.mvvmestudo.repositories.MainRepository

class MainViewModelFactory (private val repository: MainRepository) : ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>) : T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}

// Desing Pattern utilizado sempre que a viewModel tem algum argumento no construtor