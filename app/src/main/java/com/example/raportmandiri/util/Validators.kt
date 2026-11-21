package com.example.raportmandiri.util

import android.util.Patterns
import com.example.raportmandiri.data.repository.AccountType

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

    fun confirmPassword(password: String, confirm: String): String? = when {
        confirm.isBlank() -> "Konfirmasi password wajib diisi"
        confirm != password -> "Konfirmasi password tidak sama"
        else -> null
    }

    fun identity(type: AccountType, value: String): String? {
        val max = if (type == AccountType.SISWA) 20 else 25
        return when {
            value.isBlank() -> "${type.idLabel} wajib diisi"
            !value.all { it.isDigit() } -> "${type.idLabel} hanya boleh berisi angka"
            value.length < 5 || value.length > max -> "${type.idLabel} harus 5 sampai $max digit"
            else -> null
        }
    }
}