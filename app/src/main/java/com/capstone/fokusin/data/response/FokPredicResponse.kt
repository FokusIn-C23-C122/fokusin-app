package com.capstone.fokusin.data.response

import com.google.gson.annotations.SerializedName

data class FokPredicResponse(

    @field:SerializedName("data")
    val data: FokPredict,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String

)

data class FokPredict(

    @field:SerializedName("prediction")
    val predictions: ArrayList<PredicItem>
)

data class PredicItem(

    @field:SerializedName("score")
    val score: String,

    @field:SerializedName("label")
    val label: String
)