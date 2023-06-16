package com.capstone.fokusin.data.response


import com.google.gson.annotations.SerializedName

data class Tokens(
    @SerializedName("access")
    val access: String,
    @SerializedName("refresh")
    val refresh: String
)