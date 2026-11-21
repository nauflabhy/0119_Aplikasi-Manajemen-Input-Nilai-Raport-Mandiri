package com.example.raportmandiri.ui.theme.auth

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
    ) { _ ->
        Column(
            Modifier
                .fillMaxSize()
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
