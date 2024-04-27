package com.example.android

import androidx.compose.ui.text.input.TextFieldValue
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val password: String,
    val gender: String,
    val birthday: TextFieldValue,
    val role: String
)