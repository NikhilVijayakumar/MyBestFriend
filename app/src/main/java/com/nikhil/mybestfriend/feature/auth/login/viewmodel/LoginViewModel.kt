package com.nikhil.mybestfriend.feature.auth.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity
import com.nikhil.mybestfriend.feature.auth.data.repo.LoginRepo
import com.nikhil.mybestfriend.feature.commons.enums.RepoStatus
import com.nikhil.mybestfriend.feature.commons.viewmodel.BaseViewModel

class LoginViewModel(private val repo: LoginRepo) : BaseViewModel() {
    private val repoStatus  = MutableLiveData<RepoStatus>()
    val status: LiveData<RepoStatus>
    get() = repoStatus
    val data: LiveData<List<UserEntity>> = repo.userList

     fun login(
         entity: UserEntity,
         userList: List<UserEntity>?
     ) {
         repoStatus.postValue(RepoStatus.LOADING)
         userList?.let { entityList ->
             val result: UserEntity? = entityList.find { (it.email == entity.email) && (it.password == entity.password) }
           if(result != null){
                repoStatus.postValue(RepoStatus.COMPLETED)
            }else{
               repoStatus.postValue(RepoStatus.ERROR)
           }
         }
         if(userList == null){
             repoStatus.postValue(RepoStatus.DATABASE_EXCEPTION)
         }

    }
}