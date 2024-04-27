package com.example.android

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun findByEmail(email: String): User?

    @Insert
    suspend fun insertUser(user: User)
}