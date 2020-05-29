package com.nikhil.mybestfriend.feature.commons.application

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatBreedDataSource
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatBreedDataSourceImpl
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatDetailDataSource
import com.nikhil.mybestfriend.feature.cat.data.api.datasource.CatDetailDataSourceImpl
import com.nikhil.mybestfriend.feature.cat.data.repo.CatBreadRepo
import com.nikhil.mybestfriend.feature.cat.data.repo.CatBreadRepoImpl
import com.nikhil.mybestfriend.feature.commons.data.api.interceptor.ConnectivityInterceptor
import com.nikhil.mybestfriend.feature.commons.data.api.interceptor.ConnectivityInterceptorImpl
import com.nikhil.mybestfriend.feature.commons.data.api.service.CatAPIService
import com.nikhil.mybestfriend.feature.commons.data.db.FriendDatabase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class FriendApllication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FriendApllication))
        bind() from singleton { FriendDatabase(instance()) }
        bind() from singleton { instance<FriendDatabase>().catDao() }
        bind() from singleton { instance<FriendDatabase>().userDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { CatAPIService(instance()) }
        bind<CatBreedDataSource>() with singleton { CatBreedDataSourceImpl(instance()) }
        bind<CatDetailDataSource>() with singleton { CatDetailDataSourceImpl(instance()) }
        bind<CatBreadRepo>() with singleton { CatBreadRepoImpl(instance(),instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}
