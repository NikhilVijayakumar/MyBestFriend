package com.nikhil.mybestfriend.feature.cat.data.db.doa

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity

@Dao
interface UserDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userEntity: UserEntity)

  /*  @Query("UPDATE user SET password=:password WHERE email = :email")
    fun updatePassword(email:String,password: String)*/

    @Query("select * from user  where email = :email")
    fun getUser(email:String): LiveData<UserEntity>
}