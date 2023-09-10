package com.example.testing_project_1_adroid_with_nodejs.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.testing_project_1_adroid_with_nodejs.model.User
import com.example.testing_project_1_adroid_with_nodejs.other.Event
import com.example.testing_project_1_adroid_with_nodejs.other.Resource
import com.example.testing_project_1_adroid_with_nodejs.repository.Repository
import com.example.testing_project_1_adroid_with_nodejs.validation.UserValid.isValidEmail
import com.example.testing_project_1_adroid_with_nodejs.validation.UserValid.isValidPassword
import com.example.testing_project_1_adroid_with_nodejs.validation.UserValid.isValidUsername
import kotlinx.coroutines.launch


class MainViewModel(private val repository: Repository) : ViewModel() {


    private val _loginResponse : MutableLiveData<Resource<retrofit2.Response<User>>> = MutableLiveData()
    private val _registerResponse : MutableLiveData<Resource<retrofit2.Response<User>>> = MutableLiveData()
    private val _isLoggedIn = MutableLiveData<Event<Resource<Boolean>>>()
    private val _nullUsername = MutableLiveData<Event<Resource<Boolean>>>()

    val loginResponse = _loginResponse
    val registerResponse = _registerResponse
    val isLoggIn : LiveData<Event<Resource<Boolean>>> = _isLoggedIn
    val nullUsername : LiveData<Event<Resource<Boolean>>> = _nullUsername



    fun register(email : String,username : String ,password : String ){
        viewModelScope.launch {
            val response = repository.register(email, username, password)

            if(isValidEmail(email) && isValidPassword(password) && isValidUsername(username) && response.isSuccessful){
                _registerResponse.postValue(Resource.Success(response))
            } else {
                _registerResponse.postValue(Resource.Error())
            }

        }
    }



    fun login(email : String, password : String){
        viewModelScope.launch{

            val response = repository.login(email, password)

            if(isValidEmail(email) && isValidPassword(password) && response.isSuccessful){
                _loginResponse.postValue((Resource.Success(response)))

            }
            else {
                _loginResponse.postValue((Resource.Error()))
            }
        }
    }



    fun handleUsername(username : String){
        if(username != ""){
            _nullUsername.postValue(Event(Resource.Success()))
        } else {
            _nullUsername.postValue(Event(Resource.Error()))
        }
    }

    fun checking(){
        if(getLoggedFlag()){
            _isLoggedIn.postValue(Event(Resource.Success()))
        } else {
            _isLoggedIn.postValue(Event(Resource.Error()))
        }
    }

    fun setFlag(flag : Boolean){
        repository.saveLogged(flag)
    }

    fun saveUsername(username: String){
        repository.saveUsername(username)
    }

    private fun getLoggedFlag() = repository.getLogged()

    fun getUsername() = repository.getUsername()

    fun remove(){
        repository.remove()
    }



}