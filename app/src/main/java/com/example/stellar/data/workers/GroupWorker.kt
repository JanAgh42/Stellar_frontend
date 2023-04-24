package com.example.stellar.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.stellar.data.DataPipeline
import com.example.stellar.data.LocalData
import com.example.stellar.data.repositories.GroupRepository

class GroupWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params){

    private val repository = GroupRepository()

    override suspend fun doWork(): Result {
        return when (inputData.getString("decider")) {
            "GET" -> {
                this.getGroup()
            }
            "GET_ALL" -> {
                this.getAllGroups()
            }
            "POST" -> {
                this.createGroup()
            }
            "PUT" -> {
                this.updateGroup()
            }
            else -> { Result.success() }
        }
    }

    private suspend fun getGroup(): Result {
        val groupData = repository.getGroup(inputData.getString("groupId")!!)
        DataPipeline.singleGroup = groupData

        return Result.success()
    }

    private suspend fun getAllGroups(): Result {
        val groupData = repository.getAllGroups(inputData.getString("userId")!!)
        LocalData.userGroups.postValue(groupData?.toMutableList())

        return Result.success()
    }

    private suspend fun createGroup(): Result {
        return Result.success()
    }

    private suspend fun updateGroup(): Result {
        val groupData = repository.updateGroup(
            inputData.getString("groupId")!!,
            DataPipeline.groups[0]!!
        )
        DataPipeline.groups.removeAt(0)
        LocalData.boolValues.postValue(groupData)

        return Result.success()
    }
}