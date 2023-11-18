package com.example.stellar.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.stellar.data.DataPipeline
import com.example.stellar.data.LocalData
import com.example.stellar.data.repositories.NotificationRepository
import com.example.stellar.data.repositories.UserRepository

class NotificationWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    private val repository = NotificationRepository()

    override suspend fun doWork(): Result {
        return when (inputData.getString("decider")) {
            "GET" -> {
                this.getUserNotifs()
            }
            "POST" -> {
                this.createNotification()
            }
            "POST_GROUP" -> {
                this.createGroupNotif()
            }
            else -> { Result.success() }
        }
    }

    private suspend fun getUserNotifs(): Result {
        val notifData = repository.getUserNotifs(inputData.getString("userId")!!)
        LocalData.userNotifs.postValue(notifData?.toMutableList())

        return Result.success()
    }

    private suspend fun createNotification(): Result {
        val notifData = repository.createNotification(DataPipeline.notifications[0]!!)

        DataPipeline.notifications.removeAt(0)
        LocalData.boolValues.postValue(notifData)

        return Result.success()
    }

    private suspend fun createGroupNotif(): Result {

        return Result.success()
    }
}