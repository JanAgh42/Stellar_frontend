package com.example.stellar.api.services

import com.example.stellar.api.models.Notification
import retrofit2.Response
import retrofit2.http.*

interface NotificationService : BaseService {
    @GET("notifications/{user_id}")
    suspend fun getUserNotifs(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("user_id") userId: String
    ): Response<List<Notification>>

    @POST("notifications")
    suspend fun createNotif(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Body notification: Notification
    ): Response<Unit>

    @POST("notifications/{group_id}/group")
    suspend fun createGroupNotif(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("group_id") groupId: String,
        @Body notification: Notification
    ): Response<Unit>

    @DELETE("notifications/{notif_id}/single")
    suspend fun deleteNotif(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("notif_id") notifId: String
    ): Response<Unit>

    @DELETE("notifications/{user_id}/all")
    suspend fun deleteUserNotifs(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("user_id") userId: String
    ): Response<Unit>
}