package com.example.stellar.interfaces

import android.app.Activity
import android.content.Context
import android.view.MenuItem
import android.view.View
import com.example.stellar.enums.ActivityTypes

interface IGeneralFunctionality {
    fun changeActivity(
        current: Context,
        new: Class<*>,
        data: Map<String, java.io.Serializable>? = null
    )
    fun menuBarListeners(
        menuBar: View,
        context: Context,
        activity: ActivityTypes
    )
    fun checkForPermissions(
        type: String,
        code: Int,
        appContext: Context,
        activity: Activity,
        callback: () -> Unit
    )
    fun openPopupMenu(
        context: Context,
        view: View,
        menuId: Int,
        callback: (item: MenuItem) -> Unit
    )
}