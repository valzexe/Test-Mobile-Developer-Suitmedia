package com.example.mobiletestsuitmediamsib.di

import com.example.mobiletestsuitmediamsib.data.retrofit.ApiConfig
import com.example.mobiletestsuitmediamsib.repository.UserRepository

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}