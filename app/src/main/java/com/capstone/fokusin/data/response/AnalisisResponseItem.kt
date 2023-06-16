package com.capstone.fokusin.data.response

data class AnalisisResponseItem(
    val date: String,
    val description: String,
    val focus_length: Int,
    val focus_percentage: Int,
    val session_length: Int,
    val time: String
)