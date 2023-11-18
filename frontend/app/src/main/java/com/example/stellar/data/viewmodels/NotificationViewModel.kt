package com.example.stellar.data.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.stellar.data.LocalData
import com.example.stellar.data.repositories.GroupRepository
import com.example.stellar.data.repositories.NotificationRepository
import com.example.stellar.data.workers.GroupWorker
import com.example.stellar.data.workers.NotificationWorker
import kotlinx.coroutines.launch

class NotificationViewModel : ViewModel() {

    private val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    private val repository = NotificationRepository()

    fun getUserNotifs(context: Context, userId: String) {
        viewModelScope.launch {
            val notifData = Data.Builder()
                .putString("userId", userId)
                .putString("decider", "GET")
                .build()

            val groupRequest = OneTimeWorkRequest
                .Builder(NotificationWorker::class.java)
                .setConstraints(constraints)
                .setInputData(notifData)
                .addTag("getNotifications")
                .build()

            WorkManager.getInstance(context).enqueue(groupRequest)
        }
    }

    fun createNotification(context: Context) {
        viewModelScope.launch {
            val notifData = Data.Builder()
                .putString("decider", "POST")
                .build()

            val groupRequest = OneTimeWorkRequest
                .Builder(NotificationWorker::class.java)
                .setConstraints(constraints)
                .setInputData(notifData)
                .addTag("createNewNotification")
                .build()

            WorkManager.getInstance(context).enqueue(groupRequest)
        }
    }

    fun deleteNotification(notificationId: String) {
        viewModelScope.launch {
            val delete = repository.deleteNotification(notificationId)

            LocalData.boolValues.postValue(delete)
        }
    }

    fun deleteUserNotifs(userId: String) {
        viewModelScope.launch {
            val leave = repository.deleteUserNotifs(userId)

            LocalData.boolValues.postValue(leave)
        }
    }
}