package com.example.aigame.data.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesHelper(
    context: Context
) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)

    fun <T> storeData(sharedPreferenceName: String, data: T){
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val dataJson = gson.toJson(data)
        editor.putString(sharedPreferenceName, dataJson)
        editor.apply()
    }
    fun <T> retrieveData(sharedPreferenceName: String, clazz: Class<T>): T?{
        val chapterResponseJson = sharedPreferences.getString(sharedPreferenceName, null)
        return if (chapterResponseJson != null) {
            val gson = Gson()
            return gson.fromJson(chapterResponseJson, clazz)
        } else null
    }
}