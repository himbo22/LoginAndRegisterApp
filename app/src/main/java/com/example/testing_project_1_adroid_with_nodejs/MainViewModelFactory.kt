package com.example.testing_project_1_adroid_with_nodejs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testing_project_1_adroid_with_nodejs.repository.Repository
import com.example.testing_project_1_adroid_with_nodejs.viewmodel.MainViewModel

class MainViewModelFactory(private val repository: Repository) :  ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}