package com.example.stellar.interfaces

import android.content.Context
import android.widget.LinearLayout

interface ICollectionsFunctionality {
    fun generateDynamicTags(
        parent: LinearLayout,
        context: Context,
        callback: (text: String) -> Unit)
    fun generateDynamicIcons(
        parent: LinearLayout,
        context: Context,
        callback: (tag: String) -> Unit)

    fun generateDynamicColors(
        parent: LinearLayout,
        context: Context,
        callback: (text: String) -> Unit,
        colors: List<Int>,
        isGroup: Boolean)
    fun makeColorButtonsClickable(
        parent: LinearLayout,
        context: Context,
        callback: (text: String) -> Unit)
}