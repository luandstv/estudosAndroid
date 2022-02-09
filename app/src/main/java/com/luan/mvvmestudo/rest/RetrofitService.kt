package com.luan.mvvmestudo.rest

import com.luan.mvvmestudo.models.Live
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

    @GET("lista-lives.json")
    fun getAllLives() : Call<List<Live>>  // chama uma lista de lives, Live vem da DataClass Live de dentro da models

    companion object{
        //by lazy é uma forma de instanciar um objeto uma unica vez e somente quando for chamado
        private val retrofitService : RetrofitService by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://d3c0cr0sze1oo6.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstace() : RetrofitService{
            return retrofitService
        }
    }

}