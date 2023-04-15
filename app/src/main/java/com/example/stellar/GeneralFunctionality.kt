package com.example.stellar

import android.content.Context
import android.content.Intent

class GeneralFunctionality : IGeneralFunctionality {

    override fun changeActivity(
        current: Context,
        new: Class<*>,
        data: Map<String, java.io.Serializable>?)
    {
        val intent: Intent = Intent(current, new)

        data?.forEach { (key, value) ->
            intent.putExtra(key, value)
        }

        current.startActivity(intent)
    }
}