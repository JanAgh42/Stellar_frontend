package com.example.stellar.data.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.example.stellar.data.LocalData
import com.example.stellar.data.repositories.GroupRepository
import com.example.stellar.data.workers.GroupWorker
import com.example.stellar.data.workers.MessageWorker
import com.example.stellar.data.workers.NotificationWorker
import kotlinx.coroutines.launch

class GroupViewModel : ViewModel() {

    private val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    private val repository = GroupRepository()

    fun getGroup(context: Context, groupId: String) {
        viewModelScope.launch {
            val groupData = Data.Builder()
                .putString("decider", "GET")
                .putString("groupId", groupId)
                .build()

            val request = OneTimeWorkRequest
                .Builder(GroupWorker::class.java)
                .setConstraints(constraints)
                .setInputData(groupData)
                .addTag("loadGroupData")
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }
    }

    fun getAllGroups(context: Context, userId: String) {
        viewModelScope.launch {
            val groupData = Data.Builder()
                .putString("userId", userId)
                .putString("decider", "GET_ALL")
                .build()

            val groupRequest = OneTimeWorkRequest
                .Builder(GroupWorker::class.java)
                .setConstraints(constraints)
                .setInputData(groupData)
                .addTag("getAllGroups")
                .build()

            WorkManager.getInstance(context).enqueue(groupRequest)
        }
    }

    fun searchGroups(category: String?, name: String?) {
        viewModelScope.launch {
            val groups = repository.searchGroups(category, name)

            LocalData.foundGroups.postValue(groups?.toMutableList())
        }
    }

    fun updateGroup(context: Context, groupId: String) {
        viewModelScope.launch {
            val groupData = Data.Builder()
                .putString("decider", "PUT")
                .putString("groupId", groupId)
                .build()

            val groupRequest = OneTimeWorkRequest
                .Builder(GroupWorker::class.java)
                .setConstraints(constraints)
                .setInputData(groupData)
                .addTag("updateGroup")
                .build()

            WorkManager.getInstance(context).enqueue(groupRequest)
        }
    }
}