package com.nikhil.mybestfriend.feature.commons.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nikhil.mybestfriend.feature.auth.data.db.entity.UserEntity
import com.nikhil.mybestfriend.feature.cat.data.db.doa.CatDoa
import com.nikhil.mybestfriend.feature.auth.data.db.doa.UserDoa
import com.nikhil.mybestfriend.feature.cat.data.db.entity.CatEntity

@Database(
    entities = [UserEntity::class, CatEntity::class],
    version = 1
)
abstract class FriendDatabase : RoomDatabase() {
    abstract fun userDao(): UserDoa
    abstract fun catDao(): CatDoa


    companion object {
        @Volatile private var instance: FriendDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "myBestFriend.db"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                FriendDatabase::class.java, DB_NAME)
                .build()
    }
}
