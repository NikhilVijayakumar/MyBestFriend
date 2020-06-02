package com.nikhil.mybestfriend.feature.auth.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikhil.mybestfriend.feature.auth.data.repo.LoginRepo

class LoginViewModelFactory(
    val repo: LoginRepo
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repo) as T
    }
}