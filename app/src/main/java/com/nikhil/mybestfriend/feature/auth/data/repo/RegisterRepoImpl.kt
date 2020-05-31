package com.nikhil.mybestfriend.feature.auth.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity
import com.nikhil.mybestfriend.feature.cat.data.db.doa.UserDoa
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus

class RegisterRepoImpl(private val userDoa: UserDoa) : RegisterRepo {

    private val _repoStatus = MutableLiveData<RepoStatus>()
    override val repoStatus: LiveData<RepoStatus>
        get() =_repoStatus

    override suspend fun register(userEntity: UserEntity) {
        _repoStatus.postValue(RepoStatus.LOADING)
        try{
            userDoa.insert(userEntity)
            _repoStatus.postValue(RepoStatus.COMPLETED)
        }catch (e:Exception){
            _repoStatus.postValue(RepoStatus.DATABASE_EXCEPTION)
        }
    }
}