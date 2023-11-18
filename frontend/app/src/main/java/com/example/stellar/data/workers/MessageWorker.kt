package com.example.stellar.data.workers

import android.content.Context
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.stellar.data.DataPipeline
import com.example.stellar.data.LocalData
import com.example.stellar.data.repositories.MessageRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class MessageWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params){

    private val repository = MessageRepository()
    private val storage = FirebaseStorage.getInstance()

    override suspend fun doWork(): Result {
        return when (inputData.getString("decider")) {
            "GET" -> {
                this.getMessage()
            }
            "GET_GROUP" -> {
                this.getGroupMessages()
            }
            "POST" -> {
                this.createMessage()
            }
            "PUT" -> {
                this.changeMessage()
            }
            else -> { Result.success() }
        }
    }

    private suspend fun getMessage(): Result {
        val messageData = repository.getMessage(inputData.getString("messageId")!!)
        DataPipeline.singleMessage = messageData

        return Result.success()
    }

    private suspend fun getGroupMessages(): Result {
        val messageData = repository.getGroupMessages(inputData.getString("groupId")!!)
        LocalData.groupMessages.postValue(messageData?.toMutableList())

        return Result.success()
    }

    private fun createMessage(): Result {
        if (inputData.getString("uri")!! != "null") {
            this.saveToFirebase()
        }
        else {
            this.saveToDatabase()
        }
        return Result.success()
    }

    private suspend fun changeMessage(): Result {
        return Result.success()
    }

    private fun saveToFirebase() {
        val fileType = inputData.getString("datatype")!!
        val reference = this.storage.getReference("post_images/${UUID.randomUUID()}.${fileType}")

        reference.putFile(Uri.parse(inputData.getString("uri")!!)).addOnSuccessListener {
            reference.downloadUrl.addOnSuccessListener {
                DataPipeline.messages[0]!!.imageUrl = it.toString()
                saveToDatabase()
            }
        }
    }

    private fun saveToDatabase() {
        GlobalScope.launch {
            val messageData = repository.createMessage(DataPipeline.messages[0]!!)
            DataPipeline.messages.removeAt(0)

            LocalData.boolValues.postValue(messageData)
        }
    }
}