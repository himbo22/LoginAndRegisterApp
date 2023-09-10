package com.example.testing_project_1_adroid_with_nodejs.repository

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class PrefRepository(private val context: Context) {
    private val pref : SharedPreferences = context.getSharedPreferences("myAppPrefs", Context.MODE_PRIVATE)
    private val editor = pref.edit()
    private val gson = Gson()

}