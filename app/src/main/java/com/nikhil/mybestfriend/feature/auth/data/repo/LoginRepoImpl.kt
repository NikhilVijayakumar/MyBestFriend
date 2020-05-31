package com.nikhil.mybestfriend.feature.auth.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity
import com.nikhil.mybestfriend.feature.cat.data.db.doa.CatDoa
import com.nikhil.mybestfriend.feature.cat.data.db.doa.UserDoa
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepoImpl(private val userDoa: UserDoa) : LoginRepo {

    private val _repoStatus = MutableLiveData<RepoStatus>()
    override val repoStatus: LiveData<RepoStatus>
        get() =_repoStatus

    override suspend fun login(userEntity: UserEntity) {
        //_repoStatus.postValue(RepoStatus.LOADING)
        try{
            val email = "%"+userEntity.email+"%"
            val data = userDoa.getUser(email)
            val loginEntity = data.value
            loginEntity?.let {
                if(it.password.equals(userEntity.password)){
                    _repoStatus.postValue(RepoStatus.COMPLETED)
                }else{
                    _repoStatus.postValue(RepoStatus.ERROR)
                }

            }
            if(data.value == null){
                _repoStatus.postValue(RepoStatus.DATABASE_EXCEPTION)
            }

        }catch (e:Exception){
            _repoStatus.postValue(RepoStatus.DATABASE_EXCEPTION)
        }
    }
}