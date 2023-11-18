package com.example.stellar.api.services

import com.example.stellar.api.models.User
import com.example.stellar.api.models.UserGroup
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService : BaseService {
    @GET("users/{user_id}")
    suspend fun getUser(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("user_id") userId: String
    ): Response<User>

    @GET("usersgroups/{user_id}/member/{group_id}")
    suspend fun isMemberOfGroup(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("user_id") userId: String,
        @Path("group_id") groupId: String
    ): Response<Boolean>

    @POST("usersgroups")
    suspend fun addUserToGroup(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Body userGroup: UserGroup
    ): Response<Unit>

    @PUT("users/{user_id}")
    suspend fun updateUser(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("user_id") userId: String,
        @Body user: User
    ): Response<Unit>

    @DELETE("usersgroups/{user_id}/leave/{group_id}")
    suspend fun leaveGroup(
        @Header("user") identity: String,
        @Header("token") token: String,
        @Path("user_id") userId: String,
        @Path("group_id") groupId: String
    ): Response<Unit>
}