package com.example.raportmandiri.ui.dashboard

/** Placeholder. Diganti dengan Dashboard per role pada fitur F-02 (Minggu 6-7). */
@Composable
fun DashboardScreen(onLogout: () -> Unit) {
    Scaffold { padding ->
        Column(
            Modifier.fillMaxSize().padding(padding).padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Dashboard", style = MaterialTheme.typography.headlineMedium)
            Text("Login berhasil. Halaman ini akan diganti sesuai peran pengguna.")
            Button(onClick = onLogout) { Text("Keluar") }
        }
    }
}