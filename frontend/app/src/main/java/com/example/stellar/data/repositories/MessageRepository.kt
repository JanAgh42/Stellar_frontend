package com.example.stellar.data.repositories

import com.example.stellar.api.ApiClient
import com.example.stellar.api.models.Group
import com.example.stellar.api.models.Message
import com.example.stellar.api.models.User
import com.example.stellar.data.LocalData
import java.net.SocketTimeoutException

class MessageRepository {

    suspend fun getMessage(messageId: String): Message? {
        val responseData = ApiClient.messageService.getMessage(
            LocalData.identity,
            LocalData.token,
            messageId
        )
        return if (responseData.isSuccessful) responseData.body() else null
    }

    suspend fun getGroupMessages(groupId: String): List<Message>? {
        val responseData = ApiClient.messageService.getGroupMessages(
            LocalData.identity,
            LocalData.token,
            groupId
        )
        return if (responseData.isSuccessful) responseData.body() else null
    }

    suspend fun createMessage(message: Message): Boolean {
        val responseData = ApiClient.messageService.createMessage(
            LocalData.identity,
            LocalData.token,
            message
        )
        return responseData.isSuccessful
    }

    suspend fun deleteMessage(messageId: String): Boolean {
        try {
            val responseData = ApiClient.messageService.deleteMessage(
                LocalData.identity,
                LocalData.token,
                messageId
            )
            return responseData.isSuccessful
        } catch (_: SocketTimeoutException) {}
        return false
    }
}