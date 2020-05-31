package com.nikhil.mybestfriend.feature.auth.data.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus

interface RegisterRepo {
    val repoStatus: LiveData<RepoStatus>
    suspend fun register(userEntity: UserEntity)
}