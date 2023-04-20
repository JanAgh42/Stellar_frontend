package com.example.stellar.api.services

import com.example.stellar.api.models.Group
import retrofit2.Response
import retrofit2.http.*

interface GroupService : BaseService {
    @GET("groups/{group_id}")
    suspend fun getGroup(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("group_id") groupId: String
    ): Response<Group>

    @GET("groups/{user_id}/owner")
    suspend fun getOwnGroups(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("user_id") userId: String
    ): Response<List<Group>>

    @GET("groups/{user_id}/all")
    suspend fun getAllGroups(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("user_id") userId: String
    ): Response<List<Group>>

    @POST("groups")
    suspend fun createGroup(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Body group: Group
    ): Response<Unit>

    @PUT("groups/{group_id}")
    suspend fun editGroup(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("group_id") groupId: String,
        @Body group: Group
    ): Response<Unit>

    @DELETE("groups/{group_id}")
    suspend fun deleteGroup(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("group_id") groupId: String
    ): Response<Unit>
}