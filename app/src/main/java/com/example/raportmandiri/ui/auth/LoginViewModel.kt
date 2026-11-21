package com.example.raportmandiri.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.raportmandiri.data.repository.AuthRepository
import com.example.raportmandiri.data.repository.FakeAuthRepository
import com.example.raportmandiri.util.Validators
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class LoginViewModel(
    // Minggu 3: ganti dengan repository Retrofit (lewat factory atau DI)
    private val repository: AuthRepository = FakeAuthRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    fun onEmailChange(value: String) = _state.update { it.copy(email = value, emailError = null) }
    fun onPasswordChange(value: String) = _state.update { it.copy(password = value, passwordError = null) }
    fun onErrorShown() = _state.update { it.copy(errorMessage = null) }

    fun login() {
        val s = _state.value
        if (s.isLoading) return

        val emailError = Validators.email(s.email.trim())
        val passwordError = Validators.passwordRequired(s.password)
        if (emailError != null || passwordError != null) {
            _state.update { it.copy(emailError = emailError, passwordError = passwordError) }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.login(s.email.trim(), s.password)
                .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
                .onFailure { e ->
                    _state.update {
                        it.copy(isLoading = false, errorMessage = e.message ?: "Gagal masuk. Coba lagi.")
                    }
                }
        }
    }
}