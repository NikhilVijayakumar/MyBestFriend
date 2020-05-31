package com.nikhil.mybestfriend.feature.auth.register.viewmodel

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity
import com.nikhil.mybestfriend.feature.auth.data.repo.RegisterRepo
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus
import com.nikhil.mybestfriend.feature.commons.utils.lazyDeferred
import com.nikhil.mybestfriend.feature.commons.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class RegisterViewModel(val repo: RegisterRepo) : BaseViewModel() {
    val status: LiveData<RepoStatus> = repo.repoStatus
     suspend fun register(entity: UserEntity) {
        withContext(IO) {
            repo.register(entity)
        }
     }
}