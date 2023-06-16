package com.capstone.fokusin.data.response


import com.google.gson.annotations.SerializedName

data class LoginRess(
    @SerializedName("tokens")
    val tokens: Tokens,
    @SerializedName("username")
    val username: String
)