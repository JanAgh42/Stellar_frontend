package com.example.stellar.data.workers

import android.content.Context
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import com.example.stellar.data.DataPipeline
import com.example.stellar.data.LocalData
import com.example.stellar.data.repositories.UserRepository
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class UserWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {

    private val repository = UserRepository()
    private val storage = FirebaseStorage.getInstance()

    override suspend fun doWork(): Result {
        return when (inputData.getString("decider")) {
            "GET" -> {
                this.getUser()
            }
            "POST" -> {
                this.addUserToGroup()
            }
            "PUT" -> {
                this.updateUser()
            }
            else -> { Result.success() }
        }
    }

    private suspend fun getUser(): Result {
        val userData = repository.getUser(inputData.getString("userId")!!)
        LocalData.user.postValue(userData)

        return Result.success()
    }

    private suspend fun addUserToGroup(): Result {
        return Result.success()
    }

    private fun updateUser(): Result {
        if (inputData.getString("uri")!! != "null") {
            this.saveToFirebase()
        }
        else {
            this.saveToDatabase()
        }
        return Result.success()
    }

    private fun saveToFirebase() {
        val fileType = inputData.getString("datatype")!!
        val reference = this.storage.getReference("profile_images/${UUID.randomUUID()}.${fileType}")

        reference.putFile(Uri.parse(inputData.getString("uri")!!)).addOnSuccessListener {
            reference.downloadUrl.addOnSuccessListener {
                DataPipeline.messages[0]!!.imageUrl = it.toString()
                saveToDatabase()
            }
        }
    }
    private fun saveToDatabase() {
        GlobalScope.launch {
            val userData = repository.updateUser(
                inputData.getString("userId")!!,
                LocalData.user.value!!
            )

            LocalData.boolValues.postValue(userData)
        }
    }

}