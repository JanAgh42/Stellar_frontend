package com.example.stellar.enums

import com.example.stellar.api.services.*

enum class ServiceTypes (val service: Class<*>) {
    AUTH(AuthService::class.java),
    USER(UserService::class.java),
    GROUP(GroupService::class.java),
    MESSAGE(MessageService::class.java),
    NOTIFICATION(NotificationService::class.java)
}