package com.capstone.fokusin.data.response


import com.google.gson.annotations.SerializedName

data class RegisterResp(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String
)