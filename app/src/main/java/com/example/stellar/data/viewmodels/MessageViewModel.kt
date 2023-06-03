package com.example.stellar.data.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.stellar.data.LocalData
import com.example.stellar.data.repositories.GroupRepository
import com.example.stellar.data.repositories.MessageRepository
import com.example.stellar.data.workers.GroupWorker
import com.example.stellar.data.workers.MessageWorker
import com.example.stellar.data.workers.UserWorker
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {

    private val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    private val repository = MessageRepository()

    fun getMessage(context: Context, messageId: String) {
        viewModelScope.launch {
            val messageData = Data.Builder()
                .putString("decider", "GET")
                .putString("messageId", messageId)
                .build()

            val request = OneTimeWorkRequest
                .Builder(MessageWorker::class.java)
                .setConstraints(constraints)
                .setInputData(messageData)
                .addTag("loadMessageData")
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }
    }

    fun getGroupMessages(context: Context, groupId: String) {
        viewModelScope.launch {
            val messageData = Data.Builder()
                .putString("decider", "GET_GROUP")
                .putString("groupId", groupId)
                .build()

            val request = OneTimeWorkRequest
                .Builder(MessageWorker::class.java)
                .setConstraints(constraints)
                .setInputData(messageData)
                .addTag("getGroupMessages")
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }
    }

    fun createMessage(context: Context, uri: Uri?, datatype: String?) {
        viewModelScope.launch {
            val messageData = Data.Builder()
                .putString("decider", "POST")
                .putString("uri", uri.toString())
                .putString("datatype", datatype)
                .build()

            val messageRequest = OneTimeWorkRequest
                .Builder(MessageWorker::class.java)
                .setConstraints(constraints)
                .setInputData(messageData)
                .addTag("postNewMessage")
                .build()

            WorkManager.getInstance(context).enqueue(messageRequest)
        }
    }

    fun deleteMessage(messageId: String) {
        viewModelScope.launch {
            val leave = repository.deleteMessage(messageId)

            LocalData.boolValues.postValue(leave)
        }
    }
}