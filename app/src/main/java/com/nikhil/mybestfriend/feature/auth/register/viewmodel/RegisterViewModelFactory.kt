package com.nikhil.mybestfriend.feature.auth.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikhil.mybestfriend.feature.auth.data.repo.RegisterRepo

class RegisterViewModelFactory(
    val repo: RegisterRepo
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RegisterViewModel(repo) as T
    }
}