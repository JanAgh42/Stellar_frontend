package com.example.stellar.api.services

import com.example.stellar.api.models.Message
import retrofit2.Response
import retrofit2.http.*

interface MessageService : BaseService {
    @GET("messages/{message_id}")
    suspend fun getMessage(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("message_id") messageId: String
    ): Response<Message>

    @GET("messages/{group_id}/get-all")
    suspend fun getGroupMessages(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("group_id") groupId: String
    ): Response<List<Message>>

    @POST("messages")
    suspend fun createMessage(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Body message: Message
    ): Response<Unit>

    @PUT("messages/{message_id}/change")
    suspend fun editMessage(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("message_id") messageId: String,
        @Body message: Message
    ): Response<Unit>

    @DELETE("messages/{message_id}/delete")
    suspend fun deleteMessage(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("message_id") messageId: String
    ): Response<Unit>

    @DELETE("messages/{group_id}/delete-all")
    suspend fun deleteGroupMessages(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("group_id") groupId: String
    ): Response<Unit>
}