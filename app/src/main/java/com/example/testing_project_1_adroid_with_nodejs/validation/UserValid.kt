package com.example.testing_project_1_adroid_with_nodejs.validation

import java.util.regex.Pattern

object UserValid {
    fun isValidEmail(email: String): Boolean {
        val pattern = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$"
        )
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    fun isValidUsername(username : String) : Boolean{
        return username.isEmpty().not()
    }

    fun isValidPassword(password : String) : Boolean{
        return password.length >= 6
    }
}