package com.nikhil.mybestfriend.feature.auth.data.repo

import androidx.lifecycle.LiveData
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity

interface LoginRepo {

    val userList: LiveData<List<UserEntity>>


}