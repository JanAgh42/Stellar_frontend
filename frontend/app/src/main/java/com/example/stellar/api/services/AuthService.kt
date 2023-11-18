package com.example.stellar.api.services

import com.example.stellar.api.models.AuthRequest
import com.example.stellar.api.models.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService : BaseService {
    @POST("users")
    suspend fun registerUser(@Body auth: AuthRequest): Response<AuthResponse>

    @POST("users/auth/login")
    suspend fun authenticateUser(@Body auth: AuthRequest): Response<AuthResponse>
}