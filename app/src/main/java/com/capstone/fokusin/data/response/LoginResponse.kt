package com.capstone.fokusin.data.response

import com.capstone.fokusin.data.UserData
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class Data(
    @field:SerializedName("user")
    val user: UserData
)
