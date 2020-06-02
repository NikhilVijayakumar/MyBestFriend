package com.nikhil.mybestfriend.feature.auth.data.repo

import com.nikhil.mybestfriend.feature.auth.data.db.doa.UserDoa

class LoginRepoImpl(private val userDoa: UserDoa) : LoginRepo {
    override val userList = userDoa.getAllUser()
}