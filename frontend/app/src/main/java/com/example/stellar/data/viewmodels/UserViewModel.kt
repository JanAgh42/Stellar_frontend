package com.example.stellar.data.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.stellar.api.models.AuthRequest
import com.example.stellar.data.LocalData
import com.example.stellar.data.repositories.GroupRepository
import com.example.stellar.data.repositories.UserRepository
import com.example.stellar.data.workers.UserWorker
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
    private val repository = UserRepository()

    fun getUser(context: Context, userId: String) {
        viewModelScope.launch {
            val userData = Data.Builder()
                .putString("decider", "GET")
                .putString("userId", userId)
                .build()

            val request = OneTimeWorkRequest
                .Builder(UserWorker::class.java)
                .setConstraints(constraints)
                .setInputData(userData)
                .addTag("loadUserData")
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }
    }

    fun updateUser(context: Context, userId: String, uri: Uri?, datatype: String?) {
        viewModelScope.launch {
            val userData = Data.Builder()
                .putString("decider", "PUT")
                .putString("userId", userId)
                .putString("uri", uri.toString())
                .putString("datatype", datatype)
                .build()

            val request = OneTimeWorkRequest
                .Builder(UserWorker::class.java)
                .setConstraints(constraints)
                .setInputData(userData)
                .addTag("updateUserData")
                .build()

            WorkManager.getInstance(context).enqueue(request)
        }
    }
}