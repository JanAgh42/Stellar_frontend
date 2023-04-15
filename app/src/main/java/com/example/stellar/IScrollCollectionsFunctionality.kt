package com.example.stellar

import android.content.Context
import android.widget.LinearLayout

interface IScrollCollectionsFunctionality {
    fun generateDynamicTags(parent: LinearLayout, context: Context)
    fun generateDynamicIcons(parent: LinearLayout, context: Context)
}