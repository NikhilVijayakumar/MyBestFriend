package com.nikhil.mybestfriend.feature.auth.login.viewmodel

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity
import com.nikhil.mybestfriend.feature.auth.data.repo.LoginRepo
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus
import com.nikhil.mybestfriend.feature.commons.utils.lazyDeferred
import com.nikhil.mybestfriend.feature.commons.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(val repo: LoginRepo) : BaseViewModel() {
    val status: LiveData<RepoStatus> = repo.repoStatus
    suspend fun login(entity: UserEntity) {
        withContext(Dispatchers.IO) {
            repo.login(entity)
        }
    }
}