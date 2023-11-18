package com.example.stellar.data.repositories

import com.example.stellar.api.ApiClient
import com.example.stellar.api.models.Group
import com.example.stellar.data.LocalData
import java.net.SocketTimeoutException

class GroupRepository {

    suspend fun getGroup(groupId: String): Group? {
        val responseData = ApiClient.groupService.getGroup(
            LocalData.identity,
            LocalData.token,
            groupId
        )
        return if (responseData.isSuccessful) responseData.body() else null
    }

    suspend fun getAllGroups(userId: String): List<Group>? {
        val responseData = ApiClient.groupService.getAllGroups(
            LocalData.identity,
            LocalData.token,
            userId
        )
        return if (responseData.isSuccessful) responseData.body() else null
    }

    suspend fun searchGroups(category: String?, name: String?): List<Group>? {
        try {
            val responseData = ApiClient.groupService.searchGroups(
                LocalData.identity,
                LocalData.token,
                category,
                name
            )
            return if (responseData.isSuccessful) responseData.body() else null
        } catch (_: SocketTimeoutException) {}
        return null
    }

    suspend fun updateGroup(groupId: String, group: Group): Boolean {
        val responseData = ApiClient.groupService.editGroup(
            LocalData.identity,
            LocalData.token,
            groupId,
            group
        )
        return responseData.isSuccessful
    }
}