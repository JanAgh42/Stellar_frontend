package com.example.stellar.data

import androidx.lifecycle.MutableLiveData
import com.example.stellar.api.models.*

object LocalData {

    // cache collections

    var identity: String = "a11628b0-be28-40dd-9e7d-05eaae8b316d"
    var token: String = "GyXPLsXaBFaxPxTGGPbdNg"

    var user = MutableLiveData<User?>()
    var userGroups = MutableLiveData<MutableList<Group>?>()
    var userNotifs = MutableLiveData<MutableList<Notification?>>()

    // non-cache collections

    var boolValues = MutableLiveData<Boolean?>()
    var groupMessages = MutableLiveData<MutableList<Message?>>()
    var foundGroups = MutableLiveData<MutableList<Group>?>()

    fun setAuthData(data: AuthResponse?) {
        this.identity = if (data?.userId != null) data.userId else ""
        this.token = if (data?.token != null) data.token else ""
    }

    fun resetAuthData() {
        this.identity = ""
        this.token = ""
    }

    fun getAuthData(): AuthResponse {
        return AuthResponse(
            this.identity,
            this.token
        )
    }

    fun <T> MutableLiveData<T>.trigger() {
        value = value
    }
}