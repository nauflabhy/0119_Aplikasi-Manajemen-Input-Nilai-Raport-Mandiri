package com.example.raportmandiri.util

object Validators {

    fun email(value: String): String? = when {
        value.isBlank() -> "Email wajib diisi"
        !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> "Format email tidak valid"
        else -> null
    }

    fun passwordRequired(value: String): String? =
        if (value.isBlank()) "Password wajib diisi" else null

    fun newPassword(value: String): String? = when {
        value.isBlank() -> "Password wajib diisi"
        value.length < 8 -> "Password minimal 8 karakter"
        value.none { it.isLetter() } || value.none { it.isDigit() } ->
            "Password harus mengandung huruf dan angka"
        else -> null
    }