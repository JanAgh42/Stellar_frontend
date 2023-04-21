package com.example.stellar.api

import com.example.stellar.api.services.*
import com.example.stellar.enums.ServiceTypes
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private lateinit var retrofit: Retrofit
    private lateinit var interceptor: HttpLoggingInterceptor
    private lateinit var client: OkHttpClient

    private fun getClient(): Retrofit {
        this.interceptor = HttpLoggingInterceptor()
        this.interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        this.client = OkHttpClient
            .Builder()
            .addInterceptor(this.interceptor)
            .build()

        this.retrofit = Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://127.0.0.1:8000/api/")
            .client(this.client)
            .build()

        return this.retrofit
    }

    fun getService(service: ServiceTypes) : BaseService {
        return when (service) {
            ServiceTypes.AUTH -> this.getClient().create(AuthService::class.java) as AuthService
            ServiceTypes.USER -> this.getClient().create(UserService::class.java) as UserService
            ServiceTypes.GROUP -> this.getClient().create(GroupService::class.java) as GroupService
            ServiceTypes.MESSAGE -> this.getClient().create(MessageService::class.java) as MessageService
            ServiceTypes.NOTIFICATION -> this.getClient().create(NotificationService::class.java) as NotificationService
        }
    }
}