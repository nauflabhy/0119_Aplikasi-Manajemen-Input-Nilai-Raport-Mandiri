package com.example.raportmandiri.ui.auth

data class RegisterUiState(
    val accountType: AccountType = AccountType.SISWA,
    val identity: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val identityError: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val confirmError: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

class RegisterViewModel(
    private val repository: AuthRepository = FakeAuthRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state: StateFlow<RegisterUiState> = _state.asStateFlow()

    fun onAccountTypeChange(type: AccountType) =
        _state.update { it.copy(accountType = type, identity = "", identityError = null) }
    fun onIdentityChange(v: String) = _state.update { it.copy(identity = v.filter(Char::isDigit), identityError = null) }
    fun onEmailChange(v: String) = _state.update { it.copy(email = v, emailError = null) }
    fun onPasswordChange(v: String) = _state.update { it.copy(password = v, passwordError = null, confirmError = null) }
    fun onConfirmChange(v: String) = _state.update { it.copy(confirmPassword = v, confirmError = null) }
    fun onErrorShown() = _state.update { it.copy(errorMessage = null) }

    fun register() {
        val s = _state.value
        if (s.isLoading) return

        val identityError = Validators.identity(s.accountType, s.identity)
        val emailError = Validators.email(s.email.trim())
        val passwordError = Validators.newPassword(s.password)
        val confirmError = Validators.confirmPassword(s.password, s.confirmPassword)
        if (listOf(identityError, emailError, passwordError, confirmError).any { it != null }) {
            _state.update {
                it.copy(
                    identityError = identityError,
                    emailError = emailError,
                    passwordError = passwordError,
                    confirmError = confirmError
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            repository.register(s.accountType, s.identity, s.email.trim(), s.password)
                .onSuccess { _state.update { it.copy(isLoading = false, isSuccess = true) } }
                .onFailure { e ->
                    _state.update {
                        it.copy(isLoading = false, errorMessage = e.message ?: "Gagal mendaftar. Coba lagi.")
                    }
                }
        }
    }
}
