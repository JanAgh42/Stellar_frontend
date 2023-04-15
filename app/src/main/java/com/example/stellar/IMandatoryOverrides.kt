package com.example.stellar

interface IMandatoryOverrides {
    fun attachListeners()
    fun detachListeners()
    fun loadViews()
    fun setDefaultValues()
}