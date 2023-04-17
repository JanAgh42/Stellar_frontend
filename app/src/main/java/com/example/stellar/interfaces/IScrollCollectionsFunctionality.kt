package com.example.stellar.interfaces

import android.content.Context
import android.view.View
import android.widget.LinearLayout

interface IScrollCollectionsFunctionality {
    fun generateDynamicTags(
        parent: LinearLayout,
        context: Context,
        callback: (text: String) -> Unit)
    fun generateDynamicIcons(
        parent: LinearLayout,
        context: Context,
        callback: (tag: String) -> Unit)
}