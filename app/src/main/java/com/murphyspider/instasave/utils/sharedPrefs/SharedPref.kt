package com.murphyspider.instasave.utils.sharedPrefs

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    private val preferences: SharedPreferences =
        context.getSharedPreferences("tokenPref", Context.MODE_PRIVATE)

    fun setString(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return preferences.getString(key, null)
    }
}