package com.example.stellar.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stellar.api.models.AuthRequest
import com.example.stellar.api.models.AuthResponse
import com.example.stellar.data.repositories.AuthRepository
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    var authData = MutableLiveData<AuthResponse?>()

    fun registerUser(credentials: AuthRequest) {
        viewModelScope.launch {
            val auth = repository.registerUser(credentials)

            authData.postValue(auth)
        }
    }

    fun authenticateUser(credentials: AuthRequest) {
        viewModelScope.launch {
            val auth = repository.authenticateUser(credentials)

            authData.postValue(auth)
        }
    }
}