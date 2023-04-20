package com.example.stellar.interfaces

import com.example.stellar.api.models.BaseModel
import com.example.stellar.enums.ApiCallTypes

interface IApiOverrides {
    fun callApi(type: ApiCallTypes)
    fun constructObject()
}