package com.assignment.sharedpreferencestask

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesConfig(
    context: Context,
    private val sharedPreferencesHelper: SharedPreferencesHelper = SharedPreferencesHelper()
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        sharedPreferencesHelper.preferencesName,
        Context.MODE_PRIVATE
    )

    fun writeName(name: String) {
        val editor = sharedPreferences.edit()
        editor.putString(sharedPreferencesHelper.nameKey, name)
        editor.apply()
    }

    fun readName(): String? {
        return sharedPreferences.getString(sharedPreferencesHelper.nameKey, "")
    }

    fun writeAddress(address: String) {
        val editor = sharedPreferences.edit()
        editor.putString(sharedPreferencesHelper.addressKey, address)
        editor.apply()
    }

    fun readAddress(): String? {
        return sharedPreferences.getString(sharedPreferencesHelper.addressKey, "")
    }

    fun writeAge(age: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(sharedPreferencesHelper.ageKey, age)
        editor.apply()
    }

    fun readAge(): Int {
        return sharedPreferences.getInt(sharedPreferencesHelper.ageKey, 0)
    }

    fun writeCreationTime(timeInMillis: Long) {
        val editor = sharedPreferences.edit()
        editor.putLong(sharedPreferencesHelper.creationTime, timeInMillis)
        editor.apply()
    }

    fun readCreationTime(): Long {
        return sharedPreferences.getLong(sharedPreferencesHelper.creationTime, 0)
    }

    fun writeLoginInStatus(status: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(sharedPreferencesHelper.loginStatus, status)
        editor.apply()
    }

    fun readLoginInStatus(): Boolean {
        return sharedPreferences.getBoolean(sharedPreferencesHelper.loginStatus, false)
    }
}