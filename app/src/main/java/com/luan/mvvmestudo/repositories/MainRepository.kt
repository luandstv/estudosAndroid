package com.luan.mvvmestudo.repositories

import com.luan.mvvmestudo.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService){

    fun getAllLives() = retrofitService.getAllLives()
}