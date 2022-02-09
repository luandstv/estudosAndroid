package com.luan.mvvmestudo.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luan.mvvmestudo.models.Live
import com.luan.mvvmestudo.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val liveList: LiveData<List<Live>> get() = _liveList
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _liveList = MutableLiveData<List<Live>>()
    private val _errorMessage = MutableLiveData<String>()

    fun getAllLives() {

        val request = repository.getAllLives()
        request.enqueue(object : Callback<List<Live>> {
            override fun onResponse(call: Call<List<Live>>, response: Response<List<Live>>) {
                //Quando houver uma resposta
                _liveList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Live>>, t: Throwable) {
                //quando houver uma falha
                _errorMessage.postValue(t.message)
            }

        })
    }
}