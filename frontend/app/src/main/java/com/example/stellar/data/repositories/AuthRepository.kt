package com.example.stellar.data.repositories

import com.example.stellar.api.ApiClient
import com.example.stellar.api.models.AuthRequest
import com.example.stellar.api.models.AuthResponse
import java.net.SocketTimeoutException

class AuthRepository {

    suspend fun registerUser(credentials: AuthRequest): AuthResponse? {
        try {
            val responseData = ApiClient.authService.registerUser(credentials)

            if (responseData.isSuccessful) {
                return responseData.body()
            }
        } catch (_: SocketTimeoutException) {}
        return null
    }

    suspend fun authenticateUser(credentials: AuthRequest): AuthResponse? {
        try {
            val responseData = ApiClient.authService.authenticateUser(credentials)

            if (responseData.isSuccessful) {
                return responseData.body()
            }
        } catch (_: SocketTimeoutException) {}
        return null
    }
}