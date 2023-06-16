package com.capstone.fokusin.data.sharepr

import android.content.Context
import android.content.SharedPreferences

class PrefHelper(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveUser(username: String, email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("email", email)
        editor.apply()
    }

    fun getUser(): Pair<String?, String?> {
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        return Pair(username, email)
    }

    fun clearUser() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}