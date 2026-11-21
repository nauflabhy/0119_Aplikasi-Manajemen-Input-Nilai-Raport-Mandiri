package com.example.raportmandiri.ui.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raportmandiri.ui.components.AppTextField
import com.example.raportmandiri.ui.components.AuthHeader
import com.example.raportmandiri.ui.components.FormSheet
import com.example.raportmandiri.ui.components.PasswordTextField
import com.example.raportmandiri.ui.components.PrimaryButton

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbar = remember { SnackbarHostState() }

    LaunchedEffect(state.isSuccess) { if (state.isSuccess) onLoginSuccess() }
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            snackbar.showSnackbar(it)
            viewModel.onErrorShown()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        snackbarHost = { SnackbarHost(snackbar) }
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {
            AuthHeader(
                title = "Raport Mandiri",
                subtitle = "Masuk untuk melihat dan mengelola nilai raport."
            )
            FormSheet {
                AppTextField(
                    value = state.email,
                    onValueChange = viewModel::onEmailChange,
                    label = "Email",
                    error = state.emailError,
                    keyboardType = KeyboardType.Email,
                    enabled = !state.isLoading
                )
                Spacer(Modifier.height(8.dp))
                PasswordTextField(
                    value = state.password,
                    onValueChange = viewModel::onPasswordChange,
                    label = "Password",
                    error = state.passwordError,
                    imeAction = ImeAction.Done,
                    onDone = viewModel::login,
                    enabled = !state.isLoading
                )
                Spacer(Modifier.height(16.dp))
                PrimaryButton(text = "Masuk", onClick = viewModel::login, isLoading = state.isLoading)
                Spacer(Modifier.height(8.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Belum punya akun?", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    TextButton(onClick = onNavigateToRegister, enabled = !state.isLoading) { Text("Daftar") }
                }
            }
        }
    }
}
