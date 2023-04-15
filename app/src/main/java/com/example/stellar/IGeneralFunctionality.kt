package com.example.stellar

import android.content.Context

interface IGeneralFunctionality {
    fun changeActivity(current: Context, new: Class<*>, data: Map<String, java.io.Serializable>? = null)
}