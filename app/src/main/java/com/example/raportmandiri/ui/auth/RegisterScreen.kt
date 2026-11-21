package com.example.raportmandiri.ui.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.raportmandiri.data.repository.AccountType
import com.example.raportmandiri.ui.components.AppTextField
import com.example.raportmandiri.ui.components.AuthHeader
import com.example.raportmandiri.ui.components.FormSheet
import com.example.raportmandiri.ui.components.PasswordTextField
import com.example.raportmandiri.ui.components.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegistered: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbar = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            Toast.makeText(context, "Akun berhasil dibuat. Silakan masuk.", Toast.LENGTH_LONG).show()
            onRegistered()
        }
    }
    LaunchedEffect(state.errorMessage) {
        state.errorMessage?.let {
            snackbar.showSnackbar(it)
            viewModel.onErrorShown()
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        snackbarHost = { SnackbarHost(snackbar) }
    ) { _ ->
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .imePadding()
        ) {
            AuthHeader(
                title = "Buat akun",
                subtitle = "Gunakan NIS atau NIP yang sudah didaftarkan sekolah."
            )
            FormSheet {
                Text("Daftar sebagai", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(8.dp))
                SingleChoiceSegmentedButtonRow(Modifier.fillMaxWidth()) {
                    AccountType.entries.forEachIndexed { index, type ->
                        SegmentedButton(
                            selected = state.accountType == type,
                            onClick = { viewModel.onAccountTypeChange(type) },
                            shape = SegmentedButtonDefaults.itemShape(index, AccountType.entries.size),
                            enabled = !state.isLoading
                        ) { Text(type.label) }
                    }
                }
                Spacer(Modifier.height(16.dp))
                AppTextField(
                    value = state.identity,
                    onValueChange = viewModel::onIdentityChange,
                    label = state.accountType.idLabel,
                    error = state.identityError,
                    keyboardType = KeyboardType.Number,
                    enabled = !state.isLoading
                )
                Spacer(Modifier.height(8.dp))
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
                    hint = "Minimal 8 karakter, gabungan huruf dan angka",
                    enabled = !state.isLoading
                )
                Spacer(Modifier.height(8.dp))
                PasswordTextField(
                    value = state.confirmPassword,
                    onValueChange = viewModel::onConfirmChange,
                    label = "Konfirmasi password",
                    error = state.confirmError,
                    imeAction = ImeAction.Done,
                    onDone = viewModel::register,
                    enabled = !state.isLoading
                )
                Spacer(Modifier.height(16.dp))
                PrimaryButton(text = "Daftar", onClick = viewModel::register, isLoading = state.isLoading)
                Spacer(Modifier.height(8.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Sudah punya akun?", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    TextButton(onClick = onNavigateToLogin, enabled = !state.isLoading) { Text("Masuk") }
                }
            }
        }
    }
}
