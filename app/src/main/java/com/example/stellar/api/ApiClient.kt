package com.example.stellar.api

import com.example.stellar.api.services.*
import com.example.stellar.enums.ServiceTypes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {

    private lateinit var retrofit: Retrofit
    private lateinit var interceptor: HttpLoggingInterceptor
    private lateinit var client: OkHttpClient

    private fun getClient(): Retrofit {
        this.interceptor = HttpLoggingInterceptor()
        this.interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        this.client = OkHttpClient
            .Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(this.interceptor)
            .build()

        this.retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.0.2.2:8000/api/")
            .client(this.client)
            .build()

        return this.retrofit
    }

    val authService = this.getClient().create(AuthService::class.java) as AuthService
    val userService = this.getClient().create(UserService::class.java) as UserService
    val groupService = this.getClient().create(GroupService::class.java) as GroupService
    val messageService = this.getClient().create(MessageService::class.java) as MessageService
    val notificationService = this.getClient().create(NotificationService::class.java) as NotificationService
}