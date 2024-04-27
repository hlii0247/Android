package com.example.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: UserRepository
    init{
        cRepository = UserRepository(application)
    }
    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insert(user)
    }
}