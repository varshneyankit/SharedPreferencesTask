package com.assignment.sharedpreferencestask

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesConfig(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "com.assignment.sharedpreferencestask.Data_preferences",
        Context.MODE_PRIVATE
    )

    fun writeName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString("name", name)
        editor.apply()
    }

    fun readName(): String? {
        return sharedPreferences.getString("name", "No name")
    }

    fun writeAddress(address: String) {
        val editor = sharedPreferences.edit()
        editor.putString("address", address)
        editor.apply()
    }

    fun readAddress(): String? {
        return sharedPreferences.getString("address", "No address")
    }

    fun writeAge(age: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt("age", age)
        editor.apply()
    }

    fun readAge(): Int {
        return sharedPreferences.getInt("age", 0)
    }

    fun writeCreationTime(timeInMillis: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong("creation_time", timeInMillis)
        editor.apply()
    }

    fun readCreationTime(): Long {
        return sharedPreferences.getLong("creation_time", 0)
    }
}