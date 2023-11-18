package com.example.stellar.data.repositories

import com.example.stellar.api.ApiClient
import com.example.stellar.api.models.Group
import com.example.stellar.api.models.Message
import com.example.stellar.api.models.Notification
import com.example.stellar.data.LocalData
import java.net.SocketTimeoutException

class NotificationRepository {

    suspend fun getUserNotifs(userId: String): List<Notification>? {
        val responseData = ApiClient.notificationService.getUserNotifs(
            LocalData.identity,
            LocalData.token,
            userId
        )
        return if (responseData.isSuccessful) responseData.body() else null
    }

    suspend fun createNotification(notification: Notification): Boolean {
        val responseData = ApiClient.notificationService.createNotif(
            LocalData.identity,
            LocalData.token,
            notification
        )
        return responseData.isSuccessful
    }

    suspend fun deleteNotification(notificationId: String): Boolean {
        try {
            val responseData = ApiClient.notificationService.deleteNotif(
                LocalData.identity,
                LocalData.token,
                notificationId
            )
            return responseData.isSuccessful
        } catch (_: SocketTimeoutException) {}
        return false
    }

    suspend fun deleteUserNotifs(userId: String): Boolean {
        try {
            val responseData = ApiClient.notificationService.deleteUserNotifs(
                LocalData.identity,
                LocalData.token,
                userId
            )
            return responseData.isSuccessful
        } catch (_: SocketTimeoutException) {}
        return false
    }
}