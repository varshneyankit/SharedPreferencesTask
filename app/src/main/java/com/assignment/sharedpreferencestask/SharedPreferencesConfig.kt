package com.assignment.sharedpreferencestask

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesConfig(
    context: Context
) {
    private val PREFERENCE_NAME = "com.assignment.sharedpreferencestask.Data_preferences"
    private val NAME_KEY = "name"
    private val ADDRESS_KEY = "address"
    private val AGEKEY = "age"
    private val CREATION_TIME_KEY = "creation_time"
    private val LOGIN_STATUS_KEY = "login_status"

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCE_NAME,
        Context.MODE_PRIVATE
    )

    fun writeName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString(NAME_KEY, name)
        editor.apply()
    }

    fun readName(): String? {
        return sharedPreferences.getString(NAME_KEY, "")
    }

    fun writeAddress(address: String) {
        val editor = sharedPreferences.edit()
        editor.putString(ADDRESS_KEY, address)
        editor.apply()
    }

    fun readAddress(): String? {
        return sharedPreferences.getString(ADDRESS_KEY, "")
    }

    fun writeAge(age: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(AGEKEY, age)
        editor.apply()
    }

    fun readAge(): Int {
        return sharedPreferences.getInt(AGEKEY, 0)
    }

    fun writeCreationTime(timeInMillis: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(CREATION_TIME_KEY, timeInMillis)
        editor.apply()
    }

    fun readCreationTime(): Long {
        return sharedPreferences.getLong(CREATION_TIME_KEY, 0)
    }

    fun writeLoginInStatus(status: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(LOGIN_STATUS_KEY, status)
        editor.apply()
    }

    fun readLoginInStatus(): Boolean {
        return sharedPreferences.getBoolean(LOGIN_STATUS_KEY, false)
    }
}