package com.example.stellar

import android.content.Context
import android.view.View

interface IGeneralFunctionality {
    fun changeActivity(
        current: Context,
        new: Class<*>,
        data: Map<String, java.io.Serializable>? = null)
    fun menuBarListeners(
        menuBar: View,
        context: Context,
        activity: ActivityTypes)
}