package com.example.testing_project_1_adroid_with_nodejs.other

sealed class Resource<T> {
    class Success<T>(val data : T? = null) : Resource<T>()

    class Error<T>(val data : T? = null) : Resource<T>()
}
