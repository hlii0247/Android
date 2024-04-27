package com.example.android

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    var isLoggedIn by rememberSaveable { mutableStateOf(false) }
                    var showForgotPassword by rememberSaveable { mutableStateOf(false) }
                    var showRegistration by rememberSaveable { mutableStateOf(false) }

                    if (isLoggedIn) {
                        BottomNavigationBar()
                    } else if (showForgotPassword) {
                        // Display forgot password screen or logic
                        Text("Forgot Password Screen Placeholder")
                    } else if (showRegistration) {
                        RegisterScreen()
                    } else {
                        LoginForm(
                            onLogin = { username, password ->
                                if (username == "admin" && password == "admin") {
                                    isLoggedIn = true
                                }
                            },
                            onForgotPassword = {
                                showForgotPassword = true
                            },
                            onRegister = {
                                showRegistration = true
                            }
                        )
                    }
                }
            }
        }
    }
}

