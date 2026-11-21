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
