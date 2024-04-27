package com.example.android

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import android.util.Patterns
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterScreen(userViewModel: UserViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = !Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            isError = (password != confirmPassword) && confirmPassword.isNotEmpty()
        )
        Spacer(modifier = Modifier.height(8.dp))
        GenderSelector(gender, onGenderSelect = { gender = it })
        Spacer(modifier = Modifier.height(8.dp))

        // we need date-picker here

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (password == confirmPassword && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    val newUser = User(email = email, password = password, gender = gender, birthday = birthday, role = "User")
                    userViewModel.insertUser(newUser)

                }
            }
        ) {
            Text("Register")
        }
    }
}

@Composable
fun GenderSelector(currentGender: String, onGenderSelect: (String) -> Unit) {
    val options = listOf("Male", "Female", "Other")
    var showMenu by remember { mutableStateOf(false) }

    if (showMenu) {
        AlertDialog(
            onDismissRequest = { showMenu = false },
            confirmButton = {
                options.forEach { option ->
                    Button(
                        onClick = {
                            onGenderSelect(option)
                            showMenu = false
                        }
                    ) {
                        Text(option)
                    }
                }
            },
            dismissButton = {
                Button(onClick = { showMenu = false }) {
                    Text("Cancel")
                }
            },
            title = { Text("Select Gender") }
        )
    }

    TextButton(onClick = { showMenu = true }) {
        Text(text = if (currentGender.isEmpty()) "Select Gender" else currentGender)
    }
}


