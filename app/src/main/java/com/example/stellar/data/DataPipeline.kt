package com.example.stellar.data

import com.example.stellar.api.models.*

object DataPipeline {
    var groups = mutableListOf<Group?>()
    var messages = mutableListOf<Message?>()
    var notifications = mutableListOf<Notification?>()
    var userGroups = mutableListOf<UserGroup?>()

    var singleGroup: Group? = null
    var singleMessage: Message? = null
}