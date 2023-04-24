package com.example.stellar.data.repositories

import com.example.stellar.api.ApiClient
import com.example.stellar.api.models.User
import com.example.stellar.api.models.UserGroup
import com.example.stellar.data.LocalData
import java.net.SocketTimeoutException

class UserRepository {

    suspend fun getUser(userId: String): User? {
        val responseData = ApiClient.userService.getUser(
            LocalData.identity,
            LocalData.token,
            userId
        )
        return if (responseData.isSuccessful) responseData.body() else null
    }

    suspend fun updateUser(userId: String, user: User): Boolean {
        val responseData = ApiClient.userService.updateUser(
            LocalData.identity,
            LocalData.token,
            userId,
            user
        )
        return responseData.isSuccessful
    }
}