package com.example.android

import android.app.Application
import kotlinx.coroutines.flow.Flow
class UserRepository (application: Application) {
    private var userDao: UserDAO =
        UserDatabase.getDatabase(application).userDAO()
    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }
}