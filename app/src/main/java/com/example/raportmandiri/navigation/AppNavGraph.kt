package com.example.raportmandiri.navigation

import kotlinx.coroutines.delay

enum class AccountType(val label: String, val idLabel: String) {
    SISWA("Siswa", "NIS"),
    GURU("Guru", "NIP")
}

data class AuthUser(val id: Int, val name: String, val role: String)

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<AuthUser>
    suspend fun register(
        type: AccountType,
        identityNumber: String,
        email: String,
        password: String
    ): Result<Unit>
}

class FakeAuthRepository : AuthRepository {
    override suspend fun login(email: String, password: String): Result<AuthUser> {
        delay(800)
        return if (email == "admin@sekolah.id" && password == "password1")
            Result.success(AuthUser(1, "Admin Sekolah", "admin"))
        else Result.failure(Exception("Email atau password salah"))
    }

    override suspend fun register(
        type: AccountType,
        identityNumber: String,
        email: String,
        password: String
    ): Result<Unit> {
        delay(800)
        return if (identityNumber == "00000")
            Result.failure(Exception("${type.idLabel} tidak ditemukan di data sekolah"))
        else Result.success(Unit)
    }
}
