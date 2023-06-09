package com.capstone.fokusin.data

data class UserData(
    val userId: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val token: String,
    val isLogin: Boolean
)