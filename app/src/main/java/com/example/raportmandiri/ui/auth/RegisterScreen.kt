package com.example.raportmandiri.ui.auth

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
