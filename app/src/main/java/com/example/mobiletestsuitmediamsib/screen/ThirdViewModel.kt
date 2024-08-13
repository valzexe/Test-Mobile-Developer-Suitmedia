package com.example.mobiletestsuitmediamsib.screen

import androidx.lifecycle.ViewModel
import com.example.mobiletestsuitmediamsib.repository.UserRepository

class ThirdViewModel (private val userRepo : UserRepository) : ViewModel() {

    fun getUsers(page: Int, perPage: Int) = userRepo.getUsers(page, perPage)
}