package com.example.testing_project_1_adroid_with_nodejs.other

import android.os.Looper
import androidx.lifecycle.MutableLiveData

class Events<T> : MutableLiveData<Resource<T>>() {
    fun sendSuccess(data : T){
        value = Resource.Success(data)
    }

    fun sendError(data: T){
        value = Resource.Error(data)
    }

    override fun postValue(value: Resource<T>?) {
        if(Looper.myLooper() == Looper.getMainLooper()){
            super.postValue(value)
        } else {
            super.setValue(value)
        }
    }

    override fun setValue(value: Resource<T>?) {
        if(Looper.myLooper() == Looper.getMainLooper()){
            super.setValue(value)
        } else {
            super.postValue(value)
        }
    }
}