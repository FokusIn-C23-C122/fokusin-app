package com.capstone.fokusin.data.response

import com.capstone.fokusin.data.service.AnalisisResponse
import com.google.gson.annotations.SerializedName

data class FokPredicResponse(

    @field:SerializedName("data")
    val data: AnalisisResponse,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String

)
