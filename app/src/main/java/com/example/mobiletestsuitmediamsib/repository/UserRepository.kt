package com.example.mobiletestsuitmediamsib.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.mobiletestsuitmediamsib.data.PagingUser
import com.example.mobiletestsuitmediamsib.data.Result
import com.example.mobiletestsuitmediamsib.data.response.DataItem
import com.example.mobiletestsuitmediamsib.data.retrofit.ApiService
import kotlinx.coroutines.Dispatchers

class UserRepository private constructor(
    private var apiService: ApiService
){

    fun getUsers(page: Int, perPage: Int): LiveData<Result<PagingData<DataItem>>> = liveData(
        Dispatchers.IO){
        emit(Result.Loading)
        try {
            val pager = Pager(
                config = PagingConfig(
                    pageSize = perPage
                ),
                pagingSourceFactory = {
                    PagingUser(apiService, page)
                }
            ).liveData
            emit(Result.Success(pager))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error"))
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService).also { instance = it }
            }
    }
}