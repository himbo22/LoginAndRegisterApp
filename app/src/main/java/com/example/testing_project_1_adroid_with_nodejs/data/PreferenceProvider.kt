package com.example.testing_project_1_adroid_with_nodejs.data

import android.content.Context
import android.content.SharedPreferences

class PreferenceProvider(context: Context){

    private val sharedPreferences : SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveLoggedInStatus(isLoggedIn : Boolean){
        with(sharedPreferences.edit()){
            putBoolean("isLoggedIn", isLoggedIn)
            apply()
        }
    }

    fun isLoggedIn() : Boolean{
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    fun saveUsername(username : String){
        with(sharedPreferences.edit()){
            putString("username", username)
            apply()
        }
    }


    fun getUsername() : String?{
        return sharedPreferences.getString("username", "cac")
    }

    fun remove(){
        sharedPreferences.edit().remove("isLoggedIn").apply()
    }

}